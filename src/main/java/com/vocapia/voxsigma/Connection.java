package com.vocapia.voxsigma;

import com.vocapia.voxsigma.options.AudiofileAsStream;
import com.vocapia.voxsigma.response.ErrorResponse;
import com.vocapia.voxsigma.response.SuccessChunkedResponse;
import com.vocapia.voxsigma.response.SuccessResponse;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.SocketFactory;

/**
 * Class to execute a request to the server and get the response.
 */
public class Connection {

    /** The server address. */
    protected String address;
    /** The server port. */
    protected int port;
    /** The authentication. */
    protected Auth auth;
    /** The socket used. */
    protected Socket socket;

    public Connection(String address, int port, Auth auth) throws IOException {
        this.address = address;
        this.port = port;
        this.auth = auth;
        this.initSocket();
    }

    /**
     * Initialize the socket.
     */
    protected void initSocket() throws IOException {
        this.socket = SocketFactory.getDefault().createSocket(this.address, this.port);
    }

    /**
     * Returne the InputStream of the socket.
     * @return InputStream
     */
    public InputStream getInputStream() throws IOException {
        return this.socket.getInputStream();
    }

    /**
     * Execute the request and return a Reponse object
     * @param method The method object to use
     * @return Response
     */
    public final Response execute(Method method) throws IOException {
        String headers = "Host: " + this.address + ":" + this.port + "\r\n";
        headers = headers + this.auth.getHeader() + "\r\n";
        headers = headers + "User-Agent: java.com.vocapia.voxsigma/1.0\r\n";

        Option[] option_a = method.getOptions();

        String path = "/voxsigma?method=" + method;
        String body = "";
        String boundary = "--------------------------0a8606733948";

        List<String> query_string_a = new ArrayList<String>();
        for (Option option : option_a) {
            query_string_a.add(option.renderQueryString());
        }
        // filter not empty strings in query_string_a
        query_string_a.removeIf(String::isEmpty);
        path = path + "&" + String.join("&", query_string_a);

        if (method.isStream()) {
            headers = headers + "Transfer-Encoding: chunked\r\n";
        } else {
            long body_size = 0;
            for (Option option : option_a) {
                long option_body_size = option.getHttpBodySize();
                body_size += option_body_size;
                if (option_body_size > 0) {
                    body_size+= ("--"+boundary+"\r\n").getBytes(StandardCharsets.UTF_8).length;
                }
            }

            if (body_size > 0) {
                body_size += ("--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8).length;
            }

            headers = headers + "Content-Length: " + body_size + "\r\n";

            if (body_size > 0) {
                headers = headers + "Content-Type: multipart/form-data; " + boundary + "\r\n";
            }
        }

        body = "PUT " + path + " HTTP1.1\r\n";
        body = body + headers + "\r\n";
        BufferedOutputStream output = new BufferedOutputStream(this.socket.getOutputStream());
        output.write(body.getBytes(StandardCharsets.UTF_8));
        if (method.isStream()) {
            output.flush();
            ((AudiofileAsStream)((AudioMethod)method).getAudiofile()).setOutputStream(output);
        } else {
            for (Option option : option_a) {
                if (option.getHttpBodySize() == 0) {
                    continue;
                }
                output.write(("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8));
                output.write(option.getMultipartBodyHeader().getBytes(StandardCharsets.UTF_8));
                option.renderHttpBody(output);
                output.write(("\r\n").getBytes(StandardCharsets.UTF_8));
            }
            output.write(("--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));
            output.flush();
        }



        InputStream stream = this.getInputStream();
        int status_code = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        String response_type = "";
        Map<String,String> headers_map = new HashMap<String,String>();
        try {
            String str;
            Pattern http_status_pattern = Pattern.compile("^HTTP/([0-9]\\.[0-9])\\s([0-9]{3})\\s(.*)$");

            while ((str = reader.readLine()) != null) {
                if (str.isEmpty()) {
                    break;
                }
                Matcher http_status_matcher = http_status_pattern.matcher(str);
                if (http_status_matcher.matches()) {
                    // get status code from the second group of the regex matches
                    status_code = Integer.parseInt(Objects.requireNonNull(http_status_matcher.group(2)));
                    continue;
                }
                String[] header_a = str.split(":", 2);
                headers_map.put(header_a[0], header_a[1].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String content_type = Objects.requireNonNull(headers_map.get("Content-Type")).split(";")[0];
        switch (content_type) {
            case "application/json":
                response_type = Response.TYPE_JSON;
                break;
            case "text/xml":
                response_type = Response.TYPE_XML;
                break;
            default:
                return new ErrorResponse(status_code, "Unknown content type: " + content_type);
        }

        if (status_code != 200) {
            return ErrorResponse.initUsingBufferReader(reader, response_type);
        }

        boolean chunked = ("chunked".equals(headers_map.get("Transfer-Encoding")));
        if (chunked) {
            return SuccessChunkedResponse.initUsingBufferReader(reader, response_type);
        }
        return SuccessResponse.initUsingBufferReader(reader, response_type);
    }

}

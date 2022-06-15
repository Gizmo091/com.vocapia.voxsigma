package com.vocapia.voxsigma;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

abstract public class Option {

    protected String name;
    protected String value;

    public Option(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Render the option as a query string to generate the API Rest endpoint URL
     * @return String
     */
    public String renderQueryString() {
        return URLEncoder.encode(this.name, StandardCharsets.UTF_8)+"="+URLEncoder.encode(this.value, StandardCharsets.UTF_8);
    }

    /**
     * Return the size of content added to a multipart request body
     * @return long
     */
    abstract public long getHttpBodySize();

    /**
     * Return the header for a multipart request body part
     * @return String
     */
    abstract public String getMultipartBodyHeader();

    /**
     * Write the content of the request body in the output stream
     * @param outputStream
     */
    abstract public void renderHttpBody(OutputStream outputStream);

}

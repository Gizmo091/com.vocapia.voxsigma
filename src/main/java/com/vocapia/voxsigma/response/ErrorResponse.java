package com.vocapia.voxsigma.response;

import com.vocapia.voxsigma.Response;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Class to handle the error response of the server.
 */
public class ErrorResponse extends Response {

    protected int code;
    protected String message;

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public static ErrorResponse initUsingBufferReader(BufferedReader bufferedReader, String response_type) {
        switch (response_type) {
            case Response.TYPE_XML:
                return initUsingXmlBufferReader(bufferedReader);
            case Response.TYPE_JSON:
                return initUsingJsonBufferReader(bufferedReader);
        }
        return new ErrorResponse(-1, "Unknown response type");
    }

    protected static ErrorResponse initUsingXmlBufferReader(BufferedReader bufferedReader) {
        String line;
        StringBuilder builder = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            return null;
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(builder.toString())));
            doc.getDocumentElement().normalize();

            Node ErrorItem = doc.getElementsByTagName("Error").item(0);
            int error_code = Integer.parseInt(ErrorItem.getAttributes().getNamedItem("code").getNodeValue());
            String error_message = ErrorItem.getTextContent();
            bufferedReader.close();
            return new ErrorResponse(error_code, error_message);

        } catch (ParserConfigurationException | IOException | SAXException ignored) {
        }

        return null;
    }

    protected static ErrorResponse initUsingJsonBufferReader(BufferedReader bufferedReader) {
        return new ErrorResponse(-1, "JSON response type not implemented");
    }


}

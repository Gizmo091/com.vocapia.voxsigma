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

public class SuccessResponse extends Response {

    protected Document document;

    public SuccessResponse(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public static Response initUsingBufferReader(BufferedReader bufferedReader, String response_type) {
        switch (response_type) {
            case Response.TYPE_XML:
                return initUsingXmlBufferReader(bufferedReader);
            case Response.TYPE_JSON:
                return initUsingJsonBufferReader(bufferedReader);
        }
        return new ErrorResponse(-1, "Unknown response type");
    }

    protected static Response initUsingXmlBufferReader(BufferedReader bufferedReader) {
        String line;
        StringBuilder builder = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            return new ErrorResponse(-1, "Error reading response : " + e.getMessage());
        }

        try {
            bufferedReader.close();
        } catch (IOException e) {
            return new ErrorResponse(-1, "Error closing response : " + e.getMessage());
        }


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(builder.toString())));
            doc.getDocumentElement().normalize();
            if (doc.getElementsByTagName("Error").getLength() > 0) {
                Node ErrorItem = doc.getElementsByTagName("Error").item(0);
                int error_code = Integer.parseInt(ErrorItem.getAttributes().getNamedItem("code").getNodeValue());
                String error_message = ErrorItem.getTextContent();
                return new ErrorResponse(error_code, error_message);
            }
            return new SuccessResponse(doc);
        } catch (ParserConfigurationException | IOException | SAXException exception) {
            return new ErrorResponse(-1, "Error parsing response : "+ exception.getMessage());
        }
    }

    protected static Response initUsingJsonBufferReader(BufferedReader bufferedReader) {
        return new ErrorResponse(-1, "JSON response type not implemented");
    }


}

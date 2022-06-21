package com.vocapia.demo.voxsigma;

import com.vocapia.voxsigma.Connection;
import com.vocapia.voxsigma.Response;
import com.vocapia.voxsigma.options.Session;
import com.vocapia.voxsigma.response.ErrorResponse;
import com.vocapia.voxsigma.response.SuccessResponse;

import org.w3c.dom.Document;

import java.io.IOException;

final public class DemoStatus extends Demo {

    private final String session_id;

    public DemoStatus(Connection connection, String session_id) {
        super(connection);
        this.session_id = session_id;
    }

    public void run() {
        try {
            Response response = this.connection.execute(new com.vocapia.voxsigma.methods.Status(new Session(this.session_id)));
            if (response.isNotError()) {
                if (!response.isChunked()) {
                    Document XMLDocument = ((SuccessResponse) response).getDocument();
                }
            } else if (((ErrorResponse) response).getCode() == 320) {
                System.out.println("Session in progress");
            } else {
                System.out.println("Session status : " + ((ErrorResponse) response).getCode());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}

package com.vocapia.demo.voxsigma;

import com.vocapia.voxsigma.Connection;
import com.vocapia.voxsigma.Response;
import com.vocapia.voxsigma.response.SuccessResponse;

import java.io.IOException;

final public class DemoHello extends Demo {

    public DemoHello(Connection connection) {

        super(connection);

    }

    public void run() {
        try {
            Response response = this.connection.execute(new com.vocapia.voxsigma.methods.Hello());
            if (response.isNotError()) {
                if (!response.isChunked()) {
                    System.out.println("Hello response");
                    System.out.println(((SuccessResponse) response).getDocument().getElementsByTagName("Message").item(0).getTextContent());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}

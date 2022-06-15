package com.vocapia.voxsigma;

import java.io.IOException;

import javax.net.ssl.SSLSocketFactory;

public class SSLConnection extends Connection {


    public SSLConnection(String address, int port, Auth auth) throws IOException {
        super(address, port, auth);
    }

    @Override
    protected void initSocket() throws IOException {
            this.socket = ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(this.address, this.port);
    }
}

package com.vocapia.demo.voxsigma;

import com.vocapia.voxsigma.Connection;

abstract public class Demo {

    protected Connection connection;

    /**
     *
     * @param connection Connection to the web service like 'new SSLConnection("rest1.vocapia.com',8094, new UserPassword("user","password"))'
     */
    public Demo(Connection connection) {
        this.connection = connection;
    }

    abstract public void run();


}

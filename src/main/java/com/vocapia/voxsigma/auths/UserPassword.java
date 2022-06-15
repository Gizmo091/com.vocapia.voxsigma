package com.vocapia.voxsigma.auths;

import com.vocapia.voxsigma.Auth;

import java.util.Base64;

public class UserPassword extends Auth {

    protected String user;
    protected String password;

    public UserPassword(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    public String getHeader() {
        return "Authorization: Basic " + Base64.getEncoder().encodeToString((user + ":" + password).getBytes());
    }
}

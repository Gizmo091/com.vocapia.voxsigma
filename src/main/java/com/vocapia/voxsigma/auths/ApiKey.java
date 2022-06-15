package com.vocapia.voxsigma.auths;

import com.vocapia.voxsigma.Auth;

/**
 * Class to define authentication method with API key for API connection
 */
public class ApiKey extends Auth {

    /** API key */
    protected String api_key;

    public ApiKey(String api_key) {
        this.api_key = api_key;
    }

    @Override
    public String getHeader() {
        return "api-key: " + api_key;
    }
}

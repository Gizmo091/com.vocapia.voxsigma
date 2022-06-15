package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.OptionString;


/**
 * Session-ID
 *
 * The session ID is required for the status method.
 */
public class Session extends OptionString {

    public Session( String value) {
        super("session", value);
    }


}

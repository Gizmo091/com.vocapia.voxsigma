package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.OptionString;


/**
 * Session-ID
 *
 * The session ID is required for the status method.
 */
public class Session extends OptionString {

    public Session( String session_id) {
        super("session", session_id);
    }


}

package com.vocapia.voxsigma.methods;

import com.vocapia.voxsigma.Method;
import com.vocapia.voxsigma.options.Session;

/**
 * Class to define status method.
 * This method is call to get the status of a request.
 * REST API return document if the request is done.
 *
 * @author mvedie@vocapia.com
 */
final public class Status extends Method {

    public Status(Session session) {
        super("status");
        this.addOption(session);
    }
}

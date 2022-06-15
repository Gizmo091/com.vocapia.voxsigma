package com.vocapia.voxsigma;

import com.vocapia.voxsigma.response.ErrorResponse;
import com.vocapia.voxsigma.response.SuccessChunkedResponse;

public class Response {

    public final static String TYPE_XML = "xml";
    public final static String TYPE_JSON = "json";

    public final boolean isNotError() {
        return (!(this instanceof ErrorResponse));
    }

    public final boolean isChunked() {
        return (this instanceof SuccessChunkedResponse);
    }
}

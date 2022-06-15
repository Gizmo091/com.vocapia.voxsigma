package com.vocapia.voxsigma.options;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Audiofile to submit to the REST API using Custom Stream source.
 * Usefull to make real-time call
 * This type of file always sent with chunked transfer encoding
 *
 * Base class to inherit from.
 */
abstract public class AudiofileAsStream extends Audiofile {

    public AudiofileAsStream(String filename) {
        super(filename);
    }

    @Override
    protected final long getFileSize() {
        // return 0 to indicate that the file size is unknown
        return 0;
    }

    @Override
    public final String getMultipartBodyHeader() {
        // no header for chunked transfer encoding
        return "";
    }

    public final String renderQueryString() {
        // give the name of the file to the server by a query string because the body of the request cannot contain the name of the file when chunked transfer encoding is used
        try {
            return URLEncoder.encode(this.name, "UTF-8") + "=" + URLEncoder.encode(this.value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public final void renderHttpBody(OutputStream outputStream) {
        // this method is called for non-chunked transfer encoding call.
        // so we can't write to the output stream directly.
        return;
    }

    private BufferedOutputStream outputStream;

    /**
     * Method called by Connection to give the socket output stream to write to.
     *
     * @param outputStream the output stream to write to.
     */
    public final void setOutputStream(BufferedOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Method to call to start writing to the output stream.
     */
    public final void send() {
        this.sendBytes(this.outputStream);
    }

    /**
     * Method to override to write to the output stream.
     *
     * @param outputStream Output stream to write to.
     */
    abstract protected void sendBytes(BufferedOutputStream outputStream);

}

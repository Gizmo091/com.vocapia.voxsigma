package com.vocapia.voxsigma.options;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Audiofile to submit to the REST API using InputStream source.
 */
public class AudiofileInputStream extends Audiofile implements InputStreamInterface {

    /** InputStream source of the audiofile. */
    protected InputStream stream;

    public AudiofileInputStream(String filename, InputStream stream) {
        super(filename);
        this.stream = stream;
    }

    @Override
    protected long getFileSize() {
        try {
            return this.stream.available();
        } catch (IOException ioException) {
            return 0;
        }
    }

    @Override
    public void renderHttpBody(OutputStream outputStream) {
        InputStreamInterface.super.writeOutputStream(outputStream,this.stream);
    }
}

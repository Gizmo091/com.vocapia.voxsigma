package com.vocapia.voxsigma.options;

import java.io.OutputStream;


/**
 * Vocfile to submit to the REST API using file stored as string.
 */
public class VocfileRaw extends Vocfile implements RawFileInterface {

    protected String text;

    public VocfileRaw(String text) {
        super("rawvoc.txt");
        this.text = text+"\r\n";
    }

    @Override
    protected long getFileSize() {
        return RawFileInterface.super.getRawFileSize(this.text);
    }

    @Override
    public void renderHttpBody(OutputStream outputStream) {
        RawFileInterface.super.writeOutputStream(outputStream,this.text);
    }
}

package com.vocapia.voxsigma.options;

import java.io.OutputStream;

/**
 * Slfile to submit to the REST API using file stored as string.
 */
public class SlfileRaw extends Slfile implements RawFileInterface {

    protected String text;

    public SlfileRaw(String text) {
        super( "rawsl.txt");
        this.text = text;
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

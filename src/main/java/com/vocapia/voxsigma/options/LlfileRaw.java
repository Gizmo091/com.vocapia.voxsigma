package com.vocapia.voxsigma.options;

import java.io.OutputStream;

/**
 * LLfile to submit to the REST API using file stored as string.
 */
public class LlfileRaw extends Llfile implements RawFileInterface {

    protected String text;

    public LlfileRaw(String text)  {
        super("rawll.txt");
        this.text = text;
    }

    @Override
    protected long getFileSize() {
        return RawFileInterface.super.getRawFileSize(this.text);
    }

    @Override
    public void renderHttpBody(OutputStream outputStream) {
        RawFileInterface.super.writeOutputStream(outputStream, this.text);
    }
}

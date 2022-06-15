package com.vocapia.voxsigma.options;

import java.io.OutputStream;


/**
 * Textfile to submit to the REST API using file stored as string.
 */
public class TextfileRaw extends Textfile implements RawFileInterface {

    protected String text;

    public TextfileRaw(String text)  {
        super( "rawtext.txt");
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

package com.vocapia.voxsigma.options;


import java.io.File;
import java.io.OutputStream;

/**
 * Audiofile to submit to the REST API using file stored locally.
 */
public class AudiofileLocalFile extends Audiofile implements LocalFileInterface {

    /** File source of the audiofile. */
    protected File local_file;

    public AudiofileLocalFile( String local_path) {
        super( (new File(local_path)).getName());
        this.local_file = new File(local_path);
    }


    @Override
    protected long getFileSize() {
        return LocalFileInterface.super.getLocalFileSize(this.local_file);
    }

    @Override
    public void renderHttpBody(OutputStream outputStream) {
        LocalFileInterface.super.writeOutputStream(outputStream,this.local_file);
    }
}

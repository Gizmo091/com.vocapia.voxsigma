package com.vocapia.voxsigma.options;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

/**
 * Slfile to submit to the REST API using file stored locally.
 */
public class SlfileLocalFile extends Slfile implements LocalFileInterface {

    protected File local_file;

    public SlfileLocalFile(String local_path) throws FileNotFoundException {
        super((new File(local_path)).getName());
        this.local_file = new File(local_path);
        if (!this.local_file.exists()) {
            throw new FileNotFoundException("File "+local_path+" not found");
        }
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

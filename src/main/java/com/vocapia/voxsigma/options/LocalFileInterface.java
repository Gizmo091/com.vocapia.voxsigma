package com.vocapia.voxsigma.options;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;


/**
 * Interface to define the way to write an OutputStream for File with local file source
 */
public interface LocalFileInterface {

    default long getLocalFileSize(File file) {
        return file.length();
    }

    default void writeOutputStream(OutputStream outputStream, File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // process the line
                outputStream.write(line.getBytes());
            }
        } catch (IOException ignored) { }
    }
}

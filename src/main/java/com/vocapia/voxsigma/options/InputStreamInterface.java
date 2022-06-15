package com.vocapia.voxsigma.options;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface to define the way to write an OutputStream for File with InputStream
 */
public interface InputStreamInterface {

    default void writeOutputStream(OutputStream outputStream, InputStream inputStream) {
        try {
            byte[] buf = new byte[8192];
            int length;
            while ((length = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, length);
            }

        } catch (IOException ignored) {
        }
    }
}

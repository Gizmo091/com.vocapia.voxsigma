package com.vocapia.voxsigma.options;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Interface to define the way to write an OutputStream for File with string source
 */
public interface RawFileInterface {


    default long getRawFileSize(String raw) {
        return raw.getBytes(StandardCharsets.UTF_8).length;
    }

    default void writeOutputStream(OutputStream outputStream, String raw) {
        try {
            outputStream.write(raw.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

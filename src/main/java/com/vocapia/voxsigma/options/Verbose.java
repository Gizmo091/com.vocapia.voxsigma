package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.Option;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * get session log
 * <p>
 * verbose=1 to add a log header to the server response.
 * verbose=2 to add a progress header to the server response.
 */
public class Verbose extends Option {

    public static final int NO_VERBOSE = 0;
    public static final int VERBOSE_LOG_HEADER = 1; // to add a log header to the server response.
    public static final int VERBOSE_PROGRESS_HEADER = 2; // to add a progress header to the server response.

    private static final int[] VERBOSE_VALUES = {Verbose.NO_VERBOSE, Verbose.VERBOSE_LOG_HEADER, Verbose.VERBOSE_PROGRESS_HEADER};


    public Verbose(int value) throws Exception {
        super("verbose", String.valueOf(value));
        if (IntStream.of(Verbose.VERBOSE_VALUES).noneMatch(val -> val == value)) {
            throw new Exception("Bad value for Verbose parameter");
        }
    }


    @Override
    public long getHttpBodySize() {
        return 0;
    }

    @Override
    public String getMultipartBodyHeader() {
        return "";
    }

    @Override
    public void renderHttpBody(OutputStream outputStream) {
    }

}

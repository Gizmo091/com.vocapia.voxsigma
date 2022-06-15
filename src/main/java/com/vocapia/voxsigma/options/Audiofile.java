package com.vocapia.voxsigma.options;

/**
 * Audio file name
 *
 * This argument is required for all methods except hello,
 * upload and status. The audio data corresponding to the
 * audio file (max. size of 300M bytes) must be included in
 * the HTTP message. Supported audio formats: AIFF, ASF/WMA,
 * FLAC, MS-Wave, MPEG, Ogg/Vorbis, Nist Sphere, Sun AU (all
 * single track).
 */
abstract public class Audiofile extends File {

    /**
     * Constructor
     *
     * @param filename Name of the file
     */
    public Audiofile(String filename) {
        super("audiofile", filename);
    }

    public String renderQueryString() {
        return "";
    }

}

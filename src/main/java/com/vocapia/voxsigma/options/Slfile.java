package com.vocapia.voxsigma.options;

/**
 * Speaker list file
 *
 * Optional argument for the vrbs_trans and upload methods.
 * It can be used to specify a subset of the speaker list
 * (if any) associated to the model.
 * The file name and file content must be included in a
 * MIME multipart message along with the audio file name
 * and the audio file. Supported format: plain text file
 * (Unix style) with one line per speaker.
 */
abstract public class Slfile extends File {

    public Slfile(String value) {
        super("slfile", value);
    }
}

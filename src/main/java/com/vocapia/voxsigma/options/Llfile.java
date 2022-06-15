package com.vocapia.voxsigma.options;


/**
 * Language list file
 *
 * Optional argument for the vrbs_lid, vrcts_lid, vrbs_trans,
 * vrcts_trans and upload methods.
 * The file name and the file content must be included in a
 * MIME multipart message along with the audio file name and
 * the audio file. Supported format: plain text file
 * (Unix style) with one line per language with optional prior
 * probability.
 */
abstract public class Llfile extends File {

    public Llfile(String value) {
        super("llfile", value);
    }
}

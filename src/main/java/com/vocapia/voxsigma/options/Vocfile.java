package com.vocapia.voxsigma.options;

/**
 * Vocabulary file name
 *
 * Optional argument for the vrbs_align, vrcts_trans, vrbs_trans
 * and upload methods. The vocabulary file name and the vocabulary
 * file must be included in a MIME multipart message along with
 * the audio file name and the audio file. Supported format: plain
 * text file (Unix style) with one line per word with optional pronunciations.
 */
abstract public class Vocfile extends File {

    public Vocfile(String value) {
        super("vocfile", value);
    }

}

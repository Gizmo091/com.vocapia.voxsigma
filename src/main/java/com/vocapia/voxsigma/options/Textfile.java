package com.vocapia.voxsigma.options;

/**
 * Textfile
 *
 * This argument is required for the vrbs_align method.
 * It is optional for the vrcts_trans, vrbs_trans and upload methods.
 * The text file name and the text file must be included in a MIME
 * multipart message along with the audio file name and the audio file.
 * Supported text formats: plain text file using ASCII, ISO-8859 or UTF8
 * character sets with language dependent restrictions.
 * The text should include one sentence or clause per line ending with
 * a optional punctuation mark and a line-feed character to mark the end
 * of a line (i.e. Unix style).
 */
abstract public class Textfile extends File {

    public Textfile( String value) {
        super("textfile", value);
    }

}

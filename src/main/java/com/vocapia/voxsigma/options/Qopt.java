package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.OptionString;

/**
 * Decoding option
 *
 * The option value is a string of letters
 * (for example qopt=df):
 * 'd' for dual track decoding (vrcts),
 * 'n' for no postprocessing (punctuation and numbers),
 * 'p' for no audio partitioning,
 * 'm' for multiple word hypotheses,
 * 'f' for no confidence score filtering.
 */
public class Qopt extends OptionString {

    public Qopt(String value) {
        super("qopt", value);
        char[] value_char = value.toCharArray();
        // check if value_char contains only 'd', 'n', 'p', 'm' or 'f'
        for (char c : value_char) {
            if (c != 'd' && c != 'n' && c != 'p' && c != 'm' && c != 'f') {
                throw new IllegalArgumentException("Qopt: value must be 'd', 'n', 'p', 'm' or 'f'");
            }
        }

    }
}

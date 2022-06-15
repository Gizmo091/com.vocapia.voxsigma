package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.OptionFloat;

/**
 * Prior language model weight
 * <p>
 * Optional argument for the vrbs_trans and vrcts_trans methods
 * if used with textfile and/or vocfile.
 * Set the weight of the default language model [0.5-1.0].
 * By default this weight is estimated automatically.
 */
public class Uopt extends OptionFloat {

    public Uopt(float value) {
        super("uopt", value);

        if (value < 0.5 || value > 1) {
            throw new IllegalArgumentException("Uopt must be between 0.5 and 1");
        }
    }
}

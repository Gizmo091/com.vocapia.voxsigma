package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.OptionFloat;

/**
 * LID decision threshold
 *
 * Set the language identification threshold [0.0-1.0].
 * By default qlopt=0.5.
 */
public class Qlopt extends OptionFloat {

    public Qlopt(float value) {
        super("qlopt", value);

        if (value < 0 || value > 1) {
            throw new IllegalArgumentException("Qlopt must be between 0 and 1");
        }
    }
}

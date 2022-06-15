package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.OptionFloat;

/**
 * LID version
 *
 * This option is use to specify a specific LID version (for example ropt=6.0).
 * By default the service uses the most recent version.
 */
public class Ropt extends OptionFloat {

    public Ropt(float value) {
        super("ropt", value);

        if (value < 0) {
            throw new IllegalArgumentException("Ropt must be greater than 0");
        }
    }
}

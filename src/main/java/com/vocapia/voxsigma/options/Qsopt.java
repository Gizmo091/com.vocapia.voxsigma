package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.OptionInt;

/**
 * Speaker segmentation
 *
 * Use qsopt=1 to add speaker segmentation for the vrbs_align method.
 * By default qsopt=0.
 */
public class Qsopt extends OptionInt {

    public Qsopt(int value) {
        super("qsopt", value);
        // check if value is 0 or 1 (0 is default)
        if (value != 0 && value != 1) {
            throw new IllegalArgumentException("Qsopt must be 0 or 1");
        }

    }
}

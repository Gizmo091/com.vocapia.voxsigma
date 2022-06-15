package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.OptionInt;

/**
 * Set priority
 *
 * The default priority is 0. Use 10 for a low priority request3.
 */
public class Priority extends OptionInt {

    public Priority(int value) {
        super("priority", value);

        if (value < 0) {
            throw new IllegalArgumentException("Priority must be greater than 0");
        }
    }
}

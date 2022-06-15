package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.OptionInt;

/**
 * Realtime
 *
 * The real-time mode is activated by adding the option rtopt=1 to the vrbs_trans or vrcts_trans methods and by specifying the mandatory model and audiofile arguments. Here is example request:
 */
public class Rtopt extends OptionInt {

    public Rtopt(int value) {
        super("rtopt", value);

        if (value != 0 && value != 1) {
            throw new IllegalArgumentException("rtopt must be 0 or 1");
        }
    }
}

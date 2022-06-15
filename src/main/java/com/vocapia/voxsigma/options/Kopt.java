package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.OptionInt;

/**
 * Max #speakers
 *
 * The default maximum number of speakers is 1 for vrcts methods (i.e. kopt=1)
 * and infinity for vrbs methods
 */
public class Kopt extends OptionInt {

    public Kopt( int value) {
        super("kopt", value);
    }

}

package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.OptionInt;

/**
 * Speech duration (s)
 *
 * Maximum speech duration for language identification.
 * Applies to vrbs_trans, vrcts_trans, vrbs_lid and vrcts_lid.
 * By default dlopt=30.
 * The entire audio file is used to identify the language if dlopt=0.
 * With the vrbs_trans and vrcts_trans methods,
 * you have to use this option if the model is not specified.
 * Alternatively model=und instructs the service that the
 * language must be identified automatically.
 */
public class Dlopt extends OptionInt {

    public Dlopt( int value) {
        super("dlopt", value);

        if (value < 0) {
            throw new IllegalArgumentException("Dlopt must be greater than 0");
        }
    }

}

package com.vocapia.voxsigma.options;

import com.vocapia.voxsigma.OptionString;

/**
 * Model name
 *
 * A set of models is available for each method.
 * For the transcription methods (e.g. vrbs_trans)
 * the model name specifies the language (e.g. eng),
 * and optionally the dialect, the application and the
 * model version. Some models can be specifically designed
 * for a particular user application.
 */
public class Model extends OptionString {

    public Model( String value) {
        super("model", value);
    }

}

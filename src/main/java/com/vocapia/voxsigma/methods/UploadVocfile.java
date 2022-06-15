package com.vocapia.voxsigma.methods;

import com.vocapia.voxsigma.options.Vocfile;


/**
 * Class to define an Upload method for a vocfile
 */
final public class UploadVocfile extends Upload {

    public UploadVocfile(Vocfile vocfile) {
        super();
        this.addOption(vocfile);
    }
}

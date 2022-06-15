package com.vocapia.voxsigma.methods;

import com.vocapia.voxsigma.options.Slfile;


/**
 * Class to define an Upload method for a Slfile
 */
final public class UploadSlfile extends Upload {

    public UploadSlfile(Slfile slfile) {
        super();
        this.addOption(slfile);
    }
}

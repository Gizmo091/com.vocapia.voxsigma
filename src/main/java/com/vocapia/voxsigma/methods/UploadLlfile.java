package com.vocapia.voxsigma.methods;

import com.vocapia.voxsigma.options.Llfile;


/**
 * Class to define an Upload method for a llfile
 */
final public class UploadLlfile extends Upload {

    public UploadLlfile(Llfile llfile) {
        super();
        this.addOption(llfile);
    }
}

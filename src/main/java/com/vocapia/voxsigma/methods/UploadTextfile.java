package com.vocapia.voxsigma.methods;

import com.vocapia.voxsigma.options.Textfile;


/**
 * Class to define an Upload method for a Textfile
 */
final public class UploadTextfile extends Upload {

    public UploadTextfile(Textfile textfile) {
        super();
        this.addOption(textfile);
    }
}

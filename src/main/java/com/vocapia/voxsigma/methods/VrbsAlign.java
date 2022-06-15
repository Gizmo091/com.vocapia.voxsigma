package com.vocapia.voxsigma.methods;

import com.vocapia.voxsigma.AudioMethod;
import com.vocapia.voxsigma.options.Audiofile;
import com.vocapia.voxsigma.options.Textfile;


/**
 * Class to define an vrbs_align method
 */
final public class VrbsAlign extends AudioMethod {

    /**
     * Constructor
     * @param audiofile
     * @param textfile is mandatory to exectute alignment process
     */
    public VrbsAlign(Audiofile audiofile, Textfile textfile) {
        super("vrbs_align", audiofile);
        this.addOption(textfile);
    }
}

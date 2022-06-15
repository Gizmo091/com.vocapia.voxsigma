package com.vocapia.voxsigma;

import com.vocapia.voxsigma.options.Audiofile;
import com.vocapia.voxsigma.options.Rtopt;
import com.vocapia.voxsigma.options.Textfile;
import com.vocapia.voxsigma.options.Vocfile;

/**
 * Super class to define all transcription methods
 */
abstract public class AudioTransMethod extends AudioMethod {

    public AudioTransMethod(String name, Audiofile audioFile) {
        super(name,audioFile);
    }

    /**
     * Set the Real Time option to enabled
     * @return AudioTransMethod
     */
    public AudioTransMethod addRtopt() {
        this.addOption(new Rtopt(1));
        return this;
    }

    /**
     * Set the Textfile option for language model adaptation process
     * @param textFile Textfile to use
     * @return AudioTransMethod
     */
    public AudioTransMethod setTextfile(Textfile textFile) {
        return (AudioTransMethod) this.addOption(textFile);
    }

    /**
     * Set the Vocfile option for language model adaptation process
     * @param vocFile Vocfile to use
     * @return AudioTransMethod
     */
    public AudioTransMethod setVocfile(Vocfile vocFile) {
        return (AudioTransMethod) this.addOption(vocFile);
    }

}
package com.vocapia.voxsigma;

import com.vocapia.voxsigma.options.Audiofile;
import com.vocapia.voxsigma.options.AudiofileAsStream;
import com.vocapia.voxsigma.options.Model;


/**
 * Super class to define all methods using audio file
 */
abstract public class AudioMethod extends Method {

    public AudioMethod(String name, Audiofile audioFile) {
        super(name);
        this.setAudiofile(audioFile);
    }

    /** Audio file to use */
    protected Audiofile audiofile;

    /**
     * Set the audio file to use
     * @param audioFile Audio file to use
     */
    private void setAudiofile(Audiofile audioFile) {
        this.is_stream = (audioFile instanceof AudiofileAsStream);
        this.audiofile = audioFile;
        this.addOption(audioFile);
    }

    /**
     * Get the audio file to use
     * @return Audiofile
     */
    public Audiofile getAudiofile() {
        return this.audiofile;
    }

    /**
     * Set the model to use
     * @param model Model to use
     * @return AudioMethod
     */
    public final AudioMethod setModel(Model model) {
        return (AudioMethod) this.addOption(model);
    }
}

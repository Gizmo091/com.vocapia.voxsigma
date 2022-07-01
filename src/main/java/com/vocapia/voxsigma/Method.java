package com.vocapia.voxsigma;

import com.vocapia.voxsigma.options.Host;

import java.util.ArrayList;
import java.util.List;

abstract public class Method {

    protected String name;
    protected boolean is_stream = false;
    private final List<Option> options_a;

    public Method(String name) {
        this.name = name;
        this.options_a = new ArrayList<Option>();
    }

    protected final Method addOption(Option option) {
        this.options_a.add(option);
        return this;
    }

    public final Method setHost(Host host) {
        return this.addOption(host);
    }



    public final boolean isStream() {
        return this.is_stream;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Option[] getOptions() {
        return this.options_a.toArray(new Option[this.options_a.size()]);
    }

}

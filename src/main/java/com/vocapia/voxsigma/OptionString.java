package com.vocapia.voxsigma;

import java.io.OutputStream;

abstract public class OptionString extends Option{


    public OptionString(String name, String value) {
        super(name, value);
    }

    public long getHttpBodySize() {return 0; };

    public String getMultipartBodyHeader() {return ""; };

    public void renderHttpBody(OutputStream outputStream) {};

}

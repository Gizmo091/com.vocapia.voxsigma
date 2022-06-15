package com.vocapia.voxsigma;

import java.io.OutputStream;

abstract public class OptionFloat extends Option{


    public OptionFloat(String name, float value) {
        super(name,String.valueOf(value));
    }

    public long getHttpBodySize() {return 0; };

    public String getMultipartBodyHeader() {return ""; };

    public void renderHttpBody(OutputStream outputStream) {};

}

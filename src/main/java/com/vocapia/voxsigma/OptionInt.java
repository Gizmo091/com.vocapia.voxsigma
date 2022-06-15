package com.vocapia.voxsigma;

import java.io.OutputStream;

abstract public class OptionInt extends Option{


    public OptionInt(String name, int value) {
        super(name,String.valueOf(value));
    }

    public long getHttpBodySize() {return 0; };

    public String getMultipartBodyHeader() {return ""; };

    public void renderHttpBody(OutputStream outputStream) {};

}

package com.vocapia.voxsigma.options;


import com.vocapia.voxsigma.Option;

/**
 * Master class for all options which is a file or a file content
 */
abstract public class File extends Option {

    public File(String name, String value) {
        super(name, value);
    }

    @Override
    public String getMultipartBodyHeader() {
        return "Content-Disposition: form-data; name=\"" + this.name + "\"; filename=\"" + this.value + "\"\r\n" +
                "Content-Type: application/octet-stream\r\n" +
                "\r\n";
    }

    @Override
    public long getHttpBodySize() {
        return this.getMultipartBodyHeader().getBytes().length + this.getFileSize();
    }

    abstract protected long getFileSize();

    public String renderQueryString()  {
        return "";
    }
}

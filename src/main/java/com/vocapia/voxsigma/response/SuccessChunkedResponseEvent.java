package com.vocapia.voxsigma.response;


import org.w3c.dom.Document;

abstract public class SuccessChunkedResponseEvent {

    protected SuccessChunkedResponse response;
    protected boolean enable_on_line_event = true;
    protected boolean enable_on_chunk_event = true;
    protected boolean enable_on_end_event = true;

    public final void setResponse(SuccessChunkedResponse response) {
        this.response = response;
    }

    public final boolean hasOnLineEvent() {
        return this.enable_on_line_event;
    }

    public final boolean hasOnChunkEvent() {
        return this.enable_on_chunk_event;
    }

    public final boolean hasOnEndEvent() {
        return this.enable_on_end_event;
    }



    abstract public void onEnd(Document document);

    abstract public void onChunk(String chunk);
    
    public void onError(String error) {
        System.err.println("SuccessChunkedResponseEvent.onError : "+ error);
    }

    abstract public void onLine(String line);

}

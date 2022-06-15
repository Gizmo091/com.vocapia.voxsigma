package com.vocapia.voxsigma.response;

import com.vocapia.voxsigma.Response;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Class to manage chunked response from VoxSigma api
 */
public class SuccessChunkedResponse extends Response {

    /**
     * Event class maanger
     */
    protected SuccessChunkedResponseEvent successChunkedResponseEvent;
    /**
     * buffer readr built from socket outputStream
     */
    protected BufferedReader bufferedReader;


    public SuccessChunkedResponse(BufferedReader bufferedReader) {
        this.successChunkedResponseEvent = null;
        this.bufferedReader = bufferedReader;
    }


    public SuccessChunkedResponseEvent getSuccessChunkedResponseEvent() {
        return successChunkedResponseEvent;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public SuccessChunkedResponse setSuccessChunkedResponseEvent(SuccessChunkedResponseEvent successChunkedResponseEvent) {
        this.successChunkedResponseEvent = successChunkedResponseEvent;
        this.successChunkedResponseEvent.setResponse(this);
        return this;
    }

    /**
     * Start receiving the chunked response
     */
    public void receive() {
        if (this.successChunkedResponseEvent == null) {
            throw new RuntimeException("SuccessChunkedResponseEvent cannot be null");
        }
        // Start new thread to read chunked response
        new Thread((new Runnable() {
            protected SuccessChunkedResponse ref;

            public Runnable init(SuccessChunkedResponse ref) {
                this.ref = ref;
                return this;
            }

            protected void closeBufferReader() {
                try {
                    this.ref.bufferedReader.close();
                } catch (IOException e) {
                    this.ref.getSuccessChunkedResponseEvent().onError(e.getMessage());
                }
            }

            @Override
            public void run() {
                SuccessChunkedResponseEvent successChunkedResponseEvent = ref.getSuccessChunkedResponseEvent();
                BufferedReader reader = ref.getBufferedReader();
                StringBuilder document_builder = new StringBuilder();

                try {
                    // loop to read every chunk
                    while_chunk:
                    while (true) {
                        // read the first line of the chunk
                        String line = reader.readLine();
                        // ignore empty line before start of the chunk
                        if (line.isEmpty()) {
                            continue;
                        }
                        // initialize the chunk string builder
                        StringBuilder chunk = new StringBuilder();
                        // initialize the size of the readed content and chunk total size
                        int chunk_current_size = 0;
                        int chunk_total_size = Integer.parseInt(line, 16);
                        // if chunk total size is 0, it means the chunk is empty, so it's the end of the chunked response
                        if (chunk_total_size == 0) {
                            break;
                        }
                        // read the chunk content
                        while ((line = reader.readLine()) != null) {
                            // adding carriage return removed by the readLine method
                            line = line + "\n";
                            chunk_current_size += line.getBytes(StandardCharsets.UTF_8).length;
                            // concat chunk only if needed to call onChunk event
                            if (successChunkedResponseEvent.hasOnChunkEvent()) {
                                chunk.append(line);
                            }
                            // concat document only if needed to call onEnd event
                            if (successChunkedResponseEvent.hasOnEndEvent()) {
                                document_builder.append(line);
                            }

                            // if has on line event call it
                            if (successChunkedResponseEvent.hasOnLineEvent()) {
                                successChunkedResponseEvent.onLine(line);
                            }
                            // if chunk current size is equal to chunk total size, it means the chunk is complete
                            if (chunk_current_size >= chunk_total_size) {
                                // if chunk is not empty and has on chunk event call it
                                if (chunk_current_size > 0 && successChunkedResponseEvent.hasOnChunkEvent()) {
                                    successChunkedResponseEvent.onChunk(chunk.toString());
                                }
                                // return to the while loop to read the next chunk
                                continue while_chunk;
                            }
                        }
                        break;
                    }
                } catch (IOException exception) {
                    successChunkedResponseEvent.onError(exception.getMessage());
                    this.closeBufferReader();
                    return;
                }

                this.closeBufferReader();

                if (successChunkedResponseEvent.hasOnEndEvent()) {
                    try {
                        this.ref.successChunkedResponseEvent.onEnd(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(document_builder.toString()))));
                    } catch (IOException | SAXException | ParserConfigurationException exception) {
                        successChunkedResponseEvent.onError(exception.getMessage());
                    }
                }
            }
        }).init(this)).start();
    }


    public static Response initUsingBufferReader(BufferedReader bufferedReader, String response_type) {
        //noinspection SwitchStatementWithTooFewBranches
        switch (response_type) {
            case Response.TYPE_XML:
                return new SuccessChunkedResponse(bufferedReader);
        }
        return new ErrorResponse(-1, "Unknown response type");
    }

}

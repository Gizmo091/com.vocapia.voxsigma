package com.vocapia.demo.voxsigma;

import android.annotation.SuppressLint;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import com.vocapia.voxsigma.AudioTransMethod;
import com.vocapia.voxsigma.Connection;
import com.vocapia.voxsigma.Response;
import com.vocapia.voxsigma.options.AudiofileAsStream;
import com.vocapia.voxsigma.options.Model;
import com.vocapia.voxsigma.response.ErrorResponse;
import com.vocapia.voxsigma.response.SuccessChunkedResponse;
import com.vocapia.voxsigma.response.SuccessChunkedResponseEvent;
import com.vocapia.voxsigma.response.SuccessResponse;
import org.w3c.dom.Document;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final public class DemoVrbsTransAndroidMicrophoneLive extends Demo {

    protected int second_to_record;

    public DemoVrbsTransAndroidMicrophoneLive(Connection connection, int second_to_record) {
        super(connection);
        this.second_to_record = second_to_record;
    }

    public void run() {
        AudiofileAsStream audioFile = new AudiofileAsStream("audio.mp3") {

            protected int second_to_record;

            public AudiofileAsStream init(int second_to_record) {
                this.second_to_record = second_to_record;
                return this;
            }
            @Override
            protected void sendBytes(BufferedOutputStream outputStream) {
                new Thread((new Runnable() {
                    protected BufferedOutputStream outputStream;
                    protected int second_to_record;

                    public Runnable init(BufferedOutputStream outputStream, int second_to_record) {
                        this.outputStream = outputStream;
                        this.second_to_record = second_to_record;
                        return this;
                    }


                    @Override
                    public void run() {
                        int bufferSize = AudioRecord.getMinBufferSize(8000,
                                AudioFormat.CHANNEL_IN_MONO,
                                AudioFormat.ENCODING_PCM_16BIT);


                        @SuppressLint("MissingPermission") AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.DEFAULT,
                                8000,
                                AudioFormat.CHANNEL_IN_MONO,
                                AudioFormat.ENCODING_PCM_16BIT,
                                bufferSize);

                        //int[] ucDataBlock_U = {82, 73, 70, 70, 36, 0, 0, 0, 87, 65, 86, 69, 102, 109, 116, 32, 16, 0, 0, 0, 1, 0, 1, 0, 64, 31, 0, 0, 128, 62, 0, 0, 2, 0, 16, 0, 100, 97, 116, 97, 0, 0, 0, 0};
                        int[] ucDataBlock_U = {1179011410, 36, 1163280727, 544501094, 16, 65537, 8000, 16000, 1048578, 1635017060, 0};
                        ByteBuffer bb = ByteBuffer.allocate(ucDataBlock_U.length * 4);
                        bb.order(ByteOrder.LITTLE_ENDIAN);
                        for (int i : ucDataBlock_U) {
                            bb.putInt(i);
                        }
                        try {
                            //System.out.println((Integer.toHexString(bb.array().length)+"\r\n"));
                            this.outputStream.write((Integer.toHexString(bb.array().length) + "\r\n").getBytes());
                            //System.out.println(new String(bb.array()));
                            this.outputStream.write(bb.array());
                            //System.out.println("\r\n");
                            this.outputStream.write("\r\n".getBytes());
                            //System.out.println(">>>> Flush >>>>>");
                            this.outputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        audioRecord.startRecording();

//                            short[] audioBuffer = new short[8096];
                        short[] audioBuffer = new short[1600];
                        int chunk_count = this.second_to_record * 5;
                        while (chunk_count-- > 0) {
                            // Faire une boucle la dessus tant que vous souhaitez capturer
                            int numberOfShort = audioRecord.read(audioBuffer, 0, audioBuffer.length);
                            ByteBuffer bfSTT = ByteBuffer.allocate(numberOfShort * 2);
                            bfSTT.order(ByteOrder.LITTLE_ENDIAN);
                            for (int z = 0; z < numberOfShort; z++) {
                                //audioData[i] = (short)Math.min((int)(audioData[i] * gain), (int)Short.MAX_VALUE);
                                bfSTT.putShort(audioBuffer[z]);
                            }
                            try {
                                //OutputStreamWriter osw = new OutputStreamWriter(this.outputStream.output);
                                //osw.write();
                                //osw.flush();
                                //System.out.println((Integer.toHexString(bfSTT.array().length)+"\r\n"));
                                this.outputStream.write((Integer.toHexString(bfSTT.array().length) + "\r\n").getBytes());
                                //System.out.println(new String(bfSTT.array()));
                                this.outputStream.write(bfSTT.array());
                                //System.out.println("\r\n");
                                this.outputStream.write("\r\n".getBytes());
                                System.out.println(">>>> Flush >>>>>");
                                this.outputStream.flush();
                                //this.outputStream.write(bfSTT.array());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            //System.out.println("0\r\n");
                            this.outputStream.write("0\r\n".getBytes());
                            //System.out.println("\r\n");
                            this.outputStream.write("\r\n".getBytes());
                            //System.out.println(">>>> Flush >>>>>");
                            this.outputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        audioRecord.stop();
                        audioRecord.release();

                    }
                }).init(outputStream,this.second_to_record)).start();
            }
        }.init(this.second_to_record);


        Response response = null;
        try {
            response = this.connection.execute(
                    ((AudioTransMethod) (new com.vocapia.voxsigma.methods.VrbsTrans(audioFile))
                            .setModel(new Model("fre")))
                            .addRtopt()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (response.isNotError()) {
            Log.e("LOG", "Success");
            if (response.isChunked()) {
                ((SuccessChunkedResponse) response).setSuccessChunkedResponseEvent((new SuccessChunkedResponseEvent() {

                    public SuccessChunkedResponseEvent init() {
                        enable_on_end_event = false;
                        enable_on_chunk_event = false;
                        return this;
                    }

                    @Override
                    public void onError(String error) {
                        Log.e("ONERROR", error);
                    }

                    @Override
                    public void onEnd(Document document) {
                        Log.e("ONEND", document.toString());
                    }

                    @Override
                    public void onChunk(String chunk) {
                        Log.e("ONCHUNK", chunk);
                    }

                    @Override
                    public void onLine(String line) {
                        Log.e("ONLINE", line);
                    }
                }).init()).receive();

                audioFile.send();
            } else {
                Log.d("LOG", ((SuccessResponse) response).getDocument().toString());
            }
        } else {
            Log.e("LOG", response.toString());
            Log.e("LOG", ((ErrorResponse) response).getMessage());
        }


    }


}

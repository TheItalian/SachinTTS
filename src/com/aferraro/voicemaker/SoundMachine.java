package com.aferraro.voicemaker;


import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.SequenceInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundMachine {

	AudioInputStream appendedFiles;
    //input File list will be received from FileManager
	//converts File list to AudioInputStream list
    public List<AudioInputStream> convertFileListToAudioInputStreamList(List<File> soundFiles){
    	List<AudioInputStream> audioList = new ArrayList<AudioInputStream>();
    	AudioInputStream buffer;
    	File file;
    	for(int i = 0; i<soundFiles.size(); i++){
            try {
            	file = soundFiles.get(i);
                buffer = AudioSystem.getAudioInputStream(file);
                audioList.add(buffer);
            } catch (Exception e){
                e.printStackTrace();
                System.exit(1);
            }
		}
    	return audioList;
    }
    
    public AudioInputStream append(AudioInputStream audio1, AudioInputStream audio2) {
        try {
            AudioInputStream clip1 = audio1;
            AudioInputStream clip2 = audio2;

            appendedFiles = new AudioInputStream(
                                new SequenceInputStream(clip1, clip2),     
                                clip1.getFormat(), 
                                clip1.getFrameLength() + clip2.getFrameLength());
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return appendedFiles;
    }
    public AudioInputStream appendAll(List<AudioInputStream> audio) { //add if statements to allow for extremely small inputs
    	AudioInputStream stream = this.append(audio.get(0), audio.get(1));
    	for(int i = 2; i<audio.size(); i++) {
    		stream = this.append(stream, audio.get(i));
    	}
    	return stream;
    }
    //add method to play audioinputstream
    private final int BUFFER_SIZE = 128000;
    private File soundFile;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    
    public void playSound(String filename){

        String strFilename = filename;

        try {
            soundFile = new File(strFilename);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
    }

}
    
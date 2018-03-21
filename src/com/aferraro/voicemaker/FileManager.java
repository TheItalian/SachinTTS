package com.aferraro.voicemaker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class FileManager {
	
    public List<File> createSoundList(List<String> phonemes){
    	List<File> fileList = new ArrayList<File>();
    	File buffer;
    	String fileName;
    	String format = "wav";
    	for(int i = 0; i<phonemes.size(); i++){
    		if(phonemes.get(i).contains(" ")) {//if there should be a pause
    			fileName = "silent";
    			buffer = this.get(fileName, format);
    			fileList.add(buffer);
    			continue;
    	//List<File> name = FileManager.createSoundList(phonemes);		
    		}
    		else { //if there should be a phoneme sound
	    		fileName = phonemes.get(i);
				buffer = this.get(fileName, format);
				fileList.add(buffer);
				continue;
    		}
		}
    	return fileList;
    }
	
	public File get(String fileName, String format) {
		return new File("audio/"+fileName+"."+format );
	}
	
	public File get(String fullFileName) {
		return new File("audio/"+fullFileName);
	}
	
	public void saveAudioInputStreamToDisk(AudioInputStream audio){
        try {
			AudioSystem.write(
			        audio
			       ,AudioFileFormat.Type.WAVE
			       ,new File("audio/finalMessage.wav"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	


}

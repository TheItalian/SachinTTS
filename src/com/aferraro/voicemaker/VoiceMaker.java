package com.aferraro.voicemaker;

import java.io.File;
import java.util.List;

import javax.sound.sampled.AudioInputStream;

public class VoiceMaker {
	public void play(String s) {
		WordMachine wordmachine = new WordMachine();
		SoundMachine soundmachine = new SoundMachine();
		FileManager filemanager = new FileManager();
		List<String> phonemes = wordmachine.splitSentenceIntoPhonemes(s);
		List<File> phonemeFileList = filemanager.createSoundList(phonemes);
		List<AudioInputStream> phonemeSoundList = soundmachine.convertFileListToAudioInputStreamList(phonemeFileList);
		AudioInputStream finalMessage = soundmachine.appendAll(phonemeSoundList);
		filemanager.saveAudioInputStreamToDisk(finalMessage);
		soundmachine.playSound("audio/finalMessage.wav");
	}
}

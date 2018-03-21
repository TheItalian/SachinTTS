package com.aferraro.voicemaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.ListUtils;

import com.tpl.asp.Phoneme;
	//A    "A_b_c_ d_e_f_  g_h_i_"
	//B    <“A_b_c_ d_e_f_”,  “g_h_i_”>
	//C    <“A_b_c_”, “d_e_f_”, “g_h_i”>
	//D    <"A", "b", "c", "d", "e", "f", "g", "h", "i">


public class WordMachine {
	
	private List<String> splitSentenceIntoPhrases(String a){
	    return Arrays.asList(a.split("((?<=  )|(?=  ))")); //b
	}

	private List<String> splitPhrasesIntoWords(List<String> b){
		List<String> base = new ArrayList<String>();
		List<String> buffer = new ArrayList<String>();

		for(int i = b.size(); i>0; i--){
			buffer =  Arrays.asList(b.get(i-1).split("((?<= )|(?= ))"));
			base = ListUtils.union(buffer,base);
		}
		return base; //c

	}

	private List<String> splitWordsIntoPhonemes(List<String> c){
		List<String> base = new ArrayList<String>();
		List<String> buffer = new ArrayList<String>();
		
		
		for(int i = c.size(); i>0; i--){
			buffer =  Arrays.asList(c.get(i-1).split("_"));
			base = ListUtils.union(buffer, base);
		}
		return base; //d

	}
	
	public List<String> splitSentenceIntoPhonemes(String s){
		List<String> arr = new ArrayList<String>();
		
		s = this.getPhonemeString(s);
		arr = this.splitSentenceIntoPhrases(s);
		arr = this.splitPhrasesIntoWords(arr);
		arr = this.splitWordsIntoPhonemes(arr);
		return arr;
	}
	
	public String getPhonemeString(String s) {
		s = Phoneme.toPhoneme(s);
		s = s.replace("_ ", " ");
		return s;
	}



	
	
}

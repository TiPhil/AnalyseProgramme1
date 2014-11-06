package program;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LexicalAnalyser {
	
	private FileInputStream fileInput;
	
	public LexicalAnalyser() {
		try {
			fileInput = new FileInputStream("src/program/file.l");
		} catch (Exception e) { }
	}
	
	public String getNextToken() {
		String word = "";
		int r;
		
		try {
			while ((r = fileInput.read()) != -1) {
				if (((char) r != ' ') || ((char) r != '\r')) {
					word += (char) r;
					break;
				}
			}
			
			while ((r = fileInput.read()) != -1) {
				if ((char) r != ' ') {
					word += (char) r;
				}
				else {
					return word;
				}
			}
			
		} catch (Exception e) {
		
		}
		
		return word;
	}
	
	public void closeFile() {
		try {
			fileInput.close();
		} catch (IOException e) { }
	}
	
	/*private void checkIdentity(String word) {
		if (!word.isEmpty()) {
			if (existReservedKeyWords(word)) {
				System.out.println("MOT RESERVE : " + word);
			}
			else if (isNumber(word)){
				System.out.println("NOMBRE : " + word);
			}
			else if (isIdentificator(word)){
				System.out.println("IDENTIFICATEUR : " + word);
			}
		}
	}
	
	private boolean isIdentificator(String word) {
	    int compteur = 0;
	    boolean valid = false;
		
		for(char ch: word.toCharArray()) {
			if (compteur == 0) {
				if (Character.isAlphabetic(ch))
					valid = true;
			}
			else {
				if (Character.isAlphabetic(ch) || Character.isDigit(ch))
					valid = true;
				else
					valid = false;
			} 
			compteur++;
	    }
		if (word.length() > 8)
			valid = false;
		return valid;
	}
	
	private boolean isNumber(String word) {
		for(char ch: word.toCharArray()) {
			if (!Character.isDigit(ch))
				return false;
	    }
		return true;	
	}
	
	private boolean existReservedKeyWords(String word) {
		
		for (String element : ReservedKeyWords.getInstance().getKeyWordsTable()) {
			if (element.equals(word))
				return true;
		}
		return false;
	}*/
}

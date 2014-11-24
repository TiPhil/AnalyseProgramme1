package program;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LexicalAnalyser {
	
	private FileInputStream fileInput;
	private Token buffer;
	private Token temp;
	
	// Constructeur
	public LexicalAnalyser() {
		try {
			fileInput = new FileInputStream("src/program/file2.l");
		} catch (Exception e) { }
	}
	
	// Recupere le prochain token dans le fichier en lecture
	public Token getNextToken() {
		String word = "";
		temp = new Token(TokenType.OTHER, "");	
		Token token = new Token(TokenType.OTHER, "");	
		int counter = 0;
		int r;
		char ch;
		
		if (buffer != null) {			
			temp.setTokenType(buffer.getTokenType());
			temp.setValue(buffer.getValue());
			buffer = null;		
			return temp;
		}
		
		try {
			while ((r = fileInput.read()) != -1) {
				ch = (char)r;
				counter++;
				
				// Si nous evaluons le premier caractere du token,
				// certaines conditions s'appliquent...
				if(counter == 1) {					
					if(Character.isLetter(ch)) {
						word += (char) r;
						token = new Token(TokenType.IDENTIFICATOR, word);
					}
					else if(Character.isDigit(ch)) {
						word += (char) r;
						token = new Token(TokenType.NUMBER, word);
					}
					else if(Character.isWhitespace(ch)) { // DOES THIS WORKS?
						counter--;
					}
					else if(ch == '+') {
						word += (char) r;
						token = new Token(TokenType.ADDITION, word);
						return token;
					}
					else if(ch == '-') {
						word += (char) r;
						token = new Token(TokenType.MINUS, word);
						return token;
					}
					else if(ch == '*') {
						word += (char) r;
						token = new Token(TokenType.MULTIPLIER, word);
						return token;
					}
					else if(ch == '/') {
						word += (char) r;
						token = new Token(TokenType.DIVISION, word);
						return token;
					}
					else if(ch == '(') {
						word += (char) r;
						token = new Token(TokenType.PARENTHESE_LEFT, word);
						return token;
					}
					else if(ch == ')') {
						word += (char) r;
						token = new Token(TokenType.PARENTHESE_RIGHT, word);
						return token;
					}
					else if(ch == '=') {
						word += (char) r;
						token = new Token(TokenType.EQUAL, word);
						return token;
					}
					else if(ch == ':') {
						word += (char) r;
						token = new Token(TokenType.COLON, word);
						return token;
					}
					else if(ch == ';') {
						word += (char) r;
						token = new Token(TokenType.SEMICOLON, word);
						return token;
					}
				}
				// Si nous evaluons un caractere plus grand que 1,
				// d'autres conditions s'appliquent...
				else {
					if(Character.isLetter(ch)) {
						word += (char) r;
												
						if(token.getTokenType() == TokenType.IDENTIFICATOR || token.getTokenType() == TokenType.RESERVEDKEYWORD) {
							if(ReservedKeyWords.getInstance().isReservedKeyWords(word)) {
								token = new Token(TokenType.RESERVEDKEYWORD, word);
							}
							else {
								token = new Token(TokenType.IDENTIFICATOR, word);
							}
						}
						else {
							token = new Token(TokenType.OTHER, word);
						}
					}
					else if(Character.isDigit(ch)) {
						word += (char) r;
						
						if(token.getTokenType() == TokenType.IDENTIFICATOR || token.getTokenType() == TokenType.RESERVEDKEYWORD) {
							token = new Token(TokenType.IDENTIFICATOR, word);
						}
						else {
							token = new Token(TokenType.NUMBER, word);
						}
					}
					
					// TO CONTINU
					else if(Character.isWhitespace(ch)) {
						return token;
					}
					else if(ch == '_') {
						word += (char) r;
						token = new Token(TokenType.IDENTIFICATOR, word);
					}
					else if(ch == '+') {
						buffer = new Token(TokenType.ADDITION, Character.toString(ch));
						return token;
					}
					else if(ch == '-') {
						buffer = new Token(TokenType.MINUS, Character.toString(ch));
						return token;
					}
					else if(ch == '*') {
						buffer = new Token(TokenType.MULTIPLIER, Character.toString(ch));
						return token;
					}
					else if(ch == '/') {
						buffer = new Token(TokenType.DIVISION, Character.toString(ch));
						return token;
					}
					else if(ch == '(') {
						buffer = new Token(TokenType.PARENTHESE_LEFT, Character.toString(ch));
						return token;
					}
					else if(ch == ')') {
						buffer = new Token(TokenType.PARENTHESE_RIGHT, Character.toString(ch));
						return token;
					}
					else if(ch == '=') {
						buffer = new Token(TokenType.EQUAL, Character.toString(ch));
						return token;
					}
					else if(ch == ':') {
						buffer = new Token(TokenType.COLON, Character.toString(ch));
						return token;
					}
					else if(ch == ';') {
						buffer = new Token(TokenType.SEMICOLON, Character.toString(ch));
						return token;
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return token;
	}
	
	// Fermer le fichier
	public void closeFile() {
		try {
			fileInput.close();
		} catch (IOException e) { }
	}
}

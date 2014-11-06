package program;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LexicalAnalyser analyser = new LexicalAnalyser();
		
		analyser.readFile("src/program/file.l");
		

	}

}

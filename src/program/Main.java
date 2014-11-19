package program;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {

		SyntaxAnalyser syntaxAnalyser = new SyntaxAnalyser();
		
		
		if (syntaxAnalyser.procedure()) {
			System.out.println("Aucune erreur dans le programme n'a ete detecte!");
		}
		else {
			System.out.println("Il y a une ou plusieurs erreurs dans le programme!");
		}
	}

}

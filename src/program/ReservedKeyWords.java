package program;

import java.util.ArrayList;
import java.util.List;

public class ReservedKeyWords {
	
	private static ReservedKeyWords instance = null;
	private ArrayList<String> keyWordsTable = new ArrayList<String>();
	
	// Constructeur
	// Initie les mots cles reserves
	private ReservedKeyWords() {
		keyWordsTable.add("Procedure");
		keyWordsTable.add("Fin_Procedure");
		keyWordsTable.add("declare");
		keyWordsTable.add("entier");
		keyWordsTable.add("reel");
	}
	
	// Retourne le singleton
	public static ReservedKeyWords getInstance() {
		if(instance == null) {
			instance = new ReservedKeyWords();
		}
		return instance;
	}
	
	// Verifie si la String en parametre est un mot cle reserve
	public boolean isReservedKeyWords(String word) {
		for (String element : keyWordsTable) {
			if (element.equals(word))
				return true;
		}
		return false;
	}
}

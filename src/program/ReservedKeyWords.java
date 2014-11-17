package program;

import java.util.ArrayList;
import java.util.List;

public class ReservedKeyWords {
	
	private static ReservedKeyWords instance = null;
	private ArrayList<String> keyWordsTable = new ArrayList<String>();
	
	private ReservedKeyWords() {
		keyWordsTable.add("Procedure");
		keyWordsTable.add("Fin_Procedure");
		keyWordsTable.add("declare");
		keyWordsTable.add("entier");
		keyWordsTable.add("reel");
		keyWordsTable.add("+");
		keyWordsTable.add("-");
		keyWordsTable.add(":");
		keyWordsTable.add(";");
		keyWordsTable.add("*");
		keyWordsTable.add("/");
	}
	
	public static ReservedKeyWords getInstance() {
		if(instance == null) {
			instance = new ReservedKeyWords();
		}
		return instance;
	}
	
	// is this used? to delete...
	public List<String> getKeyWordsTable() {
		return keyWordsTable;
	}
	
	// is this used? to delete...
	public boolean isReservedKeyWords(String word) {
		for (String element : keyWordsTable) {
			if (element.equals(word))
				return true;
		}
		return false;
	}
}

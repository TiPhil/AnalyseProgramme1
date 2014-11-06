package program;

import java.util.ArrayList;
import java.util.List;

public class ReservedKeyWords {
	
	private ArrayList<String> keyWordsTable = new ArrayList<String>();
	private static ReservedKeyWords instance = null;
	
	private ReservedKeyWords() {
		keyWordsTable.add("Procedure");
		keyWordsTable.add("Fin_Procedure");
		keyWordsTable.add("declare");
		keyWordsTable.add("entier");
		keyWordsTable.add("réel");
		keyWordsTable.add("+");
		keyWordsTable.add("-");
		keyWordsTable.add(":");
		keyWordsTable.add(";");
		keyWordsTable.add("*");
		keyWordsTable.add("/");
	}
	
	public static ReservedKeyWords getInstance() {
		if (instance == null) {
			instance = new ReservedKeyWords();	
		}
		return instance;
	}

	public List<String> getKeyWordsTable() {
		return keyWordsTable;
	}
	
}

package program;

public enum TokenType {
	IDENTIFICATOR, NUMBER, MULTIPLIER, DIVISION, ADDITION, MINUS, PARENTHESE_LEFT, PARENTHESE_RIGHT, 
	COLON, SEMICOLON, EQUAL, AFFECTATION,
	
	// Mots cles reserves
	RESERVEDKEYWORD,
	
	// Dans les cas d'erreurs et autre...
	OTHER
}

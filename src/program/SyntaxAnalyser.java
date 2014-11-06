package program;

public class SyntaxAnalyser {
	
	private LexicalAnalyser lexicalAnalyser;
	private String token;
	
	public SyntaxAnalyser() {
		lexicalAnalyser = new LexicalAnalyser();
		Procedure();
	}
	
	public void Procedure() {
		token = lexicalAnalyser.getNextToken();
		if (token ==  "Procedure") {
			token = lexicalAnalyser.getNextToken();
			Identificateur();
			Declarations();
			Instructions_affectation();
			
			if (token == code_fin_procedure) {
				read();
				Identificateur();
			}
			else {
				System.out.println("ERREUR : Le programme ne se termine pas par le mot réservé FIN_PROCEDURE.");
			}
		}
		else {
			System.out.println("ERREUR : Le programme ne débute pas par le mot réservé PROCEDURE.");
		}
	}

	public void Declarations() {
		
	}
	
	public void Instructions_affectation() {
		
	}
	
	public void Identificateur() {
	    int compteur = 0;
	    boolean valid = false;
		
		for(char ch: token.toCharArray()) {
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
		if (token.length() > 8)
			valid = false;
		return valid;
	}
	
}

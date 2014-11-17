package program;

public class SyntaxAnalyser {
	
	private LexicalAnalyser lexicalAnalyser;
	private Token token;
	private ReservedKeyWords reservedKeyWords;
	
	public SyntaxAnalyser() {
		lexicalAnalyser = new LexicalAnalyser();
		reservedKeyWords = new ReservedKeyWords();
	}
	
	public boolean procedure() {
		getNextToken();
		String nomProcedure = "";
		
		if (token.equals("Procedure")) { // <== à modifier
			System.out.println("Mot réservé : " + token);
			getNextToken();
			nomProcedure = token;
			
			if(!identificateur()) {
				return false;
			}
			
			if(!declarations()) {
				return false;
			}

			
			//instructions_affectation(); // TODO
			
			if (token.equals("Fin_Procedure")) { // <== à modifier
				System.out.println("Mot réservé : " + token);
				getNextToken();
				if(nomProcedure.equals(token)) {
					identificateur();
					System.out.println("SUCCÈS : Aucune erreur n'a été détectée.");
					return true;
				}
				else {
					printError("Les identificateurs de début et de fin de la procédure ne sont pas identique.");
					return false;
				}
			}
			else {
				printError("Le programme ne se termine pas par le mot réservé FIN_PROCEDURE.");
				return false;
			}
		}
		else {
			printError("Le programme ne débute pas par le mot réservé PROCEDURE.");
			return false;
		}
	}

	public boolean declarations() {
		
		boolean valid = false;
		int counter = 0;
		
		do {
			valid = declaration();
			if(valid)
				counter++;
		} while(valid);
		
		if(counter >= 1)
			return true;
		else {
			printError("La déclaration de variable est erronée.");
			return false;
		}
	}
	
	public boolean declaration() {
		if(token.equals("declare")) {
			System.out.println("Mot réservé : " + token);
			getNextToken();
			if(variable()) {
				if(token.equals(":")) {
					System.out.println("Mot réservé : " + token);
					getNextToken();
					if(type()) {
						if(token.equals(";")) {
							System.out.println("Mot réservé : " + token);
							getNextToken();
							return true;
						}
						else {
							return false;
						}
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public boolean variable() {
		return identificateur();
	}
	
	public boolean type() {
		if (token.equals("reel") || token.equals("entier")) {
			System.out.println("Mot réservé : " + token);
			getNextToken();
			return true;
		}
		else {
			printError("Le type doit être REEL ou ENTIER.");
			return false;
		}
	}
	
	public boolean instructions_affectation() {
		return false;
	}
	
	public boolean instruction_affectation() {
		return false;
	}
	
	public boolean expression_arithmetique() {
		return false;
	}
	
	public boolean terme() {
		return false;
	}
	
	public boolean facteur() {
		return false;
	}

	public boolean identificateur() {
	    int compteur = 0;
	    boolean valid = false;
		for(char ch: token.toCharArray()) {
			if (compteur == 0) {
				if (Character.isAlphabetic(ch))
					valid = true;
				else {
					printError("Le premier caractères de l'identificateur n'est pas une lettre.");
					return false;
				}
			}
			else {
				if (Character.isAlphabetic(ch) || Character.isDigit(ch))
					valid = true;
				else {
					printError("L'identificateur ne contient pas uniquement des caractères alphanumériques.");
					return false;
				}
			} 
			compteur++;
	    }

		if (token.length() > 8) {
			printError("L'identificateur dépasse 8 caractères.");
			return false;
		}
		else if (reservedKeyWords.isReservedKeyWords(token)){
			printError("L'identificateur ne peut pas être un mot réservé.");
			return false;
		}
		else {
			System.out.println("Identificateur : " + token);
			getNextToken();
			return valid;
		}
	}
	
	private void printError(String error) {
		System.out.println("TOKEN ERRONÉ : " + token);
		System.out.println("ERREUR : " + error);
	}
	
	private void getNextToken() {
		token = lexicalAnalyser.getNextToken();
	}
}

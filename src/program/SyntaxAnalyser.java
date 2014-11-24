package program;

import java.util.ArrayList;
import java.util.List;

public class SyntaxAnalyser {
	
	private LexicalAnalyser lexicalAnalyser;
	private Token token;
	private List<Variable> variableList = new ArrayList<Variable>();
	boolean isEntier = false;
	
	// Constructeur
	public SyntaxAnalyser() {
		lexicalAnalyser = new LexicalAnalyser();
	}
	
	// Fonction principale de l'analyseur syntaxique
	// Returne vrai si l'analyse c'est effectue avec success
	// Aussi non, retourne faux
	public boolean procedure() {
		Token identificateurProcedure = null;
		
		getNextToken();

		if (token.getValue().equals("Procedure")) {
			getNextToken();
			identificateurProcedure = token;
			if(identificateur()) {
				if(declarations()) {
					if (instructions_affectation()) {
						if (token.getValue().equals("Fin_Procedure")) {
							getNextToken();
							if (token.getValue().equals(identificateurProcedure.getValue())) {
								return true;
							}
							else {
								printError("L'identificateur de Fin_Procedure ne correspond pas a l'identificateur de Procedure.");
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
		return false;
	}

	// Verifie le non-terminal "declarations"
	public boolean declarations() {
		
		boolean valid = false;
		int counter = 0;
		
		do {
			valid = declaration();
			if(token.getTokenType() == TokenType.IDENTIFICATOR) {
				break;
			}
			if(valid)
				counter++;
			else
				return false;
			
		} while(valid);
		
		if(counter >= 1)
			return true;
		else {
			printError("La déclaration de variable est erronée.");
			return false;
		}
	}

	// Verifie le non-terminal "declaration"
	public boolean declaration() {
		String valueTemp;
		String typeTemp;
		
		if(token.getValue().equals("declare")) {
			getNextToken();
			
			valueTemp = token.getValue();
			if(variable()) {
				if(token.getTokenType() == TokenType.COLON) {
					getNextToken();
					
					typeTemp = token.getValue();
					if(type()) {
						if(token.getTokenType() == TokenType.SEMICOLON) {
							variableList.add(new Variable(valueTemp, typeTemp));
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
			if(token.getTokenType() == TokenType.IDENTIFICATOR) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	// Verifie le non-terminal "variable"
	public boolean variable() {
		return identificateur();
	}
	
	// Verifie le terminal "type"
	public boolean type() {
		if (token.getValue().equals("reel") || token.getValue().equals("entier")) {
			getNextToken();
			return true;
		}
		else {
			printError("Le type doit être REEL ou ENTIER.");
			return false;
		}
	}
	
	// Verifie le non-terminal "instructions_affectation"
	public boolean instructions_affectation() {
		boolean valid = false;
		int counter = 0;
		
		do {
			if (counter > 0) {
				if (token.getTokenType() == TokenType.SEMICOLON) {
					getNextToken();
					
					valid = instruction_affectation();
				}
				else {
					printError("Il manque un point-virgule entre les instructions d'affectation");
					return false;
				}
			}
			else {
				valid = instruction_affectation();
			}
			
			if(valid) {
				if(token.getTokenType() != TokenType.SEMICOLON) {
					counter++;
					break;
				}
				counter++;
			}
			else
				return false;
			
		} while(valid);
		
		if(counter >= 1)
			return true;
		else {
			printError("La déclaration de variable est erronée.");
			return false;
		}
	}
	
	// Verifie le non-terminal "instruction_affectation"
	public boolean instruction_affectation() {
		
		if (isVariable(token.getValue())) {
			if (isEntier(token.getValue()))
				isEntier = true;
			
			if (variable()) {
				if (token.getTokenType() == TokenType.EQUAL) {
					getNextToken();
					if (expression_arithmetique()) {
						
						return true;
					}
					else {
						//printError("L'instruction ne se termine pas par une expression arithmetique");
						return false;
					}
				}
				else {
					//printError("L'instruction ne comporte pas de signe d'egalite");
					return false;
				}
			}
			else {
				//printError("L'instruction ne debute pas par une variable");
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	// Verifie le non-terminal "expression_arithmetique"
	public boolean expression_arithmetique() {
		if (terme()) {
			while (token.getTokenType() == TokenType.ADDITION || token.getTokenType() == TokenType.MINUS) {
				getNextToken();
				
				if (!terme()) {
					//printError("Il manque un terme a l'expression arithmetique");
					return false;
				}
			}
			
			return true;
		}
		else {
			//printError("L'expression arithmetique ne debute pas par un terme");
			return false;
		}
	}

	// Verifie le non-terminal "terme"
	public boolean terme() {
		if (facteur()) {
			while (token.getTokenType() == TokenType.MULTIPLIER || token.getTokenType() == TokenType.DIVISION) {
				getNextToken();
				
				if (!facteur()) {
					//printError("Il manque un facteur au terme");
					return false;
				}
			}
			
			return true;
		}
		else {
			//printError("Le terme ne debute pas par un facteur");
			return false;
		}
	}

	// Verifie le non-terminal "facteur"
	public boolean facteur() {
		if (isVariable(token.getValue())) {
			if (isEntier) {
				if (isEntier(token.getValue())) {
					getNextToken();
					return true;
				}
				else {
					return false;
				}
			}
			else {
				getNextToken();
				return true;
			}
		}
		else if (token.getTokenType() == TokenType.NUMBER) {
			getNextToken();
			return true;
		}
		else if (token.getTokenType() == TokenType.PARENTHESE_LEFT) {
			getNextToken();
			
			if (expression_arithmetique()) {
				if (token.getTokenType() == TokenType.PARENTHESE_RIGHT) {
					getNextToken();
					return true;
				}
				else {
					printError("Il manque une parenthese droite a l'expression");
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

	// Verifie le terminal "identificateur"
	public boolean identificateur() {
	    if(token.getTokenType() == TokenType.IDENTIFICATOR) {
			if (token.getValue().length() <= 8) {
				getNextToken();
				return true;
			}
			else {
				printError("L'identificateur dépasse 8 caractères.");
				return false;
			}
		}
	    else {
	    		return false;
	    }
	}
	
	// Affiche le token errone et le type d'erreur (String)
	private void printError(String error) {
		System.out.println("*TOKEN ERRONÉ*");
		System.out.println("Token type : " + token.getTokenType());
		System.out.println("Token value : " + token.getValue());
		System.out.println("ERREUR : " + error);
		System.out.println();
	}
	
	// Appelle l'analyseur lexical pour recuperer le prochain token
	private void getNextToken() {
		token = lexicalAnalyser.getNextToken();
		printToken();
	}
	
	// Affiche le token a la console
	private void printToken() {
		System.out.println("Token type : " + token.getTokenType());
		System.out.println("Token value : " + token.getValue());
		System.out.println();
	}
	
	// Verifie si la String en parametre fait parti de la liste des variables
	// Permet de verifier si un variable utilise existe ou non
	private boolean isVariable(String var) {
		for (Variable element : variableList) {
			if (element.getValue().equals(var)) {
				return true;
			}
		}
		return false;
	}
	
	// Verifie si la String en parametre est en entier ou non
	// Permet de verifier si l'on peut affecter le resultat d'une operation
	// a une variable
	private boolean isEntier(String var) {
		for (Variable element : variableList) {
			if (element.getValue().equals(var)) {
				if (element.getType().equals("entier"))
					return true;
				else
					return false;
			}
		}
		return false;
	}
}

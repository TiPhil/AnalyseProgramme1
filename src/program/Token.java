package program;

public class Token {

	private TokenType tokenType;
	private String value;
	
	public Token(TokenType tokenType, String value) {
		this.tokenType = tokenType;
		this.value = value;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

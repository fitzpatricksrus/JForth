import java.util.Scanner;

public class Terminal {
	private static String operator = "([\\+-<>\\,\\.=!@#$%^\\&\\*\\(\\)\\[\\]\\{\\}]+)";
	private static String keyword = "(\\w+\\:)";

	public enum TokenType {
		NUMBER,
		OPERATOR,
		KEWORD,
	}

	public static class Token {
		public TokenType type;
		public String value;
		public int intValue;

		public Token(TokenType type, String value) {
			this.type = type;
			this.value= value;
		}
		public Token(TokenType type, int value) {
			this.type = type;
			this.intValue= value;
		}
		public String toString() {
			return "Token("+type+", "+value+", "+intValue+")";
		}
	}

	Scanner inputScanner = new Scanner(System.in);
	public Terminal() {
	}

	public Token nextToken() {
		if (inputScanner.hasNextInt()) {
			return new Token(TokenType.NUMBER, inputScanner.nextInt());
		} else if (inputScanner.hasNext(operator)) {
			return new Token(TokenType.OPERATOR, inputScanner.next(operator));
		} else {
			return new Token(TokenType.KEWORD, inputScanner.next());
		}
	}

	public void print(String message) {
		System.out.println(message);
	}
}


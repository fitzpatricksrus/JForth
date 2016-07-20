package us.cownet.jforth;

import java.util.Scanner;

public class Terminal extends Word {
	private static String operator = "([\\+\\-\\<\\>\\,\\.\\=\\!\\@\\#\\$%\\^\\&\\*\\(\\)\\[\\]\\{\\}]+)";
	private static String keyword = "(\\w+\\:)";
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

	public enum TokenType {
		UNKNOWN(0),
		NUMBER(1),
		OPERATOR(2),
		KEWORD(3);

		public int value;

		TokenType(int value) {
			this.value = value;
		}

		;
	}

	public static class Token {
		public TokenType type;
		public String value;
		public int intValue;

		public Token(TokenType type, String value) {
			this.type = type;
			this.value = value;
		}

		public Token(TokenType type, int value) {
			this.type = type;
			this.intValue = value;
		}

		public String toString() {
			return "Token(" + type + ", " + value + ", " + intValue + ")";
		}
	}

	public static void nextToken(ExecutionContext context) {
		// ( terminal -- value type )
		Token t = context.getTerminal().nextToken();
		switch (t.type) {
			case KEWORD:
			case OPERATOR:
				context.push(t.value);
				break;
			case NUMBER:
				context.push(t.intValue);
				break;
			default:
				context.push((Word) null);
				break;
		}
		context.push(t.type.value);
	}

	public static void printString(ExecutionContext context) {
		context.getTerminal().print(context.pop().toString());
	}
}


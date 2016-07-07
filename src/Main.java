import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*

 root variable: "inputBuffer" is: ""
 -> root "inputBuffer" "" variable:is:

 inputBuffer variable: "length' is: 0
 -> inputBuffer "length" 0 variable:is:

 root variable: "outerInterp"
 -> root "outerInterp" variable:

 outerInterp variable: "isWhiteSpace:at:" is: { | buffer pos | (buffer at: pos) == ' ' }
 -> outerInterp "isWhiteSpace:at:" { 0 paramVal 1 paramVal at: ' ' == } variable:is:

 outerInterp variable: "spaceCountOf:" is: {

 buffer |


 }
 -> outerInterp "isWhiteSpace:at:" { 0 paramVal 1 paramVal at: ' ' == } variable:is:

 outerInterp isWhatSpace: 'b'
 ( outerInterp isWhatSpace: ' ' ) if: { "It's white space" print }
 { outerInterp isWhatSpace: ' ' } if: { "It's white space" print }
 { outerInterp isWhatSpace: ' ' } whileTrue: { "true" }


 root subclass: "TestClass"
 TestClass int: "count" is: 0
 TestClass method: "sayHello" is: { count times: { "hello" print } }
 TestClass sayHello
 sayHello
 count: 7


 { | count | count times: { "hello" print } } executeWith: 4


 }

 Tasks
 - search root

 */

public class Main {

	public class Word {
		public String getName() {
			return getClass().getName();
		}

		public void execute(ExecutionContext context) {
		}
	}

	public class ExecutionContext extends Word {
		private ExecutionContext parent;
		private int index;
		private CompositeWord caller;
		private Vocabulary vocab;
		private Terminal terminal;

		public ExecutionContext(Vocabulary vocab, Terminal terminal) {
			parent = null;
			index = 0;
			caller = null;
			this.vocab = vocab;
			this.terminal = terminal;
		}

		public ExecutionContext(ExecutionContext other) {
			parent = other;
			index = 0;
			caller = other.caller;
			vocab = other.vocab;
			terminal = other.terminal;
		}
		
		public ExecutionContext getParent() {
			return parent;
		}

		public void setTerminal(Terminal terminal) {
			this.terminal = terminal;
		}

		public Terminal getTerminal() {
			return terminal;
		}

		public void setVocab(Vocabulary vocab) {
			this.vocab = vocab;
		}

		public Vocabulary getVocab() {
			return vocab;
		}

		public void setCaller(CompositeWord caller) {
			this.caller = caller;
		}

		public CompositeWord getCaller() {
			return caller;
		}
		
		public void branch(int offset) {
			index = index + offset;
		}
	}

	public class CompositeWord extends Word {
		private String name;
		private ArrayList<Word> words = new ArrayList<>();

		public CompositeWord(String name) {
			this.name = name;
		}

		public CompositeWord(String name, Word[] words) {
			this.name = name;
			this.words.addAll(Arrays.asList(words));
		}

		public String getName() {
			return name;
		}

		public void appendWord(Word word) {
			words.add(word);
		}

		@Override
		public void execute(ExecutionContext contextIn) {
			ExecutionContext context = new ExecutionContext(contextIn);
			context.setCaller(this);
			for (context.index = 0; context.index < words.size(); context.index++) {
				Word w = words.get(context.index);
				w.execute(context);
			}
		}
	}

	public interface Vocabulary {
		public Word searchWord(String name);

		public class EmptyVocabulary implements Vocabulary {
			@Override
			public Word searchWord(String name) {
				return null;
			}
		}

		public static Vocabulary EMPTY = new EmptyVocabulary();
	}

	public class VocabularyExtension implements Vocabulary {
		private Vocabulary parent;
		private HashMap<String, Word> wordList;

		public VocabularyExtension() {
			this.parent = Vocabulary.EMPTY;
			this.wordList = new HashMap<>();
		}

		public VocabularyExtension(Vocabulary parent) {
			this.parent = parent;
			this.wordList = new HashMap<>();
		}

		public void addWord(Word word) {
			wordList.put(word.getName(), word);
		}

		public void removeWord(Word word) {
			wordList.remove(word.getName());
		}

		public Word getLocalWord(String name) {
			return wordList.get(name);
		}

		@Override
		public Word searchWord(String name) {
			Word word = getLocalWord(name);
			if (word != null) {
				return word;
			} else {
				return parent.searchWord(name);
			}
		}
	}

	
	private static String operator = "([\\+-<>,.=!@#$%^&*\\(\\)\\[\\]\\{\\}]+)";
	private static String keyword = "(\\w+\\:)";
	
	public static class Terminal {
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

	public static void main(String args[]) {
		Terminal t = new Terminal();
		System.out.println("Hello world.");
		while (true) {
			t.print(t.nextToken().toString());
			t.print("\n");
		}
	}
}

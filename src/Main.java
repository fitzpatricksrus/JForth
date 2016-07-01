import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

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


class Word {
    private String name;
    public String getName() {
        return name;
    }
    public void execute(ExecutionContext context) {
    }
}

class ExecutionContext {
	// hey jf - make this a proper linked stack list
	public int index;
	public CompositeWord caller;
	public Vocabulary vocab;
	public Terminal terminal;

	public ExecutionContext(ExecutionContext other) {
		index = other.index;
		caller = other.caller;
		vocab = other.vocab;
		terminal = other.terminal;
	}
	public ExecutionContext(ExecutionContext other, CompositeWord newCaller) {
		index = other.index;
		caller = newCaller;
		vocab = other.vocab;
		terminal = other.terminal;
	}
}

class CompositeWord extends Word {
    private ArrayList<Word> words = new ArrayList<>();
    public CompositeWord() {
    }

    public CompositeWord(Word[] words) {
        this.words.addAll(Arrays.asList(words));
    }

    public void appendWord(Word word) {
        words.add(word);
    }

    @Override
    public void execute(ExecutionContext contextIn) {
		ExecutionContext context = new ExecutionContext(contextIn, this);
        for (context.index = 0; context.index < words.size(); context.index++ ) {
			Word w = words.get(context.index);
            w.execute(context);
        }
    }
}

interface Vocabulary {
    public Word searchWord(String name);
    public class EmptyVocabulary implements Vocabulary {
        public Word searchWord(String name) {
            return null;
        }
    }
    public static Vocabulary EMPTY = new EmptyVocabulary();
}

class VocabularyExtension implements Vocabulary {
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
    public Word getWord(String name) {
        return wordList.get(name);
    }
    public Word searchWord(String name) {
        Word word = getWord(name);
        if (word != null) {
            return word;
        } else {
            return parent.searchWord(name);
        }
    }
}

interface Terminal {
    public String nextToken();
    public void print(String message);
}

public class Main {
	public static void main(String args[]) {
		System.out.println("Hello world.");
	}
}

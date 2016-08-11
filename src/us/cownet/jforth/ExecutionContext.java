package us.cownet.jforth;

import java.util.ArrayList;

public class ExecutionContext extends Word {
	public int index;
	private ExecutionContext parent;
	private CompositeWord caller;
	private Vocabulary vocab;
	private Terminal terminal;
	private ArrayList<Word> temps;

	public ExecutionContext(Vocabulary vocab, Terminal terminal) {
		parent = null;
		index = 0;
		caller = null;
		this.vocab = vocab;
		this.terminal = terminal;
		temps = new ArrayList<>();
	}

	public ExecutionContext(ExecutionContext parent, CompositeWord caller) {
		this.parent = parent;
		index = 0;
		this.caller = caller;
		this.vocab = parent.vocab;
		this.terminal = parent.terminal;
		temps = new ArrayList<>();
	}

	public ExecutionContext getParent() {
		return parent;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	public Vocabulary getVocab() {
		return vocab;
	}

	public void setVocab(Vocabulary vocab) {
		this.vocab = vocab;
	}

	public CompositeWord getCaller() {
		return caller;
	}

	public void setCaller(CompositeWord caller) {
		this.caller = caller;
	}

	public void branch(int offset) {
		index = index + offset;
	}

	public Word top() {
		return temps.get(temps.size() - 1);
	}

	public Word peek(int ndx) {
		return temps.get(ndx);
	}

	public Word pop() {
		int ndx = temps.size() - 1;
		Word result = temps.get(ndx);
		temps.remove(ndx);
		return result;
	}

	public void push(Word value) {
		if (value == null) {
			temps.add(Word.NULL);
		} else {
			temps.add(value);
		}
	}

	public boolean popBoolean() {
		BooleanConstant b = (BooleanConstant) pop();
		return b.getValue();
	}

	public void push(boolean b) {
		push(b ? BooleanConstant.TRUE : BooleanConstant.FALSE);
	}

	public int popInt() {
		IntegerConstant i = (IntegerConstant) pop();
		return i.getValue();
	}

	public void push(int i) {
		push((i == 0) ? IntegerConstant.ZERO : (i == 1) ? IntegerConstant.ONE : new IntegerConstant(i));
	}

	public String popString() {
		StringConstant s = (StringConstant) pop();
		return s.getValue();
	}

	public void push(String s) {
		push(new StringConstant(s));
	}

	public Word wordFor(String key) {
		// hey jf - this confuses the contents of the catalog with the catalog object itself.
		Word result = getVocab().wordFor(key);
		if (result == null) {
			if (parent == null) {
				result = null;
			} else {
				result = parent.wordFor(key);
			}
		}
		return result;
	}

	//--------------------------
	// Vocabulary

	public static void dup(ExecutionContext context) {
		// ( W -- W W)
		context.push(context.peek(0));
	}

	public static void drop(ExecutionContext context) {
		// ( ...W1, W0, IntegerConstant -- ...W1 )
		context.pop();
	}

	public static void peek(ExecutionContext context) {
		// (  ..., Wn+1, Wn,..., W0, IntegerConstant -- ..., Wx, Wn,..., W0, Wn )
		context.push(context.peek(context.popInt()));
	}

	public static void pick(ExecutionContext context) {
		// ( ..., Wx, Wn,..., W0, IntegerConstant -- ..., Wx, Wn-1,..., W0, Wn )
		int ndx = context.popInt();
		Word selectedWord = context.temps.get(ndx);
		context.temps.remove(ndx);
		context.temps.add(selectedWord);
	}

	@AlternateName(name = "this")
	public static void currentCaller(ExecutionContext context) {
		// ( Word -- <data pushed by Word> )
		context.push(context.caller);
	}

	public static void executeTOS(ExecutionContext context) {
		// ( Word -- <data pushed by Word> )
		context.pop().execute(context);
	}

	public static void currentContext(ExecutionContext context) {
		// ( Word -- <data pushed by Word> )
		context.push(context);
	}

	public static void currentVocab(ExecutionContext context) {
		// ( Word -- <data pushed by Word> )
		context.push(context.vocab);
	}
}

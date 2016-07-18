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

	//--------------------------
	// Vocabulary

	@Override
	protected Vocabulary constructVocabulary() {
		return super.constructVocabulary()
		            .addWord(new ExecutionContextDup())
		            .addWord(new ExecutionContextPeek())
		            .addWord(new ExecutionContextDrop())
		            .addWord(new ExecutionContextSelect())
		            .addWord(new ExecutionContextExecuteTOS())
		            .addWord(new ExecutionContextThis());
	}

	public static class ExecutionContextDup extends Word {
		// ( Word -- Word Word)
		@Override
		public void execute(ExecutionContext context) {
			context.push(context.peek(0));
		}
	}

	public static class ExecutionContextPeek extends Word {
		// ( IntegerConstant -- Word )
		@Override
		public void execute(ExecutionContext context) {
			context.push(context.peek(context.popInt()));
		}
	}

	public static class ExecutionContextDrop extends Word {
		// ( Wn,...W0, IntegerConstant -- Wn-1,...W0, Wn )
		@Override
		public void execute(ExecutionContext context) {
			context.pop();
		}
	}

	public static class ExecutionContextSelect extends Word {
		// ( ..., Wx, Wn,..., W0, IntegerConstant -- ..., Wx, Wn-1,..., W0, Wn )
		@Override
		public void execute(ExecutionContext context) {
			int ndx = context.popInt();
			Word selectedWord = context.temps.get(ndx);
			context.temps.remove(ndx);
			context.temps.add(selectedWord);
		}
	}

	public static class ExecutionContextThis extends Word {
		// ( Word -- <data pushed by Word> )
		@Override
		public void execute(ExecutionContext context) {
			context.push(context.caller);
		}
	}

	public static class ExecutionContextExecuteTOS extends Word {
		// ( Word -- <data pushed by Word> )
		@Override
		public void execute(ExecutionContext context) {
			context.pop().execute(context);
		}
	}

	public static class ExecutionContextThis extends Word {
		// ( Word -- <data pushed by Word> )
		@Override
		public void execute(ExecutionContext context) {
			context.push(context.caller);
		}
	}
}

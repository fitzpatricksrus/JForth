package us.cownet.jforth;

public class ExecutionContext extends Word {
	public int index;
	private ExecutionContext parent;
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
}

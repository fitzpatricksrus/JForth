import CompositeWord;
import Vocabulary;
import Terminal;

public class ExecutionContext extends Word {
	private ExecutionContext parent;
	public int index;
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

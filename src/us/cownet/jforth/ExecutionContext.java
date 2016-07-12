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

	public Word popTemp() {
		int ndx = temps.size() - 1;
		Word result = temps.get(ndx);
		temps.remove(ndx);
		return result;
	}

	public int pushTemp(Word value) {
		int ndx = temps.size();
		temps.add(value);
		return ndx;
	}

	public Word getTemp(int ndx) {
		return temps.get(ndx);
	}

	public void setTemp(int ndx, Word value) {
		temps.set(ndx, value);
	}

	public Word topTemp() {
		return temps.get(temps.size() - 1);
	}
}

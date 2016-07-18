package us.cownet.jforth;

public class CompositeWord extends WordArray {
	public CompositeWord(Word[] words) {
		super(words);
	}

	@Override
	public void execute(ExecutionContext context) {
		ExecutionContext newContext = new ExecutionContext(context, CompositeWord.this);
		for (newContext.index = 0; newContext.index < CompositeWord.this.size(); newContext.index++) {
			Word w = CompositeWord.this.at(newContext.index);
			w.execute(newContext);
		}
	}

	//--------------------------
	// Vocabulary

	@Override
	protected Vocabulary constructVocabulary() {
		return super.constructVocabulary();
	}
}

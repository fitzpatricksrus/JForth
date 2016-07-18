package us.cownet.jforth;

public class Word {
	public static final Word NULL = new Word() {
		public String getName() {
			return "NULL";
		}
	};

	public void execute(ExecutionContext context) {
		context.push(this);
	}

	public Word searchWord(String key) {
		return getVocabulary().searchWord(key);
	}

	//--------------------------
	// Vocabulary

	public final Vocabulary getVocabulary() {
		return constructVocabulary();
	}

	protected Vocabulary constructVocabulary() {
		return new Vocabulary()
				.addWord("===", new IdentityEquals())
				.addWord("NULL", new WordNull());
	}

	public static class IdentityEquals extends Word {
		@Override
		public void execute(ExecutionContext context) {
			// (word word -- BooleanConstant )
			context.push((context.pop() == context.pop()) ? BooleanConstant.TRUE : BooleanConstant.FALSE);
		}
	}

	public static class WordNull extends Word {
		@Override
		public void execute(ExecutionContext context) {
			// ( Word -- StringConstant )
			context.push(NULL);
		}
	}
}


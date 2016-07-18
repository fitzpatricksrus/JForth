package us.cownet.jforth;

public class Word {
	public String getName() {
		return getClass().getName();
	}

	public void execute(ExecutionContext context) {
		context.push(this);
	}

	public Word searchWord(String key) {
		return getVocabulary().searchWord(key);
	}

	public final Vocabulary getVocabulary() {
		return constructVocabulary();
	}

	protected SimpleVocabulary constructVocabulary() {
		return new SimpleVocabulary().addWord(new IdentityEquals())
		                             .addWord(new WordName());
	}

	//--------------------------
	// Vocabulary

	public static class IdentityEquals extends Word {
		@Override
		public void execute(ExecutionContext context) {
			// (word word -- BooleanConstant )
			context.push((context.pop() == context.pop()) ? BooleanConstant.TRUE : BooleanConstant.FALSE);
		}
	}

	public static class WordName extends Word {
		@Override
		public void execute(ExecutionContext context) {
			// ( Word -- StringConstant )
			context.push(new StringConstant(context.pop().getName()));
		}
	}
}


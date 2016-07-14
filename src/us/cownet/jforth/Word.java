package us.cownet.jforth;

public class Word {
	public String getName() {
		return getClass().getName();
	}

	public void execute(ExecutionContext context) {
		context.pushTemp(this);
	}

	public final Vocabulary getVocabulary() {
		return constructVocabulary();
	}

	protected SimpleVocabulary constructVocabulary() {
		return new SimpleVocabulary().addWord(new IdentityEquals<>());
	}

	public static class IdentityEquals<T> extends Word {
		@Override
		public void execute(ExecutionContext context) {
			context.pushTemp(new BooleanConstant(context.popTemp() == context.popTemp()));
		}
	}

	public static class ExecuteTOS extends Word {
		public void execute(ExecutionContext context) {
			context.popTemp().execute(context);
		}
	}
}


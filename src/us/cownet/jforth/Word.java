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
		Vocabulary v = new Vocabulary();
		Class c = getClass();
		while (c != null) {
			v.autoFillWords(c);
			c = c.getSuperclass();
		}
		return v;
	}

	@AlternateName(name = "===")
	public static void IdentityEquals(ExecutionContext context) {
		// (word word -- BooleanConstant )
		context.push((context.pop() == context.pop()) ? BooleanConstant.TRUE : BooleanConstant.FALSE);
	}

	@AlternateName(name = "NULL")
	public static void WordNull(ExecutionContext context) {
		// ( -- NULL )
		context.push(NULL);
	}

}


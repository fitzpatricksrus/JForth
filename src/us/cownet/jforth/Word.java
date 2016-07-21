package us.cownet.jforth;

public class Word {
	public static final String PARENT_KEY = "parent";

	public static final Word NULL = new Word() {
		public String getName() {
			return "NULL";
		}
	};

	public void execute(ExecutionContext context) {
		context.push(this);
	}

	public Word wordFor(String key) {
		Word result = getVocabulary().localWordFor(key);
		if (result == null) {
			Word parent = getVocabulary().localWordFor(PARENT_KEY);
			if (parent != null) {
				result = parent.wordFor(key);
			}
		}
		return result;
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

	public static void getParent(ExecutionContext context) {
		// ( word -- parentWord )
		context.push(context.pop().wordFor(PARENT_KEY));
	}

	public static void setParent(ExecutionContext context) {
		// ( newParent word -- )
		Word it = context.pop();
		it.getVocabulary().addWord(PARENT_KEY, context.pop());
	}

	@AlternateName(name = "wordFor")
	public static void searchVocabulary(ExecutionContext context) {
		// ( StringConstant Word -- Word )
		Word w = context.pop();
		context.push(w.wordFor(context.popString()));
	}
}


package us.cownet.jforth;

public class Word {
	public static final String PARENT_KEY = "parent";

	public static final Word NULL = new Word();

	public void execute(ExecutionContext context) {
		context.push(this);
	}

	public Word respondsWithWord(String key) {
		Word result = getVocabulary().containsWord(key);
		if (result == null) {
			Word parent = getVocabulary().containsWord(PARENT_KEY);
			if (parent != null) {
				result = parent.respondsWithWord(key);
			}
		}
		return result;
	}

	public Word getParent() {
		return getVocabulary().respondsWithWord(PARENT_KEY);
	}

	public void setParent(Word newParent) {
		getVocabulary().addWord(PARENT_KEY, newParent);
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

	@AlternateName(name = "null")
	public static void WordNull(ExecutionContext context) {
		// ( -- NULL )
		context.push(NULL);
	}

	public static void getParent(ExecutionContext context) {
		// ( word -- parentWord )
		context.push(context.pop().getParent());
	}

	public static void setParent(ExecutionContext context) {
		// ( newParent word -- )
		Word it = context.pop();
		it.setParent(context.pop());
	}

	@AlternateName(name = "respondsWithWord")
	public static void searchVocabulary(ExecutionContext context) {
		// ( StringConstant Word -- Word )
		Word w = context.pop();
		context.push(w.respondsWithWord(context.popString()));
	}
}


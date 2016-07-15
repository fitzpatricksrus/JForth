package us.cownet.jforth;

public class CompositeWord extends WordArray {
	private String name;

	public CompositeWord(String name) {
		this.name = name;
	}

	public CompositeWord(String name, Word[] words) {
		super(words);
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public Word getExecutor() {
		return new Word() {
			@Override
			public String getName() {
				return CompositeWord.this.getName() + ".execute";
			}

			@Override
			public void execute(ExecutionContext context) {
				ExecutionContext newContext = new ExecutionContext(context, CompositeWord.this);
				for (newContext.index = 0; newContext.index < CompositeWord.this.size(); newContext.index++) {
					Word w = CompositeWord.this.at(newContext.index);
					w.execute(newContext);
				}
			}
		};
	}

	@Override
	protected SimpleVocabulary constructVocabulary() {
		return super.constructVocabulary()
				.addWord(new CompositeWordExecutable());
	}

	// this returns a Word that when executed will execute the contents of this CompositeWord
	public static class CompositeWordExecutable extends Word {
		// ( CompositeWord -- CompositeWordExecutor )
		@Override
		public void execute(ExecutionContext context) {
			CompositeWord cw = (CompositeWord) context.pop();
			context.push(cw.getExecutor());
		}
	}
}

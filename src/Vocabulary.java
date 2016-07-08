public interface Vocabulary {
	public Word searchWord(String name);

	public class EmptyVocabulary implements Vocabulary {
		@Override
		public Word searchWord(String name) {
			return null;
		}
	}

	public static Vocabulary EMPTY = new EmptyVocabulary();
}

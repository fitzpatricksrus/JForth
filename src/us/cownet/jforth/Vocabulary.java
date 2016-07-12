package us.cownet.jforth;

public interface Vocabulary {
	Vocabulary EMPTY = new EmptyVocabulary();

	Word searchWord(String name);

	class EmptyVocabulary implements Vocabulary {
		@Override
		public Word searchWord(String name) {
			return null;
		}
	}
}

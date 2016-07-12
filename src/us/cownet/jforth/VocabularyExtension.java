package us.cownet.jforth;

import java.util.HashMap;

public class VocabularyExtension implements Vocabulary {
	private Vocabulary parent;
	private HashMap<String, Word> wordList;

	public VocabularyExtension() {
		this.parent = Vocabulary.EMPTY;
		this.wordList = new HashMap<>();
	}

	public VocabularyExtension(Vocabulary parent) {
		this.parent = parent;
		this.wordList = new HashMap<>();
	}

	public void addWord(Word word) {
		wordList.put(word.getName(), word);
	}

	public void removeWord(Word word) {
		wordList.remove(word.getName());
	}

	@Override
	public Word searchWord(String name) {
		Word word = getLocalWord(name);
		if (word != null) {
			return word;
		} else {
			return parent.searchWord(name);
		}
	}

	public Word getLocalWord(String name) {
		return wordList.get(name);
	}
}


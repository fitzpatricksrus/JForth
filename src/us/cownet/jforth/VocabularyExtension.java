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
		addWord(word.getName(), word);
	}

	public void addWord(String name, Word word) {
		wordList.put(name, word);
	}

	public void removeWord(Word word) {
		removeWord(word.getName());
	}

	public void removeWord(String name) {
		wordList.remove(name);
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


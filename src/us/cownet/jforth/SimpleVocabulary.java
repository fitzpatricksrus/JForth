package us.cownet.jforth;

import java.util.HashMap;

public class SimpleVocabulary implements Vocabulary {
	private HashMap<String, Word> wordList;

	public SimpleVocabulary() {
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
		return getLocalWord(name);
	}

	public Word getLocalWord(String name) {
		return wordList.get(name);
	}
}

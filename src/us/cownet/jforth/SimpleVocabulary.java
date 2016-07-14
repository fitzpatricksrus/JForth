package us.cownet.jforth;

import java.util.HashMap;

public class SimpleVocabulary implements Vocabulary {
	private HashMap<String, Word> wordList;

	public SimpleVocabulary() {
		wordList = new HashMap<>();
	}

	public SimpleVocabulary(Word words[]) {
		wordList = new HashMap<>();
		for (Word w : words) {
			wordList.put(w.getName(), w);
		}
	}

	public SimpleVocabulary addWord(Word word) {
		addWord(word.getName(), word);
		return this;
	}

	public SimpleVocabulary addWord(String name, Word word) {
		wordList.put(name, word);
		return this;
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

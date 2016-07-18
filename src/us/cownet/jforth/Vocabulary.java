package us.cownet.jforth;

import java.util.HashMap;

public class Vocabulary extends Word {
	private HashMap<String, Word> wordList;
	private HashMap<Word, String> nameList;

	public Vocabulary() {
		wordList = new HashMap<>();
	}

	public Vocabulary addWord(String name, Word word) {
		wordList.put(name, word);
		nameList.put(word, name);
		return this;
	}

	public void removeWord(Word word) {
		removeWord(nameList.get(word));
	}

	public void removeWord(String name) {
		Word word = wordList.get(name);
		wordList.remove(name);
		nameList.remove(word);
	}

	public String getName(Word word) {
		return nameList.get(word);
	}

	@Override
	public Word searchWord(String name) {
		return wordList.get(name);
	}

	//--------------------------
	// Vocabulary

	protected Vocabulary constructVocabulary() {
		return super.constructVocabulary()
				.addWord("new", new VocabularyCreate())
				.addWord("add:", new VocabularyAddWord())
				.addWord("remove:", new VocabularyRemoveWord())
				.addWord("get:", new VocabularySearchWord());
	}

	public static class VocabularyCreate extends Word {
		// ( -- Vocabulary )
		@Override
		public void execute(ExecutionContext context) {
			context.push(new Vocabulary());
		}
	}

	public static class VocabularyAddWord extends Word {
		// ( String Word Vocab -- )
		@Override
		public void execute(ExecutionContext context) {
			Vocabulary v = (Vocabulary) context.pop();
			Word word = context.pop();
			String name = context.popString();
			v.addWord(name, word);
		}
	}

	public static class VocabularyRemoveWord extends Word {
		// ( Word Vocab -- )
		@Override
		public void execute(ExecutionContext context) {
			Vocabulary v = (Vocabulary) context.pop();
			Word word = context.pop();
			if (word instanceof StringConstant) {
				StringConstant s = (StringConstant) word;
				v.removeWord(s);
			} else {
				v.removeWord(word);
			}
		}
	}

	public static class VocabularySearchWord extends Word {
		// ( String Vocabulary -- Word )
		@Override
		public void execute(ExecutionContext context) {
			Vocabulary v = (Vocabulary) context.pop();
			context.push(v.searchWord(context.popString()));
		}
	}




}

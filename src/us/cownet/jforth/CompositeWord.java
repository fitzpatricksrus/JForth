package us.cownet.jforth;

import java.util.ArrayList;
import java.util.Arrays;

public class CompositeWord extends Word {
	private String name;
	private ArrayList<Word> words = new ArrayList<>();

	public CompositeWord(String name) {
		this.name = name;
	}

	public CompositeWord(String name, Word[] words) {
		this.name = name;
		this.words.addAll(Arrays.asList(words));
	}

	public String getName() {
		return name;
	}

	@Override
	public void execute(ExecutionContext context) {
		ExecutionContext newContext = new ExecutionContext(context, this);
		for (newContext.index = 0; newContext.index < words.size(); newContext.index++) {
			Word w = words.get(newContext.index);
			w.execute(newContext);
		}
	}

	public void appendWord(Word word) {
		words.add(word);
	}
}

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

	public void appendWord(Word word) {
		words.add(word);
	}

	@Override
	public void execute(ExecutionContext contextIn) {
		ExecutionContext context = new ExecutionContext(contextIn);
		context.setCaller(this);
		for (context.index = 0; context.index < words.size(); context.index++) {
			Word w = words.get(context.index);
			w.execute(context);
		}
	}
}

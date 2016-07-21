package us.cownet.jforth;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Vocabulary extends Word {
	private HashMap<String, Word> wordList;
	private HashMap<Word, String> nameList;

	public Vocabulary() {
		wordList = new HashMap<>();
		nameList = new HashMap<>();
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

	public Word localWordFor(String name) {
		return wordList.get(name);
	}

	public Vocabulary autoFillWords(Class c) {
		Method methods[] = getMethodsBySignature(c, Void.TYPE, ExecutionContext.class);
		for (Method m : methods) {
			int modifiers = m.getModifiers();
			if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
				AlternateName an = m.getAnnotation(AlternateName.class);
				String name = (an == null) ? m.getName() : an.name();
				final Method method = m;
				addWord(
						name,
						new Word() {
							public void execute(ExecutionContext context) {
								try {
									method.invoke(null, context);
								} catch (InvocationTargetException e) {
									// hey jf - what do you want to do with these?
									System.out.println(e);
								} catch (IllegalAccessException e) {
									// hey jf - what do you want to do with these?
									System.out.println(e);
								}
							}
						}
				);
			}
		}
		return this;
	}

	public static Method[] getMethodsBySignature(Class<?> clazz, Class<?> returnType, Class<?>... args) {
		ArrayList<Method> result = new ArrayList<Method>();
		for (Method m : clazz.getDeclaredMethods()) {
			if (m.getReturnType().equals(returnType)) {
				Class<?>[] params = m.getParameterTypes();
				if (Arrays.equals(params, args)) {
					result.add(m);
				}
			}
		}
		return result.toArray(new Method[result.size()]);
	}

	//--------------------------
	// Vocabulary

	public static void create(ExecutionContext context) {
		// ( -- Vocabulary )
		context.push(new Vocabulary());
	}

	public static void add(ExecutionContext context) {
		// ( String Word Vocab -- )
		Vocabulary v = (Vocabulary) context.pop();
		Word word = context.pop();
		String name = context.popString();
		v.addWord(name, word);
	}

	public static void remove(ExecutionContext context) {
		// ( Word Vocab -- )
		Vocabulary v = (Vocabulary) context.pop();
		Word word = context.pop();
		if (word instanceof StringConstant) {
			StringConstant s = (StringConstant) word;
			v.removeWord(s);
		} else {
			v.removeWord(word);
		}
	}

	public static void at(ExecutionContext context) {
		// ( String Vocabulary -- Word )
		Vocabulary v = (Vocabulary) context.pop();
		context.push(v.localWordFor(context.popString()));
	}
}

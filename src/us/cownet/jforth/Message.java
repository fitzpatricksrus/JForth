package us.cownet.jforth;

public class Message extends Word {
	/*
	Message go on the stack as ( arg1, arg2, ..., argn, thisWord -- )
	The message leaves the stack in tact when it calls
	 */

	private String message;

	public Message(String message) {
		this.message = message;
	}

	protected Word getExecutor() {
		return new Word() {
			@Override
			public void execute(ExecutionContext context) {
				// ( word -- ? )
				// hey jf - what happens if the message isn't found?
				Word word = context.top().wordFor(message);
				if (word == null) {
					word = context.wordFor(message);
				}
				if (word == null) {
					// what do we do here if the message is not understood?
				} else {
					word.execute(context);
				}
			}
		};
	}

	public static class MessageLookup extends Word {
		@Override
		public void execute(ExecutionContext context) {
			// ( StringConstant Word_a - Word_b )
			Word thisWord = context.pop();
			String message = context.popString();
			context.push(thisWord.wordFor(message));
		}
	}

	@Override
	public void execute(ExecutionContext context) {
		context.push(this);
	}

	//--------------------------
	// Vocabulary

	public static void create(ExecutionContext context) {
		// ( StringConstant - Message )
		String messageString = context.popString();
		context.push(new Message(messageString));
	}

	public static void executable(ExecutionContext context) {
		// ( Message - Word )
		Message m = (Message) context.pop();
		context.push(m.getExecutor());
	}
}

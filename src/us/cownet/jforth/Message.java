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
			public String getName() {
				return "." + Message.this.getName() + "()";
			}

			@Override
			public void execute(ExecutionContext context) {
				// ( word -- ? )
				// hey jf - what happens if the message isn't found?
				context.top().searchWord(message).execute(context);
			}
		};
	}

	@Override
	public String getName() {
		return "message_" + message;
	}

	@Override
	public void execute(ExecutionContext context) {
		context.push(this);
	}

	public static class MessageCreate extends Word {
		@Override
		public void execute(ExecutionContext context) {
			// ( StringConstant - Message )
			String messageString = context.popString();
			context.push(new Message(messageString));
		}
	}

	//--------------------------
	// Vocabulary

	public static class MessageLookup extends Word {
		@Override
		public void execute(ExecutionContext context) {
			// ( StringConstant Word_a - Word_b )
			Word thisWord = context.pop();
			String message = context.popString();
			context.push(thisWord.searchWord(message));
		}
	}

	@Override
	protected SimpleVocabulary constructVocabulary() {
		return super.constructVocabulary()
		            .addWord(new MessageCreate())
		            .addWord(new MessageExecutable());
	}

	public static class MessageExecutable extends Word {
		@Override
		public void execute(ExecutionContext context) {
			// ( Message - Word )
			Message m = (Message) context.pop();
			context.push(m.getExecutor());
		}
	}


}

package testSpace;

import java.io.Serializable;

public final class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Object object;
	private String content;

	public Message(final Object obj, final String content) {
		this.object = obj;
		this.content = content;
	}

	public Object getObject() {
		return this.object;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(final String c) {
		this.content = c;
	}
}
package ing_software.gestione_impiegati;

import java.io.Serializable;

public final class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	private Object obj;
	private String content;
	private Functions calledFunction;

	public Functions getCalledFunction() {
		return calledFunction;
	}

	public void setCalledFunction(Functions calledFunction) {
		this.calledFunction = calledFunction;
	}

	public Message(final Object obj, final String content, final Functions calledFunction) {
		this.obj = obj;
		this.content = content;
		this.calledFunction = calledFunction;
	}

	public void setObj(final Object obj) {
		this.obj = obj;
	}
	
	public Object getObj() {
		return this.obj;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(final String c) {
		this.content = c;
	}
}
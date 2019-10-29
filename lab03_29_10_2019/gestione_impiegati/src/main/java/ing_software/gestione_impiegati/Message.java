package ing_software.gestione_impiegati;

import java.io.Serializable;

public final class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private Branch branch;
	private String content;

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

}

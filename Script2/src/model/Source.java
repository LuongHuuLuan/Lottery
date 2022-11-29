package model;

public class Source {
	private int idSour;
	private String codeSour;
	private String name;
	private String url;

	public Source() {
	}

	public Source(int idSour, String codeSour, String name, String url) {
		super();
		this.idSour = idSour;
		this.codeSour = codeSour;
		this.name = name;
		this.url = url;
	}

	public int getIdSour() {
		return idSour;
	}

	public void setIdSour(int idSour) {
		this.idSour = idSour;
	}

	public String getCodeSour() {
		return codeSour;
	}

	public void setCodeSour(String codeSour) {
		this.codeSour = codeSour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

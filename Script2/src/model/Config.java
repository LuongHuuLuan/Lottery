package model;

public class Config {
	private int id;
	private String sourceCode;
	private String sourceName;
	private String sourceUrl;
	private String localStogrePath;
	private String ftp;
	private String userName;
	private String password;

	public Config() {

	}

	

	public Config(int id, String sourceCode, String sourceName, String sourceUrl, String localStogrePath, String ftp,
			String userName, String password) {
		this.id = id;
		this.sourceCode = sourceCode;
		this.sourceName = sourceName;
		this.sourceUrl = sourceUrl;
		this.localStogrePath = localStogrePath;
		this.ftp = ftp;
		this.userName = userName;
		this.password = password;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getSourceCode() {
		return sourceCode;
	}



	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}



	public String getSourceName() {
		return sourceName;
	}



	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}



	public String getSourceUrl() {
		return sourceUrl;
	}



	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}



	public String getLocalStogrePath() {
		return localStogrePath;
	}



	public void setLocalStogrePath(String localStogrePath) {
		this.localStogrePath = localStogrePath;
	}



	public String getFtp() {
		return ftp;
	}



	public void setFtp(String ftp) {
		this.ftp = ftp;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

}

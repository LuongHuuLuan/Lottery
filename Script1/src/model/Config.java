package model;

public class Config {
	private int idConfig;
	private String source;
	private String sourceLocal;
	private String ftp;
	private String userName;
	private String password;

	public Config() {

	}

	public Config(int idConfig, String source, String sourceLocal, String ftp, String userName, String password) {
		this.idConfig = idConfig;
		this.source = source;
		this.sourceLocal = sourceLocal;
		this.ftp = ftp;
		this.userName = userName;
		this.password = password;
	}

	public int getIdConfig() {
		return idConfig;
	}

	public void setIdConfig(int idConfig) {
		this.idConfig = idConfig;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceLocal() {
		return sourceLocal;
	}

	public void setSourceLocal(String sourceLocal) {
		this.sourceLocal = sourceLocal;
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

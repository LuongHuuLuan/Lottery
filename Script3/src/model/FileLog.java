package model;

import java.sql.Timestamp;

public class FileLog {
	private int id;
	private Config config;
	private String fileName;
	private Timestamp date;
	private String state;
	private int contact;

	public FileLog() {
	}

	public FileLog(int id, Config config, String fileName, Timestamp date, String state, int contact) {
		this.id = id;
		this.config = config;
		this.fileName = fileName;
		this.date = date;
		this.state = state;
		this.contact = contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}
}

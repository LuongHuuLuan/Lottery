package model;

public class Date {
	private int idDate;
	private String fullDate;
	private String day;
	private int date;
	private int month;
	private int year;

	public Date() {
	}

	public Date(int idDate, String fullDate, String day, int date, int month, int year) {
		this.idDate = idDate;
		this.fullDate = fullDate;
		this.day = day;
		this.date = date;
		this.month = month;
		this.year = year;
	}

	public int getIdDate() {
		return idDate;
	}

	public void setIdDate(int idDate) {
		this.idDate = idDate;
	}

	public String getFullDate() {
		return fullDate;
	}

	public void setFullDate(String fullDate) {
		this.fullDate = fullDate;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int day) {
		this.date = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}

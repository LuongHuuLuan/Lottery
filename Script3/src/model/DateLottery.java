package model;

public class DateLottery {
	private int idDate;
	private String fullDate;
	private String day;
	private int date;
	private int month;
	private int year;

	public DateLottery(int idDate, String fullDate, String day, int date, int month, int year) {
		super();
		this.idDate = idDate;
		this.fullDate = fullDate;
		this.day = day;
		this.date = date;
		this.month = month;
		this.year = year;
	}

	@Override
	public String toString() {
		return "DateLottery [idDate=" + idDate + ", fullDate=" + fullDate + ", day=" + day + ", date=" + date
				+ ", month=" + month + ", year=" + year + "]";
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

	public void setDate(int date) {
		this.date = date;
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

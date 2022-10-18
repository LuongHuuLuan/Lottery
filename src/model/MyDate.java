package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MyDate {
	private int day;
	private int month;
	private int year;
	private int time;
	private int minute;
	private int second;
	private int nano;

	public MyDate() {
		LocalDateTime now = LocalDateTime.now();
		this.day = now.getDayOfMonth();
		this.month = now.getMonthValue();
		this.year = now.getYear();
		this.time = now.getHour();
		this.minute = now.getMinute();
		this.second = now.getSecond();
		this.nano = now.getNano();
	}

	public static void main(String[] args) {
		System.out.println(new MyDate());
	}

	public MyDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public MyDate(int day, int month, int year, int time, int minute, int second) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.time = time;
		this.minute = minute;
		this.second = second;
	}

	public MyDate(int day, int month, int year, int time, int minute, int second, int nano) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.time = time;
		this.minute = minute;
		this.second = second;
		this.nano = nano;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
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

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getNano() {
		return nano;
	}

	public void setNano(int nano) {
		this.nano = nano;
	}

	public MyDate nextDay() {
		int newDay = this.day;
		int newMonth = this.month;
		int newYear = this.year;
		++newDay;
		if (newMonth == 1 || newMonth == 3 || newMonth == 5 || newMonth == 7 || newMonth == 8 || newMonth == 10) {
			if (newDay > 31) {
				newDay = 1;
				++newMonth;
			}
		} else if (newMonth == 4 || newMonth == 6 || newMonth == 9 || newMonth == 11) {
			if (newDay > 30) {
				newDay = 1;
				++newMonth;
			}
		} else if (newMonth == 2) {
			if (newYear % 4 == 0 && newYear % 100 != 0 || newYear % 400 == 0) {
				if (newDay > 29) {
					newDay = 1;
					++newMonth;
				}
			} else {
				if (newDay > 28) {
					newDay = 1;
					++newMonth;
				}
			}
		} else {
			if (newDay > 31) {
				newDay = 1;
				newMonth = 1;
				++newYear;
			}
		}
		return new MyDate(newDay, newMonth, newYear);
	}

	public MyDate previousDay() {
		int newDay = this.day;
		int newMonth = this.month;
		int newYear = this.year;
		--newDay;
		if (newMonth == 2 || newMonth == 4 || newMonth == 6 || newMonth == 8 || newMonth == 9 || newMonth == 11) {
			if (newDay < 1) {
				newDay = 31;
				--newMonth;
			}
		} else if (newMonth == 5 || newMonth == 7 || newMonth == 10 || newMonth == 12) {
			if (newDay < 1) {
				newDay = 30;
				--newMonth;
			}
		} else if (newMonth == 3) {
			if (newYear % 4 == 0 && newYear % 100 != 0 || newYear % 400 == 0) {
				if (newDay < 1) {
					newDay = 29;
					--newMonth;
				}
			} else {
				if (newDay < 1) {
					newDay = 28;
					--newMonth;
				}
			}
		} else {
			if (newDay < 1) {
				newDay = 31;
				newMonth = 12;
				--newYear;
			}
		}
		return new MyDate(newDay, newMonth, newYear);
	}
	

	public MyDate clone() {
		return new MyDate(this.day, this.month, this.year, this.time, this.minute, this.second, this.nano);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyDate other = (MyDate) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	public String toDateString() {
		return (day < 10 ? "0" + day : day) + "-" + (month < 10 ? "0" + month : month) + "-" + year;
	}

	@Override
	public String toString() {
		return (day < 10 ? "0" + day : day) + "-" + (month < 10 ? "0" + month : month) + "-" + year + " "
				+ (time < 10 ? "0" + time : time) + ":" + (minute < 10 ? "0" + minute : minute) + ":"
				+ (second < 10 ? "0" + second : second);
	}

	public Timestamp toTimeStamp() {
		return Timestamp.valueOf(year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day)
				+ " " + (time < 10 ? "0" + time : time) + ":" + (minute < 10 ? "0" + minute : minute) + ":"
				+ (second < 10 ? "0" + second : second));
	}
}

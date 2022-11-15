package model;

import java.util.List;

public class Result {
	private String day;
	private String date;
	private List<String> provinces;
	private List<String> results;

	public Result() {

	}

	public Result(String day, String date, List<String> provinces, List<String> results) {
		this.day = day;
		this.date = date;
		this.provinces = provinces;
		this.results = results;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<String> provinces) {
		this.provinces = provinces;
	}

	public List<String> getResults() {
		return results;
	}

	public void setResults(List<String> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < provinces.size(); i++) {
			result += provinces.get(i) + "," + day + " " + date;
			result += "," + results.get(i);
			result += "," + results.get(i + provinces.size());
			result += "," + results.get(i + provinces.size() * 2);
			result += "," + results.get(i + provinces.size() * 3);
			result += "," + results.get(i + provinces.size() * 4);
			result += "," + results.get(i + provinces.size() * 5);
			result += "," + results.get(i + provinces.size() * 6);
			result += "," + results.get(i + provinces.size() * 7);
			result += "," + results.get(i + provinces.size() * 8) + "\n";
		}
		return result;
	}

	public int getIDForProvice(String province) {
		String[] provinces = { "Kiên Giang", "Tiền Giang", "Đà Lạt", "TPHCM", "Đồng Tháp", "Cà Mau", "Vũng Tàu",
				"Bến Tre", "Bạc Liêu", "Sóc Trăng", "Cần Thơ", "An Giang", "Bình Thuận", "Đồng Nai", "Bình Dương",
				"Tây Ninh", "Vĩnh Long", "Long An", "Trà Vinh", "Bình Phước", "Hậu Giang" };
		for (int i = 0; i < provinces.length; i++) {
			if (provinces[i].toLowerCase().equals(province.toLowerCase())) {
				return i + 1;
			}
		}
		return -1;
	}
}

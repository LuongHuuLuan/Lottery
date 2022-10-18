package crawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import dao.ConfigDAO;
import dao.LogDAO;
import model.Config;
import model.FileLog;
import model.MyDate;
import model.Result;

public class Crawler {
	// crawling xosodaiphat
	public String crawling(String url) throws IOException {
		List<String> provinces = new ArrayList<String>();
		List<String> results = new ArrayList<String>();
		Document doc = Jsoup.connect(url).data("query", "Java").userAgent("Chrome").cookie("auth", "token").get();
		try {
			Elements timeElement = doc.getElementsByClass("block-main-content view-loto").select("p").first()
					.getAllElements();
			// get time of lottery
			String timeText = timeElement.text();
			String day = "";
			String fullDate = "";
			if (timeText.indexOf("Thứ") != -1) {
				day = timeText.substring(timeText.indexOf("Thứ"), timeText.indexOf("Thứ") + 5);
				fullDate = timeText.substring(timeText.lastIndexOf(" ") + 1);
			} else {
				day = "Chủ Nhật";
				fullDate = timeText.substring(timeText.lastIndexOf(" ") + 1);
			}
			Elements contentElements = doc.getElementsByClass("block-main-content").select("table").get(0).children();
			// get province of lottery
			Elements provinceElements = contentElements.select("thead tr th:not(:first-child)");
			provinceElements.forEach(e -> {
				provinces.add(e.text());
			});
			// get result of lottery
			Elements resultElements = contentElements.select("tbody tr td:not(:first-child)");
			resultElements.forEach(e -> {
				results.add(e.text());
			});
			return new Result(day, fullDate, provinces, results).toString();
		} catch (Exception e2) {
			return "";
		}
	}

	public boolean saveCSV(String content, String dest) throws IOException {
		File destFile = new File(dest);
		if (content.length() != 0) {
			if (!destFile.exists()) {
				content = "Tỉnh,Ngày,Giải 8,Giải 7,Giải 6,Giải 5,Giải 4,Giải 3,Giải 2,Giải 1,Giải ĐB\n" + content;
			}
			FileWriter fw = new FileWriter(dest, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(content);
			pw.flush();
			pw.close();
			return true;
		}
		return false;
	}

	public List<MyDate> getStringYear(MyDate start, MyDate end) {
		List<MyDate> result = new ArrayList<MyDate>();
		while (!start.equals(end)) {
			result.add(start.clone());
			start = start.nextDay();
		}
		result.add(end);
		return result;
	}

	public void crawlToday() {
		// ten file
//		lottery-result_20-09-2020_xosodaiphat.csv
		try {
			Config config = ConfigDAO.getConfig(1);
			String src = config.getSource();
			MyDate today = new MyDate();
			String url = src.substring(0, src.indexOf("-")) + "-" + today.toDateString() + ".html";
			String data = crawling(url);
			System.out.println(data);
			String fileName = "lottery-result_" + today.toDateString() + "_"
					+ src.substring(src.indexOf("//") + 2, src.indexOf(".")) + ".csv";
			saveCSV(data, config.getSourceLocal() + "/" + fileName);
			
			FileLog fileLog = new FileLog();
			fileLog.setIdConfig(1);
			fileLog.setFileName(fileName);
			fileLog.setDate(today.toTimeStamp());
			fileLog.setState("EXTRACT");
			fileLog.setContact(1);
			LogDAO.saveLog(fileLog);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void crawl(MyDate date) {
		try {
			Config config = ConfigDAO.getConfig(1);
			String src = config.getSource();
			String url = src.substring(0, src.indexOf("-")) + "-" + date.toDateString() + ".html";
			String data = crawling(url);
			System.out.println(data);
			String fileName = "lottery-result_" + date.toDateString() + "_"
					+ src.substring(src.indexOf("//") + 2, src.indexOf(".")) + ".csv";
			saveCSV(data, config.getSourceLocal() + "/" + fileName);

			FileLog fileLog = new FileLog();
			fileLog.setIdConfig(1);
			fileLog.setFileName(fileName);
			date.setTime(17);
			date.setMinute(50);
			date.setSecond(0);
			fileLog.setDate(date.toTimeStamp());
			fileLog.setState("EXTRACT");
			fileLog.setContact(1);
			LogDAO.saveLog(fileLog);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void crawl(MyDate start, MyDate end) {
		List<MyDate> dates = getStringYear(start, end);
		for (MyDate date : dates) {
			crawl(date);
		}
	}
	

	public static void main(String[] args) {
		MyDate start = new MyDate(10, 9, 2022);
		MyDate end = new MyDate(11, 10, 2022);
//		MyDate yesterday = new MyDate(20, 9, 2022);
		Crawler c = new Crawler();
//		c.crawlToday();
//		c.crawl(yesterday);
		c.crawl(start, end);
		System.out.println("done");

	}
}

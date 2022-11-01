package extract;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import dao.ConfigDAO;
import dao.LogDAO;
import model.Config;
import model.FileLog;
import model.MyDate;
import model.Result;

public class ExtractXSDP {
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
		} catch (Exception e) {
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
		try {
//			connect db control
//			get 1 row data has date = today and status = ER or ES  from table file_log
			if (LogDAO.getLastRowExtract(true) == null) {
// 				no data
//				get 1 row data from table file_configuration
				Config config = ConfigDAO.getConfig(1);
				String src = config.getSource();
				MyDate today = new MyDate();
				String url = src.substring(0, src.indexOf("-")) + "-" + today.toDateString() + ".html";
				String fileName = "lottery-result_" + today.toDateString() + "_"
						+ src.substring(src.indexOf("//") + 2, src.indexOf(".")) + ".csv";
//				insert 1 row to table file_log(status ES)
				FileLog fileLog = new FileLog();
				fileLog.setIdConfig(1);
				fileLog.setFileName(fileName);
				fileLog.setDate(today.toTimeStamp());
				fileLog.setState("ES");
				fileLog.setContact(1);
				LogDAO.saveLog(fileLog);

//				get data from source
				String data = crawling(url);
				if (data.trim().length() != 0) {
//					has data
//					save data format csv to ftp server and local
					saveCSV(data, config.getSourceLocal() + "/" + fileName);
//					get last row from table file_log has status ES and update status ER
					LogDAO.updateStateLastRow("ES", "ER");
				} else {
//					no data
					LogDAO.updateStateLastRow("ES", "EF");
				}
			}
			// no data: end
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void crawl(MyDate date) {
		try {
//			connect db control
//			get 1 row data has date = today and status = ER or ES  from table file_log
//			get 1 row data from table file_configuration
			if (LogDAO.getLastRowExtract(true) == null) {
				// has data
				Config config = ConfigDAO.getConfig(1);
				String src = config.getSource();
				String url = src.substring(0, src.indexOf("-")) + "-" + date.toDateString() + ".html";
				String fileName = "lottery-result_" + date.toDateString() + "_"
						+ src.substring(src.indexOf("//") + 2, src.indexOf(".")) + ".csv";

//				insert 1 row to table file_log(status ES)
				FileLog fileLog = new FileLog();
				fileLog.setIdConfig(1);
				fileLog.setFileName(fileName);
				date.setTime(17);
				date.setMinute(50);
				date.setSecond(0);
				fileLog.setDate(date.toTimeStamp());
				fileLog.setState("ES");
				fileLog.setContact(1);
				LogDAO.saveLog(fileLog);

//				get data from source
				String data = crawling(url);
				if (data.trim().length() != 0) {
//					has data
//					save data format csv to ftp server and local
					saveCSV(data, config.getSourceLocal() + "/" + fileName);
//					get last row from table file_log has status ES and update status ER
					LogDAO.updateStateLastRow("ES", "ER");
				} else {
//					no data
					LogDAO.updateStateLastRow("ES", "EF");
				}
			}
			// no data: end
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
//		MyDate start = new MyDate(10, 9, 2022);
//		MyDate end = new MyDate(25, 10, 2022);
//		MyDate yesterday = new MyDate(25, 10, 2022);
		ExtractXSDP c = new ExtractXSDP();
		c.crawlToday();
//		c.crawl(yesterday);
//		c.crawl(start, end);

		JOptionPane.showMessageDialog(null, "Finish");
	}
}

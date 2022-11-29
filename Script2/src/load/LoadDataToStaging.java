package load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.DateDAO;
import dao.LogDAO;
import dao.LotteryDAO;
import dao.PrizeDAO;
import dao.ProvinceDAO;
import dao.ResultDAO;
import dao.SourceDAO;
import model.Config;
import model.Date;
import model.FileLog;
import model.Lottery;
import model.Prize;
import model.Province;
import model.Result;
import model.Source;

public class LoadDataToStaging {

	public String readFile(String path) {
		File file = new File(path);
		String result = "";
		if (file.exists()) {
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
				String line = bufferedReader.readLine();
				while (line != null) {
					result += line + "\n";
					line = bufferedReader.readLine();
				}
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.trim();
	}

	public void getDataFromCSVFile(String path) {
		File file = new File(path);
		if (file.exists()) {

		}
	}

	public void loadSourDim(String sourceCode, String sourceName, String sourceURL) {
		if (SourceDAO.getSource(sourceName) == null) {
			Source sour = new Source();
			sour.setCodeSour(sourceCode);
			sour.setName(sourceName);
			sour.setUrl(sourceURL);
			SourceDAO.addSource(sour);
		}
	}

	public List<String> loadProvinceDim(String path) {
		String fileContent = readFile(path);
		String[] lines = fileContent.split("\\n");
		List<String> provinces = new ArrayList<String>();
		for (int i = 1; i < lines.length; i++) {
			String provinceName = lines[i].split(",")[0];
			if (ProvinceDAO.getProvince(provinceName) == null) {
				Province province = new Province();
				province.setCodePro(GetCode.getCode(provinceName));
				province.setName(provinceName);
				ProvinceDAO.addProvince(province);
				provinces.add(provinceName);
			}
		}
		return provinces;
	}

	public String loadDateDim(String path) {
		String fileContent = readFile(path);
		String[] lines = fileContent.split("\\n");
		String fullDate = null;
		for (int i = 1; i < lines.length; i++) {
			fullDate = lines[i].split(",")[1];
			if (DateDAO.getDate(fullDate) == null) {
				String day = fullDate.substring(0, fullDate.lastIndexOf(" "));
				String shortDate = fullDate.substring(fullDate.lastIndexOf(" ") + 1, fullDate.length());
				String[] splitShortDate = shortDate.split("/");
				int date = Integer.parseInt(splitShortDate[0]);
				int month = Integer.parseInt(splitShortDate[1]);
				int year = Integer.parseInt(splitShortDate[2]);
				Date dateDim = new Date();
				dateDim.setFullDate(fullDate);
				dateDim.setShortDate(shortDate);
				dateDim.setDay(day);
				dateDim.setDate(date);
				dateDim.setMonth(month);
				dateDim.setYear(year);
				DateDAO.addDate(dateDim);
			}
		}
		return fullDate;
	}

	public double getPrize(String prizeName) {
		switch (prizeName) {
		case "Giải 8": {
			return 100000;
		}
		case "Giải 7": {
			return 200000;
		}
		case "Giải 6": {
			return 400000;
		}
		case "Giải 5": {
			return 1000000;
		}
		case "Giải 4": {
			return 3000000;
		}
		case "Giải 3": {
			return 10000000;
		}
		case "Giải 2": {
			return 15000000;
		}
		case "Giải 1": {
			return 30000000;
		}
		case "Giải ĐB": {
			return 2000000000;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + prizeName);
		}
	}

	public void loadPrize(String path) {
		String fileContent = readFile(path);
		String[] lines = fileContent.split("\\n");
		String[] listPrizes = lines[0].split(",");
		for (int i = 2; i < listPrizes.length; i++) {
			Prize prizeDim = new Prize();
			String prizeName = listPrizes[i];
			if (PrizeDAO.getPrize(prizeName) == null) {
				double prize = getPrize(prizeName);
				prizeDim.setCodePri(GetCode.getCode(prizeName));
				prizeDim.setName(prizeName);
				prizeDim.setPrize(prize);
				PrizeDAO.addPrize(prizeDim);
			}
		}
	}

	public void loadLottery(String path, String sourceName, String fullDate) {
		String fileContent = readFile(path);
		String[] lines = fileContent.split("\\n");
		String[] listPrizes = lines[0].split(",");
		;
		for (int i = 1; i < lines.length; i++) {
			String[] lineData = lines[i].split(",");
			String provinceName = lineData[0];
			Source source = SourceDAO.getSource(sourceName);
			Province province = ProvinceDAO.getProvince(provinceName);
			Date date = DateDAO.getDate(fullDate);
			String shortDate = fullDate.substring(fullDate.lastIndexOf(" ") + 1, fullDate.length());
			String idLot = source.getCodeSour() + "_" + shortDate.replaceAll("/", "-") + "_" + province.getCodePro();
			if (LotteryDAO.getLottery(idLot) == null) {
				Lottery lottery = new Lottery(idLot, date, source, province);
				LotteryDAO.addLottery(lottery);
				for (int j = 2; j < lineData.length; j++) {
					String prizeName = listPrizes[j];
					Prize prize = PrizeDAO.getPrize(prizeName);
					if (ResultDAO.getResult(idLot, prize.getIdPri()) == null) {
						String results = lineData[j];
						Result result = new Result(lottery, prize, results);
						ResultDAO.addResult(result);
					}
				}
			}
		}
	}

	public void loadData() {
		// connect db control
//		get all row data has date = today and status = ER  from table file_log
		List<FileLog> logs = LogDAO.getAllExtract();
//		has row
		if (logs.size() != 0) {
			// connect db staging
//			get file_name in row data has date = today and status = ER  from table file_log
			for (FileLog log : logs) {
				Config config = log.getConfig();
//				get file csv has file name = file_name from ftp server or local
				String localFilePath = config.getLocalStogrePath() + "\\" + log.getFileName();
//				save data from csv file to database staging
				loadSourDim(config.getSourceCode(), config.getSourceName(), config.getSourceUrl());
				loadProvinceDim(localFilePath);
				String fullDate = loadDateDim(localFilePath);
				loadPrize(localFilePath);
				loadLottery(localFilePath, config.getSourceName(), fullDate);
//				get last row from table file_log has status ER and update status SR
				LogDAO.setLogState(log.getId(), "SR");
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Loading...");
		LoadDataToStaging load = new LoadDataToStaging();
		load.loadData();
		System.out.println("finish");
//		JOptionPane.showMessageDialog(null, "Finish");
	}
}

package load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import dao.ConfigDAO;
import dao.LogDAO;
import dao.ProvinceDAO;
import dao.SourceDao;
import model.Config;
import model.FileLog;

public class LoadData {

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

	public void loadSourDim(String path) {
		File file = new File(path);
		if (file.exists()) {
			String fileContent = readFile(path);
			String[] lines = fileContent.split("\\n");
			String sourName = lines[0];
			String sourUrl = lines[1];
			if (SourceDao.getSource(sourName, sourUrl) == null) {
				SourceDao.addSource(sourName, sourUrl);
			}
		}
	}

	public void loadProvinceDim(String path) {
		String fileContent = readFile(path);
		String[] lines = fileContent.split("\\n");
		for (int i = 3; i < lines.length; i++) {
			String provinceName = lines[i].split(",")[0];
			if (ProvinceDAO.getProvince(provinceName) == null) {
				ProvinceDAO.addProvince(provinceName);
			}
		}
	}

	public void loadDateDim(String path) {
		String fileContent = readFile(path);
		String[] lines = fileContent.split("\\n");
		for (int i = 3; i < lines.length; i++) {
			String date = lines[i].split(",")[1];
			System.out.println(date);
		}
	}

	public void loadData() {
		System.out.println("Load");
		// connect db control
//		get all row data has date = today and status = ER  from table file_log
		List<FileLog> logs = LogDAO.getAllExtract();
//		has row
		if (logs.size() != 0) {
			// connect db staging
//			get file_name in row data has date = today and status = ER  from table file_log
			for (FileLog log : logs) {
				Config config = ConfigDAO.getConfig(1);
				String localFilePath = config.getSourceLocal() + "\\" + log.getFileName();
				loadSourDim(localFilePath);
				loadSourDim(localFilePath);
				loadProvinceDim(localFilePath);
				loadDateDim(localFilePath);
			}
		}
	}

	public static void main(String[] args) {
		LoadData load = new LoadData();
		load.loadData();
	}
}

package extract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import dao.ConfigDAO;
import dao.LogDAO;
import dao.ProvinceDAO;
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
				line = bufferedReader.readLine();
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

	public void loadProvinceDim(String path) {
		String fileContent = readFile(path);
		String[] lines = fileContent.split("\\n");
		for (int i = 0; i < lines.length; i++) {
			String provinceName = lines[i].split(",")[0];
			if (ProvinceDAO.getProvince(provinceName) == null) {
				ProvinceDAO.addProvince(provinceName);
			}
		}
	}
	
	public void loadDateDim(String path) {
		String fileContent = readFile(path);
		String[] lines = fileContent.split("\\n");
		for (int i = 0; i < lines.length; i++) {
			String date = lines[i].split(",")[1];
			System.out.println(date);
		}
	}

	public void loadData() {
		// connect db control
//		get 1 row data has date = today and status = ER  from table file_log
//		has row
		if (LogDAO.getLastRowExtract(false) != null) {
			// connect db staging
//			get file_name in row data has date = today and status = ER  from table file_log
			FileLog log = LogDAO.getLastRowExtract(false);
			Config config = ConfigDAO.getConfig(1);
			String localFilePath = config.getSourceLocal() + "\\" + log.getFileName();

			loadProvinceDim(localFilePath);
			loadDateDim(localFilePath);
		}
	}

	public static void main(String[] args) {
		LoadData load = new LoadData();
		load.loadData();
	}
}

package staging;

import java.util.List;

import dao.ConfigDAO;
import dao.LogDAO;
import dao.StagingDAO;
import model.Config;
import model.FileLog;

public class LoadToStaging {
	public static void loading() {
		Config config = ConfigDAO.getConfig(1);
		List<FileLog> fileLogs = LogDAO.getListFilesExtract();
		fileLogs.forEach(log -> {
			String url = config.getSourceLocal() + "//" + log.getFileName();
			boolean isLoad = StagingDAO.loadCsvToStaging(url);
			if (isLoad) {
				System.out.println("Loading " + url);
				LogDAO.setLogState(log.getId(), "STAGING");
			}
		});
		System.out.println("done");
	}

	public static void main(String[] args) {
		loading();
	}
}

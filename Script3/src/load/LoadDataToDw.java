package load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.Database;
import dao.DateDAO;
import dao.LogDAO;
import dao.LotteryDAO;
import dao.PrizeDAO;
import dao.ProvinceDAO;
import dao.ResultDAO;
import dao.SourceDAO;
import dao.StagingDAO;
import model.Date;
import model.FileLog;
import model.Lottery;
import model.Prize;
import model.Province;
import model.Result;
import model.Source;

public class LoadDataToDw {

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

	public int loadSourDim(Lottery lottery) {
		Source sourceInStaging = lottery.getSource();
		Source sourceInDW = SourceDAO.getSource(sourceInStaging.getName(), sourceInStaging.getUrl(),
				Database.DataWerehouse);
		if (sourceInDW == null)
			return SourceDAO.addSource(sourceInStaging, Database.DataWerehouse);
		else
			return sourceInDW.getIdSour();

	}

	public int loadProvinceDim(Lottery lottery) {
		Province provinceInStaging = lottery.getProvince();
		Province provinceInDW = ProvinceDAO.getProvince(provinceInStaging.getName(), Database.DataWerehouse);
		if (provinceInDW == null)
			return ProvinceDAO.addProvince(provinceInStaging, Database.DataWerehouse);
		else
			return provinceInDW.getIdPro();
	}

	public int loadDateDim(Lottery lottery) {
		Date dateInStaging = lottery.getDate();
		Date dateInDW = DateDAO.getDate(dateInStaging.getFullDate(), Database.DataWerehouse);
		if (dateInDW == null)
			return DateDAO.addDate(dateInStaging, Database.DataWerehouse);
		else
			return dateInDW.getIdDate();
	}

	public void loadPrize() {
		List<Prize> prizes = PrizeDAO.getAllPrizeFromStaging();
		for (Prize prize : prizes) {
			if (PrizeDAO.getPrize(prize.getName(), Database.DataWerehouse) == null) {
				PrizeDAO.addPrize(prize, Database.DataWerehouse);
			}
		}
	}

	public void loadLottery(Lottery lottery, int idSour, int idPro, int idDate) {
		Lottery lotteryInDw = LotteryDAO.getLottery(lottery.getNkIdLot(), Database.DataWerehouse);
		if (lotteryInDw == null) {
			if (lottery.getSource().getIdSour() != idSour) {
				lottery.getSource().setIdSour(idSour);
			}
			if (lottery.getProvince().getIdPro() != idPro) {
				lottery.getProvince().setIdPro(idPro);
			}
			if (lottery.getDate().getIdDate() != idDate) {
				lottery.getDate().setIdDate(idDate);
			}
			int idLot = LotteryDAO.addLottery(lottery, Database.DataWerehouse);
			lottery.setIdLot(idLot);
		} else {
			lottery = lotteryInDw;
		}
		List<Result> results = ResultDAO.getAllResultsInStaging(lottery.getNkIdLot());
		for (Result result : results) {
			result.setLottery(lottery);
			if (ResultDAO.getResult(result.getLottery().getNkIdLot(), result.getPrize().getIdPri(),
					Database.DataWerehouse) == null) {
				Prize prizeInDw = PrizeDAO.getPrize(result.getPrize().getName(), Database.DataWerehouse);
				result.setPrize(prizeInDw);
				ResultDAO.addResult(result, Database.DataWerehouse);
			}

		}
	}

	public void loadDataToDw() {
		List<FileLog> logs = LogDAO.getAllStaging();
		if (logs.size() != 0) {
			List<Lottery> lotteries = LotteryDAO.getAllLotteriesFromStaging();
			loadPrize();
			for (Lottery lottery : lotteries) {
				int idSour = loadSourDim(lottery);
				int idPro = loadProvinceDim(lottery);
				int idDate = loadDateDim(lottery);
				loadLottery(lottery, idSour, idPro, idDate);
			}
		}
		for (FileLog log : logs) {
			LogDAO.setLogState(log.getId(), "TR");
		}
		StagingDAO.deleteDateStaging();
	}

	public static void main(String[] args) {
		System.out.println("Loading...");
		LoadDataToDw load = new LoadDataToDw();
		load.loadDataToDw();
		System.out.println("finish");
//		JOptionPane.showMessageDialog(null, "Finish");
	}
}

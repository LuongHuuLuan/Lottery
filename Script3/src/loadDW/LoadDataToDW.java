package loadDW;

import java.util.ArrayList;
import java.util.List;

import dao.DateDAO;
import dao.LogDAO;
import dao.LotteryDAO;
import dao.PrizeDAO;
import dao.ProvinceDAO;
import dao.SourceDao;
import model.DateLottery;
import model.FileLog;
import model.Lottery;
import model.Prize;
import model.Province;
import model.Source;

public class LoadDataToDW {

	public void loadDataProvince() {
		try {
			ArrayList<Lottery> lotteries = LotteryDAO.getAllLotteryInStaging();
			ArrayList<Province> provinces = ProvinceDAO.getAllProvinceInDW();
			ArrayList<String> namePros = new ArrayList<String>();
			for (Province p : provinces) {
				namePros.add(p.getName());
			}
			for (int i = 0; i < lotteries.size(); i++) {
				int idPro = lotteries.get(i).getIdPro();
				Province province = ProvinceDAO.getProvinceInStaging(idPro);
				String nameProvince = province.getName();
				if (namePros.contains(nameProvince) == false) {
					ProvinceDAO.addProvinceToDaWH(nameProvince);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadDataSource() {
		try {
			ArrayList<Lottery> lotteries = LotteryDAO.getAllLotteryInStaging();
			for (int i = 0; i < lotteries.size(); i++) {
				int idSour = lotteries.get(i).getIdSour();
				Source source = SourceDao.getSourceInStaging(idSour);
				Source checkSource = SourceDao.getSourceURLInDataWH(source.getUrl());
				if (!checkSource.getUrl().equalsIgnoreCase(source.getUrl())) {
					SourceDao.addSourceToDaWH(source.getName(), source.getUrl());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadDateDate() {
		try {
			ArrayList<Lottery> lotteries = LotteryDAO.getAllLotteryInStaging();
			for (Lottery lottery : lotteries) {
				int idDate = lottery.getIdDate();
				DateLottery dateLottery = DateDAO.getDateInStaging(idDate);
				DateLottery checkDate = DateDAO.getDateInDataWH(dateLottery.getDate(), dateLottery.getMonth(),
						dateLottery.getYear());
				System.out.println(checkDate.toString());
				if (checkDate.getDate() != dateLottery.getDate() || checkDate.getMonth() != dateLottery.getMonth()
						|| checkDate.getYear() != dateLottery.getYear()) {
					DateDAO.addDateToDaWH(dateLottery.getFullDate(), dateLottery.getDay(), dateLottery.getDate(),
							dateLottery.getMonth(), dateLottery.getYear());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadDataPrize() {
		try {
			ArrayList<Prize> prizesInStaging = PrizeDAO.getAllPrizeInStaging();
			ArrayList<Prize> prizesInDataWH = PrizeDAO.getAllPrizeInDataWH();
			if (prizesInDataWH.size() == 0) {
				for (Prize prize : prizesInStaging) {
					PrizeDAO.addPrizeToDaWH(prize.getNamePri(), prize.getPrize());
				}
			} else
				for (Prize prizeS : prizesInStaging) {
					for (Prize prizeD : prizesInDataWH) {
						if (prizeS.getNamePri().equalsIgnoreCase(prizeD.getNamePri())) {
							if (prizeS.getPrize() != prizeD.getPrize()) {
								PrizeDAO.updatePrize(prizeD.getNamePri(), prizeD.getIdPri(), prizeS.getPrize());
							}
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadData() {
		// connect db control
		// get all row data has date = today and status = SR from table file_log
		List<FileLog> logs = LogDAO.getAllExtract();
		// has row
		if (logs.size() != 0) {
			// connect db staging and db warehouse
			// transform data
		}
	}

	public static void main(String[] args) {
		LoadDataToDW load = new LoadDataToDW();
//		load.loadDataProvince();
//		load.loadDataSource();
//		load.loadDataPrize();
//		load.loadDateDate();
	}
}

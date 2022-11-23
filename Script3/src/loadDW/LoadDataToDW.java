package loadDW;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectStaging;
import dao.DateDAO;
import dao.LogDAO;
import dao.LotteryDAO;
import dao.PrizeDAO;
import dao.ProvinceDAO;
import dao.ResultDAO;
import dao.SourceDao;
import dao.StagingDAO;
import model.DateLottery;
import model.FileLog;
import model.Lottery;
import model.MyDate;
import model.Prize;
import model.Province;
import model.Result;
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

	@SuppressWarnings("deprecation")
	public void loadDataResult() {
		try {
			ArrayList<Result> resultStagings = ResultDAO.getAllResultInStaging();
			int idLotDW = 0, idPriWH = 0, idDateWH = 0, idSourWH = 0, idProWH = 0;
			MyDate myDate = new MyDate();
			String resultLottery = "";
			for (Result result : resultStagings) {
				resultLottery = result.getResult();
				String namePri = PrizeDAO.getPrizeInStaging(result.getIdPri()).getNamePri();
				int idLotSG = result.getIdLot();
				ArrayList<Lottery> lotterieStaging = LotteryDAO.getAllLotteryInStaging();
				for (Lottery l : lotterieStaging) {
					if (idLotSG == l.getIdLot()) {
						String namePro = ProvinceDAO.getProvinceInStaging(l.getIdPro()).getName();
						String urlSour = SourceDao.getSourceInStaging(l.getIdSour()).getUrl();
						int date = DateDAO.getDateInStaging(l.getIdDate()).getDate();
						int month = DateDAO.getDateInStaging(l.getIdDate()).getMonth();
						int year = DateDAO.getDateInStaging(l.getIdDate()).getYear();
						ArrayList<DateLottery> dateWHs = DateDAO.getAllSourceInDW();
						for (DateLottery d : dateWHs) {
							if (d.getDate() == date && d.getMonth() == month && d.getYear() == year) {
								idDateWH = d.getIdDate();
							}
						}
						ArrayList<Source> sourceWHs = SourceDao.getAllSourceInDW();
						for (Source s : sourceWHs) {
							if (s.getUrl().equalsIgnoreCase(urlSour)) {
								idSourWH = s.getIdSour();
							}
						}
						ArrayList<Province> provinceWHs = ProvinceDAO.getAllProvinceInDW();
						for (Province p : provinceWHs) {
							if (p.getName().equalsIgnoreCase(namePro)) {
								idProWH = p.getIdPro();
							}
						}
						idLotDW = LotteryDAO.getLotteryInDataWH(idLotSG + "", idDateWH, idSourWH, idProWH);
					}
				}
				ArrayList<Prize> prizeWHs = PrizeDAO.getAllPrizeInDataWH();
				for (Prize p : prizeWHs) {
					if (p.getNamePri().equalsIgnoreCase(namePri)) {
						idPriWH = p.getIdPri();
					}
				}

				Result resultCheck = ResultDAO.getResultInDataWH(idLotDW, idPriWH, resultLottery);
				if (resultCheck.getIdLot() != idLotDW || resultCheck.getIdPri() != idPriWH
						|| !resultCheck.getResult().equals(resultLottery)) {
					ResultDAO.addResultInDataWH(idLotDW, idPriWH, resultLottery, "false",
							new Date(myDate.getYear(), myDate.getMonth(), myDate.getDay()), new Date(3000, 12, 31));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void loadDataLottery() {
		try {
			ArrayList<Lottery> lotterieStagings = LotteryDAO.getAllLotteryInStaging();
			MyDate myDate = new MyDate();
			int idDateWH = 0, idSourWH = 0, idProWH = 0;
			String nkIdLotWH = "";
			for (Lottery lottery : lotterieStagings) {
				nkIdLotWH = lottery.getIdLot() + "";
				String namePro = ProvinceDAO.getProvinceInStaging(lottery.getIdPro()).getName();
				String urlSour = SourceDao.getSourceInStaging(lottery.getIdSour()).getUrl();
				int date = DateDAO.getDateInStaging(lottery.getIdDate()).getDate();
				int month = DateDAO.getDateInStaging(lottery.getIdDate()).getMonth();
				int year = DateDAO.getDateInStaging(lottery.getIdDate()).getYear();
				ArrayList<DateLottery> dateWHs = DateDAO.getAllSourceInDW();
				for (DateLottery d : dateWHs) {
					if (d.getDate() == date && d.getMonth() == month && d.getYear() == year) {
						idDateWH = d.getIdDate();
					}
				}
				ArrayList<Source> sourceWHs = SourceDao.getAllSourceInDW();
				for (Source s : sourceWHs) {
					if (s.getUrl().equalsIgnoreCase(urlSour)) {
						idSourWH = s.getIdSour();
					}
				}
				ArrayList<Province> provinceWHs = ProvinceDAO.getAllProvinceInDW();
				for (Province p : provinceWHs) {
					if (p.getName().equalsIgnoreCase(namePro)) {
						idProWH = p.getIdPro();
					}
				}
				Lottery lotteryCheck = LotteryDAO.getLotteryInDataWH(idDateWH, idSourWH, idProWH);
				System.out.println(lotteryCheck.getIdDate() + " " + idDateWH);
				if (lotteryCheck.getIdDate() != idDateWH || lotteryCheck.getIdSour() != idSourWH
						|| lotteryCheck.getIdPro() != idProWH) {
					LotteryDAO.addLotteryToDaWH(nkIdLotWH, idDateWH, idSourWH, idProWH, "false",
							new Date(myDate.getYear(), myDate.getMonth(), myDate.getDay()), new Date(3000, 12, 31));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteDateStaging() {
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadData() {
		// connect db control
//		 get all row data has date = today and status = SR from table file_log
		List<FileLog> logs = LogDAO.getAllExtract();
//		 has row
		if (logs.size() != 0) {

//		 connect db staging and db warehouse
//		 transform data
			loadDataProvince();
			loadDataSource();
			loadDataPrize();
			loadDateDate();
			loadDataLottery();
			loadDataResult();
			StagingDAO.deleteDateStaging();
			LogDAO.updateStatus();

		}
	}

	public static void main(String[] args) {
		LoadDataToDW load = new LoadDataToDW();
		load.loadData();
		System.out.println("Done");
	}
}

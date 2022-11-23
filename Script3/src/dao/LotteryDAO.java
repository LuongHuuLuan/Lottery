package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDW;
import connection.ConnectStaging;
import model.Lottery;

public class LotteryDAO {
	// get all row in the table lottery of Staging
	public static ArrayList<Lottery> getAllLotteryInStaging() {
		ArrayList<Lottery> result = new ArrayList<Lottery>();
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			String sql = "select * from lottery";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result.add(new Lottery(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getInt(4)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// add 1 row in the table lottery of DataWH
	public static boolean addLotteryToDaWH(String nkIdLot, int idDate, int idSour, int idPro) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "INSERT INTO lottery(nk_id_lot, id_date, id_sour, id_pro) values(?,?,?,?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, nkIdLot);
			ps.setInt(2, idDate);
			ps.setInt(3, idSour);
			ps.setInt(4, idPro);
			int status = ps.executeUpdate();
			if (status > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// get 1 row the table lottery in DataWH
	public static Lottery getLotteryInDataWH(int idDate, int idSour, int idPro) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "SELECT * from lottery where id_date = ? and id_sour = ? and id_pro = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, idDate);
			preparedStatement.setInt(2, idSour);
			preparedStatement.setInt(3, idPro);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				return new Lottery("", resultset.getInt(3), resultset.getInt(4), resultset.getInt(5));//lưu ý
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Lottery("", 0, 0, 0);
	}

	// get 1 row the table lottery in DataWH
	public static int getLotteryInDataWH(String nkIdLot, int idDate, int idSour, int idPro) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "SELECT id_lot from lottery where nk_id_lot = ? and id_date = ? and id_sour = ? and id_pro = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, nkIdLot);
			preparedStatement.setInt(2, idDate);
			preparedStatement.setInt(3, idSour);
			preparedStatement.setInt(4, idPro);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				return resultset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

}

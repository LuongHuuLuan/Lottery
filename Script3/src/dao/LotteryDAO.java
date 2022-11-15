package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.ConnectStaging;
import model.Lottery;

public class LotteryDAO {
	public static void main(String[] args) {
		LotteryDAO.getAllLotteryInStaging();
	}

	// get all row in the table lottery of Staging
	public static ArrayList<Lottery> getAllLotteryInStaging() {
		ArrayList<Lottery> result = new ArrayList<Lottery>();
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			String sql = "select * from lottery";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result.add(new Lottery(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getInt(4)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

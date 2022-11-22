package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectDW;
import connection.ConnectStaging;
import model.Prize;

public class PrizeDAO {
	public static void main(String[] args) {
		System.out.println(PrizeDAO.getPrizeInStaging(2));
	}

	// get all the table prize_dim of Staging
	public static ArrayList<Prize> getAllPrizeInStaging() {
		ArrayList<Prize> result = new ArrayList<Prize>();
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			String sql = "select * from prize_dim";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result.add(new Prize(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// get all the table prize_dim of DataWWh
	public static ArrayList<Prize> getAllPrizeInDataWH() {
		ArrayList<Prize> result = new ArrayList<Prize>();
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "select * from prize_dim";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result.add(new Prize(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// get 1 row the table prize_dim of DataWWh where id=?
	public static Prize getPrizeInStaging(int id) {
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			String sql = "select * from prize_dim where id_pri = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return new Prize(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// update 1 row the table prize_dim of DataWH
	public static void updatePrize(String name, int id, int prize) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "update prize_dim set id_pri = ?, prize = ? where name = ?";
			PreparedStatement pr = connect.prepareStatement(sql);

			pr.setInt(1, id);
			pr.setInt(2, prize);
			pr.setString(3, name);
			pr.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		String sql = "UPDATE student " + "SET full_name = ?, score = ? " + "WHERE id_student = ?";
	}

	// add 1 row to the table prize_dim in DataWH
	public static boolean addPrizeToDaWH(String name, int prize) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "INSERT INTO prize_dim(name, prize) values(?,?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, prize);
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

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDW;
import connection.ConnectStaging;
import model.Province;

public class ProvinceDAO {
	public static void main(String[] args) {
		ProvinceDAO dao = new ProvinceDAO();
//		ProvinceDAO.getAllProvinceInDW();
//		ProvinceDAO.getProvinceInStaging(3);
//		ProvinceDAO.addProvinceToDaWH(3, "Alo");
	}

	// get all row in table province of DataWH
	public static ArrayList<Province> getAllProvinceInDW() {
		ArrayList<Province> result = new ArrayList<Province>();
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "select * from province_dim";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result.add(new Province(resultSet.getInt(1), resultSet.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// add 1 row to the table province in DataWH
	public static boolean addProvinceToDaWH(String name) {
		try {
			Connection connect = ConnectDW.getInstance().getConnection();
			String sql = "INSERT INTO province_dim(name) values(?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, name);
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

	// get 1 row in table province of Staging
	public static Province getProvinceInStaging(int id) {
		try {
			Connection connect = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT id_pro, name from province_dim where id_pro = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				return new Province(resultset.getInt(1), resultset.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectStaging;
import model.Date;
import model.Lottery;
import model.Province;
import model.Source;

public class LotteryDAO {
	public static void addLottery(Lottery lottery) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectStaging.getInstance().getConnection();
			connection.setAutoCommit(false);
			String sql = "INSERT INTO lottery(id_lot, id_date, id_sour, id_pro) values(?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, lottery.getIdLot());
			ps.setInt(2, lottery.getDate().getIdDate());
			ps.setInt(3, lottery.getSource().getIdSour());
			ps.setInt(4, lottery.getProvince().getIdPro());
			ps.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	public static Lottery getLottery(String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT * from lottery where id_lot = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				String idLot = rs.getString(1);
				Date date = DateDAO.getDate(rs.getInt(2));
				Source source = SourceDAO.getSource(rs.getInt(3));
				Province province = ProvinceDAO.getProvince(rs.getInt(4));
				return new Lottery(idLot, date, source, province);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
}

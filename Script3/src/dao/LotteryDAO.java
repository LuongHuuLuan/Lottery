package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDW;
import connection.ConnectStaging;
import model.Date;
import model.Lottery;
import model.Province;
import model.Source;

public class LotteryDAO {
	public static int addLottery(Lottery lottery, Database database) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			int id = -1;
			String sql;
			if (database == Database.Staging) {
				connection = ConnectStaging.getInstance().getConnection();
				sql = "INSERT INTO lottery(id_lot, id_date, id_sour, id_pro) values(?,?,?,?)";
			} else {
				connection = ConnectDW.getInstance().getConnection();
				sql = "INSERT INTO lottery(nk_id_lot, id_date, id_sour, id_pro) values(?,?,?,?)";
			}
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, lottery.getNkIdLot());
			ps.setInt(2, lottery.getDate().getIdDate());
			ps.setInt(3, lottery.getSource().getIdSour());
			ps.setInt(4, lottery.getProvince().getIdPro());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			connection.commit();
			return id;
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
		return -1;
	}

	public static List<Lottery> getAllLotteriesFromStaging() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Lottery> lotteries = new ArrayList<Lottery>();
		try {
			connection = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT * from lottery";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String idLot = rs.getString(1);
				Date date = DateDAO.getDate(rs.getInt(2), Database.Staging);
				Source source = SourceDAO.getSource(rs.getInt(3), Database.Staging);
				Province province = ProvinceDAO.getProvince(rs.getInt(4), Database.Staging);
				Lottery lottery = new Lottery(idLot, date, source, province);
				lotteries.add(lottery);
			}
			return lotteries;
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

	public static Lottery getLottery(String id, Database database) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql;
			if (database == Database.Staging) {
				connection = ConnectStaging.getInstance().getConnection();
				sql = "SELECT * from lottery where id_lot = ?";
			} else {
				connection = ConnectDW.getInstance().getConnection();
				sql = "SELECT * from lottery where nk_id_lot = ? and is_delete = 'FALSE'";
			}
			ps = connection.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (database == Database.Staging) {
					String idLot = rs.getString(1);
					Date date = DateDAO.getDate(rs.getInt(2), database);
					Source source = SourceDAO.getSource(rs.getInt(3), database);
					Province province = ProvinceDAO.getProvince(rs.getInt(4), database);
					return new Lottery(idLot, date, source, province);
				} else {
					int idLot = rs.getInt("id_lot");
					String nkIdLot = rs.getString("nk_id_lot");
					Date date = DateDAO.getDate(rs.getInt("date"), database);
					Source source = SourceDAO.getSource(rs.getInt("id_sour"), database);
					Province province = ProvinceDAO.getProvince(rs.getInt("id_pro"), database);
					return new Lottery(idLot, nkIdLot, date, source, province);
				}
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

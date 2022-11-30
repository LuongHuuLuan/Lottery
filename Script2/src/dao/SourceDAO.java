package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectStaging;
import model.Source;

public class SourceDAO {

	public static int addSource(Source source) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			int id = -1;
			connection = ConnectStaging.getInstance().getConnection();
			connection.setAutoCommit(false);
			String sql = "INSERT INTO source_dim(code_sour, name, url) values(?,?,?)";
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, source.getCodeSour());
			ps.setString(2, source.getName());
			ps.setString(3, source.getUrl());
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

	public static Source getSource(int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT id_sour, code_sour, name, url from source_dim where id_sour = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idSour = rs.getInt("id_sour");
				String codeSour = rs.getString("code_sour");
				String name = rs.getString("name");
				String url = rs.getString("url");
				return new Source(idSour, codeSour, name, url);
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

	public static Source getSource(String name, String url) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT id_sour, code_sour, name, url from source_dim where name = ? and url = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, url);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idSour = rs.getInt("id_sour");
				String codeSour = rs.getString("code_sour");
				return new Source(idSour, codeSour, name, url);
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

	public static Source getSource(String name) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectStaging.getInstance().getConnection();
			String sql = "SELECT id_sour,code_sour, name, url from source_dim where name = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				int idSour = rs.getInt("id_sour");
				String codeSour = rs.getString("code_sour");
				String url = rs.getString("url");
				return new Source(idSour, codeSour, name, url);
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

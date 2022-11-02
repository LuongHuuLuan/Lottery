package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import connection.Connect;
import model.FileLog;

public class LogDAO {
	public static boolean saveLog(FileLog fileLog) {
		try {
			Connection connect = Connect.getInstance().getConnection();
			String sql = "INSERT INTO FILE_LOG(id_config, file_name, date, state, contact) VALUES(?,?,?,?,?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, fileLog.getIdConfig());
			ps.setString(2, fileLog.getFileName());
			ps.setTimestamp(3, fileLog.getDate());
			ps.setString(4, fileLog.getState());
			ps.setInt(5, fileLog.getContact());
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static FileLog getLastRowExtract() {
		FileLog log = null;
		try {
			Connection connect = Connect.getInstance().getConnection();
			String sql = "SELECT id, id_config, file_name, date, state, contact FROM file_log WHERE day(date) = day(now()) and month(date) = month(now()) and year(date) = year(now()) and (state = 'ES' or state = 'ER')";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				int idConfig = resultSet.getInt(2);
				String fileName = resultSet.getString(3);
				Timestamp date = resultSet.getTimestamp(4);
				String state = resultSet.getString(5);
				int contact = resultSet.getInt(6);
				log = new FileLog(id, idConfig, fileName, date, state, contact);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return log;
	}

	public static List<FileLog> getAllExtract() {
		List<FileLog> logs = new ArrayList<FileLog>();
		try {
			Connection connect = Connect.getInstance().getConnection();
			String sql = "SELECT id, id_config, file_name, date, state, contact FROM file_log WHERE state = 'ER'";
			PreparedStatement ps = connect.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				int idConfig = resultSet.getInt(2);
				String fileName = resultSet.getString(3);
				Timestamp date = resultSet.getTimestamp(4);
				String state = resultSet.getString(5);
				int contact = resultSet.getInt(6);
				FileLog log = new FileLog(id, idConfig, fileName, date, state, contact);
				logs.add(log);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logs;
	}

	public static boolean updateStateLastRow(String currentState, String newState) {
		try {
			Connection connect = Connect.getInstance().getConnection();
			String sql = "UPDATE file_log SET state = ? WHERE id = (SELECT id FROM file_log WHERE state = ? ORDER BY id DESC LIMIT 1)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, newState);
			ps.setString(2, currentState);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean setLogState(int id, String state) {
		try {
			Connection connect = Connect.getInstance().getConnection();
			String sql = "UPDATE file_log SET state = ? WHERE ID = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, state);
			ps.setInt(2, id);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

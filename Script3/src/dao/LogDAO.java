package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectControl;
import model.FileLog;

public class LogDAO {
	public static void main(String[] args) {
		LogDAO.updateStatus();
	}

	public static List<FileLog> getAllExtract() {
		List<FileLog> logs = new ArrayList<FileLog>();
		try {
			Connection connect = ConnectControl.getInstance().getConnection();
			String sql = "SELECT id, id_config, file_name, date, state, contact FROM file_log WHERE state = 'SR'";
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logs;
	}

	// update table file_log SR==>>TR
	public static void updateStatus() {
		try {
			Connection connect = ConnectControl.getInstance().getConnection();
			String sql = "update file_log set state = 'TR' where state = 'SR'";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

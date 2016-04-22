package com.ed.v1.model;

import java.sql.SQLException;
import java.util.List;

import com.ed.v1.database.DatabaseHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class HistoryDaoImpl implements HistoryDao {

	private final DatabaseHelper databaseHelper;
	private RuntimeExceptionDao<HistoryInfo, Integer> historyRuntimeDao = null;

	public HistoryDaoImpl(DatabaseHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
	}

	public DatabaseHelper getDatabaseHelper() {
		return databaseHelper;
	}

	private RuntimeExceptionDao<HistoryInfo, Integer> getRuntimeExceptionDao() {
		if (historyRuntimeDao == null) {
			historyRuntimeDao = databaseHelper
					.getRuntimeExceptionDao(HistoryInfo.class);
		}
		return historyRuntimeDao;
	}

	@Override
	public List<HistoryInfo> queryByUsername(String username, boolean issuger) {
		List<HistoryInfo> list = null;
		if (username != null) {
			try {
				list = getRuntimeExceptionDao().queryBuilder()
						.orderBy("measurementTime", false).where()
						.eq("userName", username).and()
						.eq("booldsugar", issuger).query();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<HistoryInfo> queryAll() {
		// TODO Auto-generated method stub
		List<HistoryInfo> list = null;

		try {
			list = getRuntimeExceptionDao().queryBuilder().query();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public HistoryInfo getLastPressureRecord(String username) {
		List<HistoryInfo> list = null;
		if (username != null) {
			try {
				list = getRuntimeExceptionDao().queryBuilder()
						.orderBy("measurementTime", false).where()
						.eq("userName", username).and().eq("booldsugar", false)
						.query();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public void addHistoyRecord(HistoryInfo item) {
		getRuntimeExceptionDao().createIfNotExists(item);
	}

	@Override
	public HistoryInfo getLastSugerRecord(String username) {
		List<HistoryInfo> list = null;
		if (username != null) {
			try {
				list = getRuntimeExceptionDao().queryBuilder()
						.orderBy("measurementTime", false).where()
						.eq("userName", username).and().eq("booldsugar", true)
						.query();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public void cleanHistoyRecord(List<HistoryInfo> historyList) {
		getRuntimeExceptionDao().delete(historyList);
	}
}

package com.ed.v1.model;

import java.util.List;

public interface HistoryDao {

	public List<HistoryInfo> queryByUsername(String userName, boolean issuger);

	public List<HistoryInfo> queryAll();

	public HistoryInfo getLastPressureRecord(String username);

	public HistoryInfo getLastSugerRecord(String username);

	public void addHistoyRecord(HistoryInfo item);

	public void cleanHistoyRecord(List<HistoryInfo> historyList);
}

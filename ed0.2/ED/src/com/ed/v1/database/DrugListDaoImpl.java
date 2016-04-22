package com.ed.v1.database;

import java.sql.SQLException;
import java.util.List;

import com.ed.v1.model.DrugInfo;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class DrugListDaoImpl implements DrugListDao {
	
	private final DatabaseHelper databaseHelper;

    private RuntimeExceptionDao<DrugInfo, Integer> drugInfoRuntimeDao = null;
    private Dao<DrugInfo, Integer> drugInfoDao = null;

    public DrugListDaoImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;

    }

	
	/**
     * @return the databaseHelper
     */
    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao
     * for our User class. It will create it or just give the cached value.
     * RuntimeExceptionDao only through RuntimeExceptions.
     */
    private RuntimeExceptionDao<DrugInfo, Integer> getRuntimeExceptionDao() {
        if (drugInfoRuntimeDao == null) {
        	drugInfoRuntimeDao = databaseHelper.getRuntimeExceptionDao(DrugInfo.class);
        }
        return drugInfoRuntimeDao;
    }

    private Dao<DrugInfo, Integer> getDao() {
        if (drugInfoDao == null) {
            try {
            	drugInfoDao = databaseHelper.getDao(DrugInfo.class);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return drugInfoDao;
    }


	@Override
	public List<DrugInfo> queryAll() {
		// TODO Auto-generated method stub
		List<DrugInfo> list;
		list = getRuntimeExceptionDao().queryForAll();
		return list;
	}


	@Override
	public boolean updateDrug(DrugInfo drug) {
		// TODO Auto-generated method stub
		getRuntimeExceptionDao().createOrUpdate(drug);
		return true;
	}


	@Override
	public boolean deleteDrug(DrugInfo drug) {
		// TODO Auto-generated method stub
		getRuntimeExceptionDao().delete(drug);
		return true;
	}


	@Override
	public boolean addDrugInfo(DrugInfo drug) {
		// TODO Auto-generated method stub
		getRuntimeExceptionDao().createIfNotExists(drug);
		return true;
	}


	@Override
	public List<DrugInfo> queryByNo(String drugNo) {
		// TODO Auto-generated method stub
		List<DrugInfo> list = null;
        if (drugNo != null) {
            list = getRuntimeExceptionDao().queryForEq("drugNo", drugNo);
        }
        return list;
	}

    
}

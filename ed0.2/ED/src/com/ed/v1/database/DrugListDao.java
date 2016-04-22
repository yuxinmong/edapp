package com.ed.v1.database;

import java.util.List;

import com.ed.v1.model.DrugInfo;

public interface DrugListDao {
	public List<DrugInfo> queryAll();

    public boolean updateDrug(DrugInfo drug);

    public boolean deleteDrug(DrugInfo drug);

    public boolean addDrugInfo(DrugInfo drug);
    
    public List<DrugInfo> queryByNo(String drugNo);

}

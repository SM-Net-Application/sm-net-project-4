package com.sm.net.sp.view.home.user.menu.sergroups;

import com.sm.net.sp.model.Family;

import javafx.collections.ObservableList;

public interface UserMenuSerGroupsCallback {

	public abstract void updateFamiliesTable(ObservableList<Family> list);

	public abstract void updateSerGroupsTable();
}

package com.sm.net.sp.view.home.user.menu.congr;

import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;

import javafx.collections.ObservableList;

public interface UserMenuCongrListCallback {

	public abstract void updateMembersTable(ObservableList<Member> list);

	public abstract void updateMembersTable();

	public abstract void updateFamiliesTable(ObservableList<Family> list);

	public abstract void updateFamiliesTable();
}

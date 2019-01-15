package com.sm.net.sp.view.home.user.menu.congr;

import com.sm.net.sp.model.Member;

import javafx.collections.ObservableList;

public interface UserMenuCongrListCallback {

	public abstract void updateTable(ObservableList<Member> list);

	public abstract void getMembers();
}

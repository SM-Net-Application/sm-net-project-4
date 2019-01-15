package com.sm.net.sp.view.home.user.menu.users;

import com.sm.net.sp.model.User;

import javafx.collections.ObservableList;

public interface MenuUsersListCallback {

	public abstract void updateTable(ObservableList<User> listUser);
	
	public abstract void updateListUsers();

}

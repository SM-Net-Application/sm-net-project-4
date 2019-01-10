package com.sm.net.sp.view.home.user.menu.users;

public interface MenuUsersAddCallback {

	public abstract void usernameExists();

	public abstract void usernameNotExists(String userEncrypted, String passwordEncrypted);

}

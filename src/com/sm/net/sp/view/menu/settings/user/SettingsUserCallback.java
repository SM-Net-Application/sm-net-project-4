package com.sm.net.sp.view.menu.settings.user;

import java.io.IOException;

public interface SettingsUserCallback {

	public abstract void usernameExists();

	public abstract void usernameNotExists();

	public abstract void updateSettings(String username, String password, String key) throws IOException;

}

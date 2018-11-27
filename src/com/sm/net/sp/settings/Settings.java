package com.sm.net.sp.settings;

public class Settings implements SettingsInteface {

	private SettingsConf settingsConf;

	public Settings(SettingsConf settingsConf) {
		super();
		this.settingsConf = settingsConf;
	}

	public SettingsConf getSettingsConf() {
		return settingsConf;
	}

	public void setSettingsConf(SettingsConf settingsConf) {
		this.settingsConf = settingsConf;
	}

	@Override
	public void firstSet() {

	}

	@Override
	public void load() {

	}
}
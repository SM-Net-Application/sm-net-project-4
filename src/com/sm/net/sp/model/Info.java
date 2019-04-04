package com.sm.net.sp.model;

import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.settings.Settings;

import javafx.stage.Stage;

public class Info extends UpdateDataAdapter {

	private EnumActions action;
	private String key;
	private String value;
	private Settings settings;
	private Stage ownerStage;

	public static void runAction(EnumActions action, String key, String value, Settings settings, Stage ownerStage) {

		Info info = new Info();
		info.setAction(action);
		info.setKey(key);
		info.setValue(value);
		info.setSettings(settings);
		info.setOwnerStage(ownerStage);

		info.run();
	}

	public void run() {
		Actions.checkInfo(this.key, this.settings, this.ownerStage, this);
	}

	@Override
	public void checkInfo(boolean exists) {
		super.checkInfo(exists);

		switch (this.action) {
		case CHECK:

			break;
		case SAVE:

			if (exists)
				Actions.updateInfo(this.key, this.value, this.settings, this.ownerStage);
			else
				Actions.insertInfo(this.key, this.value, this.settings, this.ownerStage);

			break;
		}
	}

	public EnumActions getAction() {
		return action;
	}

	public void setAction(EnumActions action) {
		this.action = action;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}

	public static class KEYS {
		public static final String CONGR = "inf1";
		public static final String OVERSEER1 = "inf2";
		public static final String OVERSEER1WIFE = "inf3";
		public static final String OVERSEER2 = "inf4";
		public static final String OVERSEER2WIFE = "inf5";
	}

	public enum EnumActions {
		CHECK(), SAVE();
	}
}

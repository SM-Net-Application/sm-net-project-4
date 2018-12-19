package com.sm.net.sp.settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.crypto.SecretKey;

import org.ini4j.Wini;

import com.sm.net.path.PathBuilder;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;

public class Settings {

	private SettingsConf settingsConf;

	private Language language;
	private String languageName;
	private String passwordEncrypted;
	private String databaseUrl;
	private String databaseKeyEncrypted;
	private SecretKey applicationKey;

	public Settings(SettingsConf settingsConf) {
		super();
		this.settingsConf = settingsConf;
		this.applicationKey = null;
	}

	public void setDefaultSettings() {
		this.databaseUrl = "";
		this.databaseKeyEncrypted = "";
	}

	public void save() throws IOException {

		Wini ini = settingsConf.getIni();
		ini.add(Meta.Settings.SECTION_APPLICATION, Meta.Settings.KEY_LANGUAGE, languageName);
		ini.add(Meta.Settings.SECTION_APPLICATION, Meta.Settings.KEY_PASSWORD, passwordEncrypted);
		ini.add(Meta.Settings.SECTION_DATABASE, Meta.Settings.KEY_URL, databaseUrl);
		ini.add(Meta.Settings.SECTION_DATABASE, Meta.Settings.KEY_DB_KEY, databaseKeyEncrypted);
		ini.store();
	}

	public void load() throws FileNotFoundException, IOException {

		Wini ini = settingsConf.getIni();

		this.languageName = ini.get(Meta.Settings.SECTION_APPLICATION, Meta.Settings.KEY_LANGUAGE, String.class);
		this.language = languageLoad();

		this.passwordEncrypted = ini.get(Meta.Settings.SECTION_APPLICATION, Meta.Settings.KEY_PASSWORD, String.class);
		this.databaseUrl = ini.get(Meta.Settings.SECTION_DATABASE, Meta.Settings.KEY_URL, String.class);
		this.databaseKeyEncrypted = ini.get(Meta.Settings.SECTION_DATABASE, Meta.Settings.KEY_DB_KEY, String.class);
	}

	private Language languageLoad() throws FileNotFoundException, IOException {

		String languagePathPropertiesFile = PathBuilder.concatFile(Meta.Languages.getDirectory().getAbsolutePath(),
				this.languageName);
		File languagePropertiesFile = new File(languagePathPropertiesFile);
		return new Language(languagePropertiesFile);
	}

	public SettingsConf getSettingsConf() {
		return settingsConf;
	}

	public void setSettingsConf(SettingsConf settingsConf) {
		this.settingsConf = settingsConf;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
		setLanguageName(language.getFileProperties().getName());
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getPasswordEncrypted() {
		return passwordEncrypted;
	}

	public void setPasswordEncrypted(String passwordEncrypted) {
		this.passwordEncrypted = passwordEncrypted;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}

	public String getDatabaseKeyEncrypted() {
		return databaseKeyEncrypted;
	}

	public void setDatabaseKeyEncrypted(String databaseKeyEncrypted) {
		this.databaseKeyEncrypted = databaseKeyEncrypted;
	}

	public SecretKey getApplicationKey() {
		return applicationKey;
	}

	public void setApplicationKey(SecretKey applicationKey) {
		this.applicationKey = applicationKey;
	}
}
package com.sm.net.sp.settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.crypto.SecretKey;

import org.ini4j.Wini;

import com.sm.net.path.PathBuilder;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.util.Crypt;

public class Settings {

	private SettingsConf settingsConf;

	private Language language;
	private String languageName;
	private SecretKey applicationKey;
	private String passwordEncrypted;
	private String databaseUrlEncrypted;
	private String databaseUrl;
	private String databaseKeyEncrypted;
	private String usernameEncrypted;
	private String userPasswordEncrypted;
	private String userPasswordMonitorEncrypted;

	public Settings(SettingsConf settingsConf) {
		super();
		this.settingsConf = settingsConf;
		this.applicationKey = null;
	}

	public void setDefaultSettings() {

		this.databaseUrlEncrypted = "";
		this.databaseUrl = "";
		this.databaseKeyEncrypted = "";
		this.usernameEncrypted = "";
		this.userPasswordEncrypted = "";
		this.userPasswordMonitorEncrypted = "";
	}

	public void save() throws IOException {

		Wini ini = settingsConf.getIni();
		ini.add(Meta.Settings.SECTION_APPLICATION, Meta.Settings.KEY_LANGUAGE, languageName);
		ini.add(Meta.Settings.SECTION_APPLICATION, Meta.Settings.KEY_PASSWORD, passwordEncrypted);
		ini.add(Meta.Settings.SECTION_DATABASE, Meta.Settings.KEY_URL, databaseUrlEncrypted);
		ini.add(Meta.Settings.SECTION_DATABASE, Meta.Settings.KEY_DB_KEY, databaseKeyEncrypted);
		ini.add(Meta.Settings.SECTION_USER, Meta.Settings.KEY_USERNAME, usernameEncrypted);
		ini.add(Meta.Settings.SECTION_USER, Meta.Settings.KEY_USERPASS, userPasswordEncrypted);
		ini.add(Meta.Settings.SECTION_USER, Meta.Settings.KEY_USERMONITOR, userPasswordMonitorEncrypted);
		ini.store();
	}

	public void load() throws FileNotFoundException, IOException {

		Wini ini = settingsConf.getIni();

		this.languageName = ini.get(Meta.Settings.SECTION_APPLICATION, Meta.Settings.KEY_LANGUAGE, String.class);
		this.language = languageLoad();

		this.passwordEncrypted = ini.get(Meta.Settings.SECTION_APPLICATION, Meta.Settings.KEY_PASSWORD, String.class);

		this.databaseUrlEncrypted = ini.get(Meta.Settings.SECTION_DATABASE, Meta.Settings.KEY_URL, String.class);
		this.databaseKeyEncrypted = ini.get(Meta.Settings.SECTION_DATABASE, Meta.Settings.KEY_DB_KEY, String.class);

		this.usernameEncrypted = ini.get(Meta.Settings.SECTION_USER, Meta.Settings.KEY_USERNAME, String.class);
		this.userPasswordEncrypted = ini.get(Meta.Settings.SECTION_USER, Meta.Settings.KEY_USERPASS, String.class);
		this.userPasswordMonitorEncrypted = ini.get(Meta.Settings.SECTION_USER, Meta.Settings.KEY_USERMONITOR,
				String.class);

		if (this.userPasswordMonitorEncrypted == null)
			this.userPasswordMonitorEncrypted = "";

		if (this.databaseUrlEncrypted != null)
			if (!this.databaseUrlEncrypted.isEmpty()) {
				SecretKey applicationKey = this.getApplicationKey();
				if (this.applicationKey != null)
					this.databaseUrl = Crypt.decrypt(this.databaseUrlEncrypted, applicationKey);
			}
	}

	private Language languageLoad() throws FileNotFoundException, IOException {

		String languagePathPropertiesFile = PathBuilder.concatFile(Meta.Languages.getDirectory().getAbsolutePath(),
				this.languageName);
		File languagePropertiesFile = new File(languagePathPropertiesFile);
		return new Language(languagePropertiesFile);
	}

	public SecretKey getDatabaseSecretKey() {

		if (!databaseKeyEncrypted.trim().isEmpty())
			if (applicationKey != null) {
				String databaseKey = Crypt.decrypt(databaseKeyEncrypted, applicationKey);
				if (databaseKey != null)
					return Crypt.generateKey(databaseKey);
			}

		return null;
	}

	public String getUsernameEncrypted() {

		if (!usernameEncrypted.trim().isEmpty()) {

			SecretKey applicationKey = getApplicationKey();
			if (applicationKey != null) {

				String decryptedUsername = Crypt.decrypt(usernameEncrypted, applicationKey);
				if (decryptedUsername != null) {

					SecretKey databaseSecretKey = getDatabaseSecretKey();
					if (databaseSecretKey != null)
						return Crypt.encrypt(decryptedUsername, databaseSecretKey);
				}
			}
		}

		return null;
	}

	public String getUserPasswordReEncrypted() {

		if (!userPasswordEncrypted.trim().isEmpty())
			if (applicationKey != null) {
				String userPasswordDecrypted = Crypt.decrypt(userPasswordEncrypted, applicationKey);
				if (userPasswordDecrypted != null) {
					SecretKey databaseSecretKey = getDatabaseSecretKey();
					if (databaseSecretKey != null)
						return Crypt.encrypt(userPasswordDecrypted, databaseSecretKey);
				}
			}

		return null;
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

	public String getDatabaseUrlEncrypted() {
		return databaseUrlEncrypted;
	}

	public void setDatabaseUrlEncrypted(String databaseUrlEncrypted) {
		this.databaseUrlEncrypted = databaseUrlEncrypted;
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

	public String getUsername() {
		return usernameEncrypted;
	}

	public void setUsername(String username) {
		this.usernameEncrypted = username;
	}

	public String getUserPasswordEncrypted() {
		return userPasswordEncrypted;
	}

	public void setUserPasswordEncrypted(String userPasswordEncrypted) {
		this.userPasswordEncrypted = userPasswordEncrypted;
	}

	public String getUserPasswordMonitorEncrypted() {
		return userPasswordMonitorEncrypted;
	}

	public void setUserPasswordMonitorEncrypted(String userPasswordMonitorEncrypted) {
		this.userPasswordMonitorEncrypted = userPasswordMonitorEncrypted;
	}
}
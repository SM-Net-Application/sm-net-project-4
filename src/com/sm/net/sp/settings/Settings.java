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
	private String databaseUrl;
	private String databaseKeyEncrypted;
	private String usernameEncrypted;
	private String userPasswordEncrypted;
	private String userPasswordMonitorEncrypted;

	private String mysqlHostEncrypted;
	private String mysqlDBNameEncrypted;
	private String mysqlDBUserNameEncrypted;
	private String mysqlDBUserPasswordEncrypted;

	public Settings(SettingsConf settingsConf) {
		super();
		this.settingsConf = settingsConf;
		this.applicationKey = null;
	}

	public void setDefaultSettings() {

		this.databaseUrl = "";
		this.databaseKeyEncrypted = "";
		this.usernameEncrypted = "";
		this.userPasswordEncrypted = "";
		this.userPasswordMonitorEncrypted = "";

		this.mysqlHostEncrypted = "";
		this.mysqlDBNameEncrypted = "";
		this.mysqlDBUserNameEncrypted = "";
		this.mysqlDBUserPasswordEncrypted = "";
	}

	public void save() throws IOException {

		Wini ini = settingsConf.getIni();
		ini.add(Meta.Settings.SECTION_APPLICATION, Meta.Settings.KEY_LANGUAGE, languageName);
		ini.add(Meta.Settings.SECTION_APPLICATION, Meta.Settings.KEY_PASSWORD, passwordEncrypted);
		ini.add(Meta.Settings.SECTION_DATABASE, Meta.Settings.KEY_URL, databaseUrl);
		ini.add(Meta.Settings.SECTION_DATABASE, Meta.Settings.KEY_DB_KEY, databaseKeyEncrypted);
		ini.add(Meta.Settings.SECTION_USER, Meta.Settings.KEY_USERNAME, usernameEncrypted);
		ini.add(Meta.Settings.SECTION_USER, Meta.Settings.KEY_USERPASS, userPasswordEncrypted);
		ini.add(Meta.Settings.SECTION_USER, Meta.Settings.KEY_USERMONITOR, userPasswordMonitorEncrypted);

		ini.add(Meta.Settings.SECTION_MYSQL, Meta.Settings.KEY_HOST, mysqlHostEncrypted);
		ini.add(Meta.Settings.SECTION_MYSQL, Meta.Settings.KEY_DBNAME, mysqlDBNameEncrypted);
		ini.add(Meta.Settings.SECTION_MYSQL, Meta.Settings.KEY_DBUSERNAME, mysqlDBUserNameEncrypted);
		ini.add(Meta.Settings.SECTION_MYSQL, Meta.Settings.KEY_DBPASSWORD, mysqlDBUserPasswordEncrypted);

		ini.store();
	}

	public void load() throws FileNotFoundException, IOException {

		Wini ini = settingsConf.getIni();

		this.languageName = ini.get(Meta.Settings.SECTION_APPLICATION, Meta.Settings.KEY_LANGUAGE, String.class);
		this.language = languageLoad();

		this.passwordEncrypted = ini.get(Meta.Settings.SECTION_APPLICATION, Meta.Settings.KEY_PASSWORD, String.class);

		this.databaseUrl = ini.get(Meta.Settings.SECTION_DATABASE, Meta.Settings.KEY_URL, String.class);
		this.databaseKeyEncrypted = ini.get(Meta.Settings.SECTION_DATABASE, Meta.Settings.KEY_DB_KEY, String.class);

		this.usernameEncrypted = ini.get(Meta.Settings.SECTION_USER, Meta.Settings.KEY_USERNAME, String.class);
		this.userPasswordEncrypted = ini.get(Meta.Settings.SECTION_USER, Meta.Settings.KEY_USERPASS, String.class);
		this.userPasswordMonitorEncrypted = ini.get(Meta.Settings.SECTION_USER, Meta.Settings.KEY_USERMONITOR,
				String.class);

		if (this.userPasswordMonitorEncrypted == null)
			this.userPasswordMonitorEncrypted = "";

		this.mysqlHostEncrypted = ini.get(Meta.Settings.SECTION_MYSQL, Meta.Settings.KEY_HOST, String.class);
		if (this.mysqlHostEncrypted == null)
			this.mysqlHostEncrypted = "";

		this.mysqlDBNameEncrypted = ini.get(Meta.Settings.SECTION_MYSQL, Meta.Settings.KEY_DBNAME, String.class);
		if (this.mysqlDBNameEncrypted == null)
			this.mysqlDBNameEncrypted = "";

		this.mysqlDBUserNameEncrypted = ini.get(Meta.Settings.SECTION_MYSQL, Meta.Settings.KEY_DBUSERNAME,
				String.class);
		if (this.mysqlDBUserNameEncrypted == null)
			this.mysqlDBUserNameEncrypted = "";

		this.mysqlDBUserPasswordEncrypted = ini.get(Meta.Settings.SECTION_MYSQL, Meta.Settings.KEY_DBPASSWORD,
				String.class);
		if (this.mysqlDBUserPasswordEncrypted == null)
			this.mysqlDBUserPasswordEncrypted = "";
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

	public String getDatabaseUrl() {

		if (this.applicationKey != null)
			if (this.databaseUrl != null)
				if (!this.databaseUrl.isEmpty())
					return Crypt.decrypt(this.databaseUrl, this.applicationKey);

		return "";
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

	public String getMysqlHostEncrypted() {
		return mysqlHostEncrypted;
	}

	public String getMysqlHostDecrypted() {

		if (this.applicationKey != null)
			if (this.mysqlHostEncrypted != null)
				if (!this.mysqlHostEncrypted.isEmpty())
					return Crypt.decrypt(this.mysqlHostEncrypted, this.applicationKey);

		return "";
	}

	public void setMysqlHostEncrypted(String mysqlHostEncrypted) {
		this.mysqlHostEncrypted = mysqlHostEncrypted;
	}

	public String getMysqlDBNameEncrypted() {
		return mysqlDBNameEncrypted;
	}

	public String getMysqlDBNameDecrypted() {

		if (this.applicationKey != null)
			if (this.mysqlDBNameEncrypted != null)
				if (!this.mysqlDBNameEncrypted.isEmpty())
					return Crypt.decrypt(this.mysqlDBNameEncrypted, this.applicationKey);

		return "";
	}

	public void setMysqlDBNameEncrypted(String mysqlDBNameEncrypted) {
		this.mysqlDBNameEncrypted = mysqlDBNameEncrypted;
	}

	public String getMysqlDBUserNameEncrypted() {
		return mysqlDBUserNameEncrypted;
	}

	public String getMysqlDBUserNameDerypted() {

		if (this.applicationKey != null)
			if (this.mysqlDBUserNameEncrypted != null)
				if (!this.mysqlDBUserNameEncrypted.isEmpty())
					return Crypt.decrypt(this.mysqlDBUserNameEncrypted, this.applicationKey);

		return "";
	}

	public void setMysqlDBUserNameEncrypted(String mysqlDBUserNameEncrypted) {
		this.mysqlDBUserNameEncrypted = mysqlDBUserNameEncrypted;
	}

	public String getMysqlDBUserPasswordEncrypted() {
		return mysqlDBUserPasswordEncrypted;
	}

	public String getMysqlDBUserPasswordDecrypted() {

		if (this.applicationKey != null)
			if (this.mysqlDBUserPasswordEncrypted != null)
				if (!this.mysqlDBUserPasswordEncrypted.isEmpty())
					return Crypt.decrypt(this.mysqlDBUserPasswordEncrypted, this.applicationKey);

		return "";
	}

	public void setMysqlDBUserPasswordEncrypted(String mysqlDBUserPasswordEncrypted) {
		this.mysqlDBUserPasswordEncrypted = mysqlDBUserPasswordEncrypted;
	}
}
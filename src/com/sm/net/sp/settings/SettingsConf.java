package com.sm.net.sp.settings;

import java.io.File;
import java.io.IOException;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.sm.net.util.MyApp;

public class SettingsConf {

	public static final String FILE_EXT = ".ini";

	private String folderName;
	private String fileName;
	private File iniFile;
	private Wini ini;

	public SettingsConf(String folderName, String fileName) throws InvalidFileFormatException, IOException {
		super();

		this.folderName = folderName;
		settings(fileName);
	}

	public SettingsConf(String fileName) throws InvalidFileFormatException, IOException {
		this.folderName = "";
		settings(fileName);
	}

	private void settings(String fileName) throws InvalidFileFormatException, IOException {

		this.fileName = fileName;
		this.iniFile = MyApp.getMyAppFile(folderName, fileNameExt(fileName), true);
		this.ini = new Wini(this.iniFile);
	}

	private String fileNameExt(String fileName) {
		return fileName + FILE_EXT;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Wini getIni() {
		return ini;
	}

	public void setIni(Wini ini) {
		this.ini = ini;
	}

	public File getIniFile() {
		return iniFile;
	}

	public void setIniFile(File iniFile) {
		this.iniFile = iniFile;
	}

}

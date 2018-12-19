package com.sm.net.sp.settings;

import java.io.File;
import java.io.IOException;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

public class SettingsConf {

	private File iniFile;
	private Wini ini;

	public SettingsConf(File file) throws InvalidFileFormatException, IOException {

		this.iniFile = file;
		this.ini = new Wini(file);
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

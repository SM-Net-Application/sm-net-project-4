package com.sm.net.sp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PDFDest {

	private IntegerProperty spInfID;
	private StringProperty spInf1;
	private StringProperty spInf2;

	public PDFDest(int spInfID, String spInf1, String spInf2) {

		this.spInfID = new SimpleIntegerProperty(spInfID);
		this.spInf1 = new SimpleStringProperty(spInf1);
		this.spInf2 = new SimpleStringProperty(spInf2);
	}

	public PDFDest(String spInf1, String spInf2) {

		this.spInf1 = new SimpleStringProperty(spInf1);
		this.spInf2 = new SimpleStringProperty(spInf2);
	}

	public IntegerProperty getSpInfIDProperty() {
		return spInfID;
	}

	public Integer getSpInfID() {
		return spInfID.get();
	}

	public void setSpInfIDProperty(IntegerProperty spInfID) {
		this.spInfID = spInfID;
	}

	public void setSpInfID(Integer spInfID) {
		this.spInfID.set(spInfID);
	}

	public StringProperty getSpInf1Property() {
		return spInf1;
	}

	public String getSpInf1() {
		return spInf1.get();
	}

	public void setSpInf1Property(StringProperty spInf1) {
		this.spInf1 = spInf1;
	}

	public void setSpInf1(String spInf1) {
		this.spInf1.set(spInf1);
	}

	public StringProperty getSpInf2Property() {
		return spInf2;
	}

	public String getSpInf2() {
		return spInf2.get();
	}

	public void setSpInf2Property(StringProperty spInf2) {
		this.spInf2 = spInf2;
	}

	public void setSpInf2(String spInf2) {
		this.spInf2.set(spInf2);
	}

}

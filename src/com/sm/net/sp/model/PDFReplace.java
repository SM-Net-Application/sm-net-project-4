package com.sm.net.sp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PDFReplace {

	private IntegerProperty spInfID;
	private IntegerProperty spInf1;
	private StringProperty spInf2;
	private StringProperty spInf3;

	public PDFReplace(int spInfID, int spInf1, String spInf2, String spInf3) {

		this.spInfID = new SimpleIntegerProperty(spInfID);
		this.spInf1 = new SimpleIntegerProperty(spInf1);
		this.spInf2 = new SimpleStringProperty(spInf2);
		this.spInf3 = new SimpleStringProperty(spInf3);
	}

	public PDFReplace(int spInf1, String spInf2, String spInf3) {

		this.spInf1 = new SimpleIntegerProperty(spInf1);
		this.spInf2 = new SimpleStringProperty(spInf2);
		this.spInf3 = new SimpleStringProperty(spInf3);
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

	public IntegerProperty getSpInf1Property() {
		return spInf1;
	}

	public Integer getSpInf1() {
		return spInf1.get();
	}

	public void setSpInf1Property(IntegerProperty spInf1) {
		this.spInf1 = spInf1;
	}

	public void setSpInf1(Integer spInf1) {
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

	public StringProperty getSpInf3Property() {
		return spInf3;
	}

	public String getSpInf3() {
		return spInf3.get();
	}

	public void setSpInf3Property(StringProperty spInf3) {
		this.spInf3 = spInf3;
	}

	public void setSpInf3(String spInf3) {
		this.spInf3.set(spInf3);
	}
}

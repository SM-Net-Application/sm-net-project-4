package com.sm.net.sp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PostNews {

	private IntegerProperty spInfID;
	private IntegerProperty spInf1;
	private StringProperty spInf2;
	private StringProperty spInf3;
	private StringProperty spInf4;
	private StringProperty spInf5;
	private StringProperty spInf6;
	private IntegerProperty spInf7;
	private IntegerProperty spInf8;

	public PostNews(Integer spInfID, Integer spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String spInf6, Integer spInf7, Integer spInf8) {
		super();

		this.spInfID = new SimpleIntegerProperty(spInfID);
		this.spInf1 = new SimpleIntegerProperty(spInf1);
		this.spInf2 = new SimpleStringProperty(spInf2);
		this.spInf3 = new SimpleStringProperty(spInf3);
		this.spInf4 = new SimpleStringProperty(spInf4);
		this.spInf5 = new SimpleStringProperty(spInf5);
		this.spInf6 = new SimpleStringProperty(spInf6);
		this.spInf7 = new SimpleIntegerProperty(spInf7);
		this.spInf8 = new SimpleIntegerProperty(spInf8);
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

	public StringProperty getSpInf4Property() {
		return spInf4;
	}

	public String getSpInf4() {
		return spInf4.get();
	}

	public void setSpInf4Property(StringProperty spInf4) {
		this.spInf4 = spInf4;
	}

	public void setSpInf4(String spInf4) {
		this.spInf4.set(spInf4);
	}

	public StringProperty getSpInf5Property() {
		return spInf5;
	}

	public String getSpInf5() {
		return spInf5.get();
	}

	public void setSpInf5Property(StringProperty spInf5) {
		this.spInf5 = spInf5;
	}

	public void setSpInf5(String spInf5) {
		this.spInf5.set(spInf5);
	}

	public StringProperty getSpInf6Property() {
		return spInf6;
	}

	public String getSpInf6() {
		return spInf6.get();
	}

	public void setSpInf6Property(StringProperty spInf6) {
		this.spInf6 = spInf6;
	}

	public void setSpInf6(String spInf6) {
		this.spInf6.set(spInf6);
	}

	public IntegerProperty getSpInf7Property() {
		return spInf7;
	}

	public Integer getSpInf7() {
		return spInf7.get();
	}

	public void setSpInf7Property(IntegerProperty spInf7) {
		this.spInf7 = spInf7;
	}

	public void setSpInf7(Integer spInf7) {
		this.spInf7.set(spInf7);
	}

	public IntegerProperty getSpInf8Property() {
		return spInf8;
	}

	public Integer getSpInf8() {
		return spInf8.get();
	}

	public void setSpInf8Property(IntegerProperty spInf8) {
		this.spInf8 = spInf8;
	}

	public void setSpInf8(Integer spInf8) {
		this.spInf8.set(spInf8);
	}

}

package com.sm.net.sp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Song {

	private IntegerProperty number;
	private StringProperty title;

	public Song(int number, String title) {
		this.number = new SimpleIntegerProperty(number);
		this.title = new SimpleStringProperty(title);
	}

	@Override
	public String toString() {
		return this.getNumber() + " " + this.getTitle();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (obj instanceof Song) {

			Song song = (Song) obj;

			if ((this.getNumber().intValue() == song.getNumber().intValue()) && this.getTitle().equals(song.getTitle()))
				return true;
		}

		return false;

	}

	public IntegerProperty getNumberProperty() {
		return number;
	}

	public Integer getNumber() {
		return number.get();
	}

	public StringProperty getTitleProperty() {
		return title;
	}

	public String getTitle() {
		return title.get();
	}

	public void setNumberProperty(IntegerProperty number) {
		this.number = number;
	}

	public void setNumber(Integer number) {
		this.number.set(number);
	}

	public void setTitleProperty(StringProperty title) {
		this.title = title;
	}

	public void setTitle(String title) {
		this.title.set(title);
	}
}

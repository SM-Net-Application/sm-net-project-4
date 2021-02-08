package com.sm.net.sp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PostImportNews {

	private StringProperty dest;
	private StringProperty title;
	private StringProperty text;

	public PostImportNews(String dest, String title, String text) {
		super();
		this.dest = new SimpleStringProperty(dest);
		this.title = new SimpleStringProperty(title);
		this.text = new SimpleStringProperty(text);
	}

	@Override
	public String toString() {
		return this.getTitle();
	}

	public StringProperty getDestProperty() {
		return dest;
	}

	public String getDest() {
		return dest.get();
	}

	public void setDestProperty(StringProperty dest) {
		this.dest = dest;
	}

	public void setDest(String dest) {
		this.dest.set(dest);
	}

	public StringProperty getTitleProperty() {
		return title;
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitleProperty(StringProperty title) {
		this.title = title;
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public StringProperty getTextProperty() {
		return text;
	}

	public String getText() {
		return text.get();
	}

	public void setTextProperty(StringProperty text) {
		this.text = text;
	}

	public void setText(String text) {
		this.text.set(text);
	}
}

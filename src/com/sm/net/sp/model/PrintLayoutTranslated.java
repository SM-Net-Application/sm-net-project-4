package com.sm.net.sp.model;

public class PrintLayoutTranslated {

	private EnumPrintLayouts printLayout;
	private String translatedName;

	public PrintLayoutTranslated(EnumPrintLayouts printLayout, String translatedName) {
		super();
		this.printLayout = printLayout;
		this.translatedName = translatedName;
	}

	public PrintLayoutTranslated(EnumPrintLayouts printLayout, String translatedName, SerGroup sg) {
		super();
		this.printLayout = printLayout;
		this.translatedName = String.format(translatedName, sg.getSpInf1Decrypted());
	}

	@Override
	public String toString() {
		return this.getTranslatedName();
	}

	public EnumPrintLayouts getPrintLayout() {
		return printLayout;
	}

	public void setPrintLayout(EnumPrintLayouts printLayout) {
		this.printLayout = printLayout;
	}

	public String getTranslatedName() {
		return translatedName;
	}

	public void setTranslatedName(String translatedName) {
		this.translatedName = translatedName;
	}

}

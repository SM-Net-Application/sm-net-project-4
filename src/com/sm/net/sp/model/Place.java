package com.sm.net.sp.model;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import com.sm.net.sp.view.home.user.menu.places.PlacesAdd;
import com.sm.net.util.Crypt;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Place {

	private IntegerProperty id;
	private ObjectProperty<EnumPlaceType> type;
	private StringProperty descr;
	private StringProperty street;
	private StringProperty num;
	private StringProperty postCode;
	private StringProperty city;
	private StringProperty county;
	private StringProperty country;
	private StringProperty coord;
	private BooleanProperty def;

	public Place(EnumPlaceType placeType, String descr, String street, String num, String postCode, String city,
			String county, String country, String coord, boolean def) {

		this.type = new SimpleObjectProperty<>(placeType);
		this.descr = new SimpleStringProperty(descr);
		this.street = new SimpleStringProperty(street);
		this.num = new SimpleStringProperty(num);
		this.postCode = new SimpleStringProperty(postCode);
		this.city = new SimpleStringProperty(city);
		this.county = new SimpleStringProperty(county);
		this.country = new SimpleStringProperty(country);
		this.coord = new SimpleStringProperty(coord);
		this.def = new SimpleBooleanProperty(def);
	}

	public Place(int spPlaceID, EnumPlaceType placeType, String descr, String street, String num, String postCode,
			String city, String county, String country, String coord, boolean def) {

		this(placeType, descr, street, num, postCode, city, county, country, coord, def);

		this.id = new SimpleIntegerProperty(spPlaceID);
	}

	public static Place newInstanceByView(PlacesAdd view, SecretKey key) {

		EnumPlaceType placeType = view.getTypeComboBox().getSelectionModel().getSelectedItem();
		String descr = view.getDescrTextField().getText();
		String street = view.getStreetTextField().getText();
		String num = view.getNumTextField().getText();
		String postCode = view.getPostCodeTextField().getText();
		String city = view.getCityTextField().getText();
		String county = view.getCountyTextField().getText();
		String country = view.getCountryTextField().getText();
		String coord = view.getCoordTextField().getText();
		boolean def = view.getDefaultCheckBox().isSelected();

		descr = Crypt.encrypt(descr, key);
		street = Crypt.encrypt(street, key);
		num = Crypt.encrypt(num, key);
		postCode = Crypt.encrypt(postCode, key);
		city = Crypt.encrypt(city, key);
		county = Crypt.encrypt(county, key);
		country = Crypt.encrypt(country, key);
		coord = Crypt.encrypt(coord, key);

		return new Place(placeType, descr, street, num, postCode, city, county, country, coord, def);
	}

	public static Place newInstanceByJSONObject(JSONObject json, SecretKey secretKey) {

		int spPlaceID = json.getInt("spPlaceID");
		int type = json.getInt("spInf1");
		String descr = json.getString("spInf2");
		String street = json.getString("spInf3");
		String num = json.getString("spInf4");
		String postCode = json.getString("spInf5");
		String city = json.getString("spInf6");
		String county = json.getString("spInf7");
		String country = json.getString("spInf8");
		String coord = json.getString("spInf9");
		boolean def = json.getInt("spInf10") == 1;

		descr = Crypt.decrypt(descr, secretKey);
		street = Crypt.decrypt(street, secretKey);
		num = Crypt.decrypt(num, secretKey);
		postCode = Crypt.decrypt(postCode, secretKey);
		city = Crypt.decrypt(city, secretKey);
		county = Crypt.decrypt(county, secretKey);
		country = Crypt.decrypt(country, secretKey);
		coord = Crypt.decrypt(coord, secretKey);

		EnumPlaceType placeType = EnumPlaceType.valueByID(type);

		return new Place(spPlaceID, placeType, descr, street, num, postCode, city, county, country, coord, def);
	}

	public IntegerProperty getId() {
		return id;
	}

	public void setId(IntegerProperty id) {
		this.id = id;
	}

	public StringProperty getDescr() {
		return descr;
	}

	public void setDescr(StringProperty descr) {
		this.descr = descr;
	}

	public StringProperty getStreet() {
		return street;
	}

	public void setStreet(StringProperty street) {
		this.street = street;
	}

	public StringProperty getNum() {
		return num;
	}

	public void setNum(StringProperty num) {
		this.num = num;
	}

	public StringProperty getPostCode() {
		return postCode;
	}

	public void setPostCode(StringProperty postCode) {
		this.postCode = postCode;
	}

	public StringProperty getCity() {
		return city;
	}

	public void setCity(StringProperty city) {
		this.city = city;
	}

	public StringProperty getCounty() {
		return county;
	}

	public void setCounty(StringProperty county) {
		this.county = county;
	}

	public StringProperty getCountry() {
		return country;
	}

	public void setCountry(StringProperty country) {
		this.country = country;
	}

	public StringProperty getCoord() {
		return coord;
	}

	public void setCoord(StringProperty coord) {
		this.coord = coord;
	}

	public BooleanProperty getDef() {
		return def;
	}

	public void setDef(BooleanProperty def) {
		this.def = def;
	}

	public ObjectProperty<EnumPlaceType> getType() {
		return type;
	}

	public void setType(ObjectProperty<EnumPlaceType> type) {
		this.type = type;
	}
}

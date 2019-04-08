package com.sm.net.sp.model;

import org.json.JSONObject;

import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.stage.Stage;

public class Info extends UpdateDataAdapter {

	private String congr;
	private String overseer1;
	private String overseer1wife;
	private String overseer2;
	private String overseer2wife;

	private String dayMidweekMeeting;
	private String dayWeekendMeeting;
	private String hourMidweekMeeting;
	private String minuteMidweekMeeting;
	private String hourWeekendMeeting;
	private String minuteWeekendMeeting;
	private String kingdomHallLocationStreet;
	private String kingdomHallLocationNumber;
	private String kingdomHallLocationPostcode;
	private String kingdomHallLocationCity;

	private EnumActions action;
	private String key;
	private String value;
	private Settings settings;
	private Stage ownerStage;

	public static void runAction(EnumActions action, String key, String value, Settings settings, Stage ownerStage) {

		Info info = new Info();
		info.setAction(action);
		info.setKey(key);
		info.setValue(value);
		info.setSettings(settings);
		info.setOwnerStage(ownerStage);

		info.run();
	}

	public void run() {
		Actions.checkInfo(this.key, this.settings, this.ownerStage, this);
	}

	@Override
	public void checkInfo(boolean exists) {
		super.checkInfo(exists);

		switch (this.action) {
		case CHECK:

			break;
		case SAVE:

			if (exists)
				Actions.updateInfo(this.key, this.value, this.settings, this.ownerStage);
			else
				Actions.insertInfo(this.key, this.value, this.settings, this.ownerStage);

			break;
		}
	}

	public void setInfo(Settings settings, JSONObject json) {

		if (json != null) {
			Object keyObj = json.get("keyInf");
			if (keyObj != null) {

				String keyInf = (String) keyObj;
				String inf = Crypt.decrypt(json.getString("inf"), settings.getDatabaseSecretKey());
				if (inf == null)
					inf = "";

				switch (keyInf) {
				case KEYS.CONGR:
					this.setCongr(inf);
					break;
				case KEYS.OVERSEER1:
					this.setOverseer1(inf);
					break;
				case KEYS.OVERSEER1WIFE:
					this.setOverseer1wife(inf);
					break;
				case KEYS.OVERSEER2:
					this.setOverseer2(inf);
					break;
				case KEYS.OVERSEER2WIFE:
					this.setOverseer2wife(inf);
					break;
				case KEYS.DAYMIDWEEKMEETING:
					this.setDayMidweekMeeting(inf);
					break;
				case KEYS.DAYWEEKENDMEETING:
					this.setDayWeekendMeeting(inf);
					break;
				case KEYS.HOURMIDWEEKMEETING:
					this.setHourMidweekMeeting(inf);
					break;
				case KEYS.MINUTEMIDWEEKMEETING:
					this.setMinuteMidweekMeeting(inf);
					break;
				case KEYS.HOURWEEKENDMEETING:
					this.setHourWeekendMeeting(inf);
					break;
				case KEYS.MINUTEWEEKENDMEETING:
					this.setMinuteWeekendMeeting(inf);
					break;
				case KEYS.KINGDOMHALLLOCATIONSTREET:
					this.setKingdomHallLocationStreet(inf);
					break;
				case KEYS.KINGDOMHALLLOCATIONNUMBER:
					this.setKingdomHallLocationNumber(inf);
					break;
				case KEYS.KINGDOMHALLLOCATIONPOSTCODE:
					this.setKingdomHallLocationPostcode(inf);
					break;
				case KEYS.KINGDOMHALLLOCATIONCITY:
					this.setKingdomHallLocationCity(inf);
					break;
				}
			}
		}
	}

	public EnumActions getAction() {
		return action;
	}

	public void setAction(EnumActions action) {
		this.action = action;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}

	public String getCongr() {
		return congr;
	}

	public void setCongr(String congr) {
		this.congr = congr;
	}

	public String getOverseer1() {
		return overseer1;
	}

	public void setOverseer1(String overseer1) {
		this.overseer1 = overseer1;
	}

	public String getOverseer1wife() {
		return overseer1wife;
	}

	public void setOverseer1wife(String overseer1wife) {
		this.overseer1wife = overseer1wife;
	}

	public String getOverseer2() {
		return overseer2;
	}

	public void setOverseer2(String overseer2) {
		this.overseer2 = overseer2;
	}

	public String getOverseer2wife() {
		return overseer2wife;
	}

	public void setOverseer2wife(String overseer2wife) {
		this.overseer2wife = overseer2wife;
	}

	public String getDayMidweekMeeting() {
		return dayMidweekMeeting;
	}

	public void setDayMidweekMeeting(String dayMidweekMeeting) {
		this.dayMidweekMeeting = dayMidweekMeeting;
	}

	public String getDayWeekendMeeting() {
		return dayWeekendMeeting;
	}

	public void setDayWeekendMeeting(String dayWeekendMeeting) {
		this.dayWeekendMeeting = dayWeekendMeeting;
	}

	public String getHourMidweekMeeting() {
		return hourMidweekMeeting;
	}

	public void setHourMidweekMeeting(String hourMidweekMeeting) {
		this.hourMidweekMeeting = hourMidweekMeeting;
	}

	public String getMinuteMidweekMeeting() {
		return minuteMidweekMeeting;
	}

	public void setMinuteMidweekMeeting(String minuteMidweekMeeting) {
		this.minuteMidweekMeeting = minuteMidweekMeeting;
	}

	public String getHourWeekendMeeting() {
		return hourWeekendMeeting;
	}

	public void setHourWeekendMeeting(String hourWeekendMeeting) {
		this.hourWeekendMeeting = hourWeekendMeeting;
	}

	public String getMinuteWeekendMeeting() {
		return minuteWeekendMeeting;
	}

	public void setMinuteWeekendMeeting(String minuteWeekendMeeting) {
		this.minuteWeekendMeeting = minuteWeekendMeeting;
	}

	public String getKingdomHallLocationStreet() {
		return kingdomHallLocationStreet;
	}

	public void setKingdomHallLocationStreet(String kingdomHallLocationStreet) {
		this.kingdomHallLocationStreet = kingdomHallLocationStreet;
	}

	public String getKingdomHallLocationNumber() {
		return kingdomHallLocationNumber;
	}

	public void setKingdomHallLocationNumber(String kingdomHallLocationNumber) {
		this.kingdomHallLocationNumber = kingdomHallLocationNumber;
	}

	public String getKingdomHallLocationPostcode() {
		return kingdomHallLocationPostcode;
	}

	public void setKingdomHallLocationPostcode(String kingdomHallLocationPostcode) {
		this.kingdomHallLocationPostcode = kingdomHallLocationPostcode;
	}

	public String getKingdomHallLocationCity() {
		return kingdomHallLocationCity;
	}

	public void setKingdomHallLocationCity(String kingdomHallLocationCity) {
		this.kingdomHallLocationCity = kingdomHallLocationCity;
	}

	public static class KEYS {

		public static final String CONGR = "inf1";
		public static final String OVERSEER1 = "inf2";
		public static final String OVERSEER1WIFE = "inf3";
		public static final String OVERSEER2 = "inf4";
		public static final String OVERSEER2WIFE = "inf5";

		public static final String DAYMIDWEEKMEETING = "inf6";
		public static final String DAYWEEKENDMEETING = "inf7";
		public static final String HOURMIDWEEKMEETING = "inf8";
		public static final String MINUTEMIDWEEKMEETING = "inf9";
		public static final String HOURWEEKENDMEETING = "inf10";
		public static final String MINUTEWEEKENDMEETING = "inf11";
		public static final String KINGDOMHALLLOCATIONSTREET = "inf12";
		public static final String KINGDOMHALLLOCATIONNUMBER = "inf13";
		public static final String KINGDOMHALLLOCATIONPOSTCODE = "inf14";
		public static final String KINGDOMHALLLOCATIONCITY = "inf15";

		public static String getUserMenuCongrListSelect() {

			String select = "'" + CONGR + "'";
			select += ", ";
			select += "'" + OVERSEER1 + "'";
			select += ", ";
			select += "'" + OVERSEER1WIFE + "'";
			select += ", ";
			select += "'" + OVERSEER2 + "'";
			select += ", ";
			select += "'" + OVERSEER2WIFE + "'";

			return select;
		}

		public static String getUserMenuMeetingsSelect() {

			String select = "'" + DAYMIDWEEKMEETING + "'";
			select += ", ";
			select += "'" + DAYWEEKENDMEETING + "'";
			select += ", ";
			select += "'" + HOURMIDWEEKMEETING + "'";
			select += ", ";
			select += "'" + MINUTEMIDWEEKMEETING + "'";
			select += ", ";
			select += "'" + HOURWEEKENDMEETING + "'";
			select += ", ";
			select += "'" + MINUTEWEEKENDMEETING + "'";
			select += ", ";
			select += "'" + KINGDOMHALLLOCATIONSTREET + "'";
			select += ", ";
			select += "'" + KINGDOMHALLLOCATIONNUMBER + "'";
			select += ", ";
			select += "'" + KINGDOMHALLLOCATIONPOSTCODE + "'";
			select += ", ";
			select += "'" + KINGDOMHALLLOCATIONCITY + "'";

			return select;
		}
	}

	public enum EnumActions {
		CHECK(), SAVE();
	}
}

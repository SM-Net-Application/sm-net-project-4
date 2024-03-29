package com.sm.net.sp.model;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import com.sm.net.project.Language;
import com.sm.net.util.Crypt;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Member {

	private IntegerProperty spMemberID;
	private StringProperty spInf1Encrypted;
	private StringProperty spInf1Decrypted;
	private StringProperty spInf2Encrypted;
	private StringProperty spInf2Decrypted;
	private StringProperty spInf3Encrypted;
	private StringProperty spInf3Decrypted;
	private IntegerProperty spInf4;
	private IntegerProperty spInf5;
	private IntegerProperty spInf6;
	private IntegerProperty spInf7;
	private IntegerProperty spInf8;
	private IntegerProperty spInf9;
	private IntegerProperty spInf10;
	private IntegerProperty spInf11;
	private IntegerProperty spInf12;
	private IntegerProperty spInf13;
	private IntegerProperty spInf14;
	private IntegerProperty spInf15;
	private IntegerProperty spInf16;
	private IntegerProperty spInf17;
	private IntegerProperty spInf18;
	private IntegerProperty spInf19;
	private IntegerProperty spInf20;
	private IntegerProperty spInf21;
	private IntegerProperty spInf22;
	private IntegerProperty spInf23;
	private IntegerProperty spInf24;
	private IntegerProperty spInf25;
	private IntegerProperty spInf26;
	private IntegerProperty spInf27;
	private IntegerProperty spInf28;
	private IntegerProperty spInf29;
	private IntegerProperty spInf30;
	private IntegerProperty spInf31;
	private IntegerProperty spInf32;
	private IntegerProperty spInf33;
	private IntegerProperty spInf34;
	private IntegerProperty spInf35;
	private IntegerProperty spInf36;
	private IntegerProperty spInf37;
	private IntegerProperty spInf38;
	private StringProperty spInf39Encrypted;
	private StringProperty spInf39Decrypted;
	private StringProperty spInf40Encrypted;
	private StringProperty spInf40Decrypted;
	private StringProperty spInf41Encrypted;
	private StringProperty spInf41Decrypted;
	private IntegerProperty spInf42;
	private IntegerProperty spInf43;
	private IntegerProperty spInf44;
	private IntegerProperty spInf45;
	private IntegerProperty spInf46;
	private StringProperty spInf47;
	private IntegerProperty spInf48;
	private IntegerProperty spInf49;
	private IntegerProperty spInf50;
	private IntegerProperty spInf51;
	private StringProperty spInf52Encrypted;
	private StringProperty spInf52Decrypted;
	private StringProperty spInf53Encrypted;
	private StringProperty spInf53Decrypted;

	private IntegerProperty spInf54;
	private IntegerProperty spInf55;
	private IntegerProperty spInf56;
	private IntegerProperty spInf57;
	private IntegerProperty spInf58;
	private IntegerProperty spInf59;

	private IntegerProperty spInf60;
	private IntegerProperty spInf61;
	private IntegerProperty spInf62;

	private StringProperty spInf63Encrypted;
	private StringProperty spInf63Decrypted;
	private IntegerProperty spInf64;
	private IntegerProperty spInf65;
	private IntegerProperty spInf66;
	private IntegerProperty spInf67;
	private IntegerProperty spInf68;
	private IntegerProperty spInf69;
	private IntegerProperty spInf70;
	private IntegerProperty spInf71;
	private IntegerProperty spInf72;
	private IntegerProperty spInf73;
	private IntegerProperty spInf74;
	private IntegerProperty spInf75;

	private IntegerProperty spInf76;

	public Member() {
		super();
	}

	public Member(JSONObject jsonObject, SecretKey secretKey) {
		super();
		defaultCostructor(jsonObject, secretKey);
	}

	private void defaultCostructor(JSONObject jsonObject, SecretKey secretKey) {

		this.spMemberID = new SimpleIntegerProperty(jsonObject.getInt("spMemberID"));
		this.spInf1Encrypted = new SimpleStringProperty(jsonObject.getString("spInf1"));
		this.spInf1Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf1Encrypted.get(), secretKey));
		this.spInf2Encrypted = new SimpleStringProperty(jsonObject.getString("spInf2"));
		this.spInf2Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf2Encrypted.get(), secretKey));
		this.spInf3Encrypted = new SimpleStringProperty(jsonObject.getString("spInf3"));
		this.spInf3Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf3Encrypted.get(), secretKey));
		this.spInf4 = new SimpleIntegerProperty(jsonObject.getInt("spInf4"));
		this.spInf5 = new SimpleIntegerProperty(jsonObject.getInt("spInf5"));
		this.spInf6 = new SimpleIntegerProperty(jsonObject.getInt("spInf6"));
		this.spInf7 = new SimpleIntegerProperty(jsonObject.getInt("spInf7"));
		this.spInf8 = new SimpleIntegerProperty(jsonObject.getInt("spInf8"));
		this.spInf9 = new SimpleIntegerProperty(jsonObject.getInt("spInf9"));
		this.spInf10 = new SimpleIntegerProperty(jsonObject.getInt("spInf10"));
		this.spInf11 = new SimpleIntegerProperty(jsonObject.getInt("spInf11"));
		this.spInf12 = new SimpleIntegerProperty(jsonObject.getInt("spInf12"));
		this.spInf13 = new SimpleIntegerProperty(jsonObject.getInt("spInf13"));
		this.spInf14 = new SimpleIntegerProperty(jsonObject.getInt("spInf14"));
		this.spInf15 = new SimpleIntegerProperty(jsonObject.getInt("spInf15"));
		this.spInf16 = new SimpleIntegerProperty(jsonObject.getInt("spInf16"));
		this.spInf17 = new SimpleIntegerProperty(jsonObject.getInt("spInf17"));
		this.spInf18 = new SimpleIntegerProperty(jsonObject.getInt("spInf18"));
		this.spInf19 = new SimpleIntegerProperty(jsonObject.getInt("spInf19"));
		this.spInf20 = new SimpleIntegerProperty(jsonObject.getInt("spInf20"));
		this.spInf21 = new SimpleIntegerProperty(jsonObject.getInt("spInf21"));
		this.spInf22 = new SimpleIntegerProperty(jsonObject.getInt("spInf22"));
		this.spInf23 = new SimpleIntegerProperty(jsonObject.getInt("spInf23"));
		this.spInf24 = new SimpleIntegerProperty(jsonObject.getInt("spInf24"));
		this.spInf25 = new SimpleIntegerProperty(jsonObject.getInt("spInf25"));
		this.spInf26 = new SimpleIntegerProperty(jsonObject.getInt("spInf26"));
		this.spInf27 = new SimpleIntegerProperty(jsonObject.getInt("spInf27"));
		this.spInf28 = new SimpleIntegerProperty(jsonObject.getInt("spInf28"));
		this.spInf29 = new SimpleIntegerProperty(jsonObject.getInt("spInf29"));
		this.spInf30 = new SimpleIntegerProperty(jsonObject.getInt("spInf30"));
		this.spInf31 = new SimpleIntegerProperty(jsonObject.getInt("spInf31"));
		this.spInf32 = new SimpleIntegerProperty(jsonObject.getInt("spInf32"));
		this.spInf33 = new SimpleIntegerProperty(jsonObject.getInt("spInf33"));
		this.spInf34 = new SimpleIntegerProperty(jsonObject.getInt("spInf34"));
		this.spInf35 = new SimpleIntegerProperty(jsonObject.getInt("spInf35"));
		this.spInf36 = new SimpleIntegerProperty(jsonObject.getInt("spInf36"));
		this.spInf37 = new SimpleIntegerProperty(jsonObject.getInt("spInf37"));
		this.spInf38 = new SimpleIntegerProperty(jsonObject.getInt("spInf38"));
		this.spInf39Encrypted = new SimpleStringProperty(jsonObject.getString("spInf39"));
		this.spInf39Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf39Encrypted.get(), secretKey));
		this.spInf40Encrypted = new SimpleStringProperty(jsonObject.getString("spInf40"));
		this.spInf40Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf40Encrypted.get(), secretKey));
		this.spInf41Encrypted = new SimpleStringProperty(jsonObject.getString("spInf41"));
		this.spInf41Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf41Encrypted.get(), secretKey));
		this.spInf42 = new SimpleIntegerProperty(jsonObject.getInt("spInf42"));
		this.spInf43 = new SimpleIntegerProperty(jsonObject.getInt("spInf43"));
		this.spInf44 = new SimpleIntegerProperty(jsonObject.getInt("spInf44"));
		this.spInf45 = new SimpleIntegerProperty(jsonObject.getInt("spInf45"));
		this.spInf46 = new SimpleIntegerProperty(jsonObject.getInt("spInf46"));
		this.spInf47 = new SimpleStringProperty(jsonObject.getString("spInf47"));
		this.spInf48 = new SimpleIntegerProperty(jsonObject.getInt("spInf48"));
		this.spInf49 = new SimpleIntegerProperty(jsonObject.getInt("spInf49"));
		this.spInf50 = new SimpleIntegerProperty(jsonObject.getInt("spInf50"));
		this.spInf51 = new SimpleIntegerProperty(jsonObject.getInt("spInf51"));
		this.spInf52Encrypted = new SimpleStringProperty(jsonObject.getString("spInf52"));
		this.spInf52Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf52Encrypted.get(), secretKey));
		this.spInf53Encrypted = new SimpleStringProperty(jsonObject.getString("spInf53"));
		this.spInf53Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf53Encrypted.get(), secretKey));
		this.spInf54 = new SimpleIntegerProperty(jsonObject.getInt("spInf54"));
		this.spInf55 = new SimpleIntegerProperty(jsonObject.getInt("spInf55"));
		this.spInf56 = new SimpleIntegerProperty(jsonObject.getInt("spInf56"));
		this.spInf57 = new SimpleIntegerProperty(jsonObject.getInt("spInf57"));
		this.spInf58 = new SimpleIntegerProperty(jsonObject.getInt("spInf58"));
		this.spInf59 = new SimpleIntegerProperty(jsonObject.getInt("spInf59"));

		this.spInf60 = new SimpleIntegerProperty(jsonObject.getInt("spInf60"));
		this.spInf61 = new SimpleIntegerProperty(jsonObject.getInt("spInf61"));
		this.spInf62 = new SimpleIntegerProperty(jsonObject.getInt("spInf62"));

		this.spInf63Encrypted = new SimpleStringProperty(jsonObject.getString("spInf63"));
		this.spInf63Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf63Encrypted.get(), secretKey));
		this.spInf64 = new SimpleIntegerProperty(jsonObject.getInt("spInf64"));
		this.spInf65 = new SimpleIntegerProperty(jsonObject.getInt("spInf65"));
		this.spInf66 = new SimpleIntegerProperty(jsonObject.getInt("spInf66"));
		this.spInf67 = new SimpleIntegerProperty(jsonObject.getInt("spInf67"));
		this.spInf68 = new SimpleIntegerProperty(jsonObject.getInt("spInf68"));
		this.spInf69 = new SimpleIntegerProperty(jsonObject.getInt("spInf69"));
		this.spInf70 = new SimpleIntegerProperty(jsonObject.getInt("spInf70"));
		this.spInf71 = new SimpleIntegerProperty(jsonObject.getInt("spInf71"));
		this.spInf72 = new SimpleIntegerProperty(jsonObject.getInt("spInf72"));
		this.spInf73 = new SimpleIntegerProperty(jsonObject.getInt("spInf73"));
		this.spInf74 = new SimpleIntegerProperty(jsonObject.getInt("spInf74"));
		this.spInf75 = new SimpleIntegerProperty(jsonObject.getInt("spInf75"));

		this.spInf76 = new SimpleIntegerProperty(jsonObject.getInt("spInf76"));
	}

	public static Member emptyMember(Language language) {

		Member member = new Member();

		member.spMemberID = new SimpleIntegerProperty(0);
		member.spInf1Encrypted = new SimpleStringProperty("");
		member.spInf1Decrypted = new SimpleStringProperty("");
		member.spInf2Encrypted = new SimpleStringProperty("");
		member.spInf2Decrypted = new SimpleStringProperty(language.getString("TEXT0096"));
		member.spInf3Encrypted = new SimpleStringProperty("");
		member.spInf3Decrypted = new SimpleStringProperty("");
		member.spInf4 = new SimpleIntegerProperty(0);
		member.spInf5 = new SimpleIntegerProperty(0);
		member.spInf6 = new SimpleIntegerProperty(0);
		member.spInf7 = new SimpleIntegerProperty(0);
		member.spInf8 = new SimpleIntegerProperty(0);
		member.spInf9 = new SimpleIntegerProperty(0);
		member.spInf10 = new SimpleIntegerProperty(0);
		member.spInf11 = new SimpleIntegerProperty(0);
		member.spInf12 = new SimpleIntegerProperty(0);
		member.spInf13 = new SimpleIntegerProperty(0);
		member.spInf14 = new SimpleIntegerProperty(0);
		member.spInf15 = new SimpleIntegerProperty(0);
		member.spInf16 = new SimpleIntegerProperty(0);
		member.spInf17 = new SimpleIntegerProperty(0);
		member.spInf18 = new SimpleIntegerProperty(0);
		member.spInf19 = new SimpleIntegerProperty(0);
		member.spInf20 = new SimpleIntegerProperty(0);
		member.spInf21 = new SimpleIntegerProperty(0);
		member.spInf22 = new SimpleIntegerProperty(0);
		member.spInf23 = new SimpleIntegerProperty(0);
		member.spInf24 = new SimpleIntegerProperty(0);
		member.spInf25 = new SimpleIntegerProperty(0);
		member.spInf26 = new SimpleIntegerProperty(0);
		member.spInf27 = new SimpleIntegerProperty(0);
		member.spInf28 = new SimpleIntegerProperty(0);
		member.spInf29 = new SimpleIntegerProperty(0);
		member.spInf30 = new SimpleIntegerProperty(0);
		member.spInf31 = new SimpleIntegerProperty(0);
		member.spInf32 = new SimpleIntegerProperty(0);
		member.spInf33 = new SimpleIntegerProperty(0);
		member.spInf34 = new SimpleIntegerProperty(0);
		member.spInf35 = new SimpleIntegerProperty(0);
		member.spInf36 = new SimpleIntegerProperty(0);
		member.spInf37 = new SimpleIntegerProperty(0);
		member.spInf38 = new SimpleIntegerProperty(0);
		member.spInf39Encrypted = new SimpleStringProperty("");
		member.spInf39Decrypted = new SimpleStringProperty("");
		member.spInf40Encrypted = new SimpleStringProperty("");
		member.spInf40Decrypted = new SimpleStringProperty("");
		member.spInf41Encrypted = new SimpleStringProperty("");
		member.spInf41Decrypted = new SimpleStringProperty("");
		member.spInf42 = new SimpleIntegerProperty(0);
		member.spInf43 = new SimpleIntegerProperty(0);
		member.spInf44 = new SimpleIntegerProperty(0);
		member.spInf45 = new SimpleIntegerProperty(0);
		member.spInf46 = new SimpleIntegerProperty(0);
		member.spInf47 = new SimpleStringProperty("");
		member.spInf48 = new SimpleIntegerProperty(0);
		member.spInf49 = new SimpleIntegerProperty(0);
		member.spInf50 = new SimpleIntegerProperty(0);
		member.spInf51 = new SimpleIntegerProperty(0);
		member.spInf52Encrypted = new SimpleStringProperty("");
		member.spInf52Decrypted = new SimpleStringProperty("");
		member.spInf53Encrypted = new SimpleStringProperty("");
		member.spInf53Decrypted = new SimpleStringProperty("");
		member.spInf54 = new SimpleIntegerProperty(0);
		member.spInf55 = new SimpleIntegerProperty(0);
		member.spInf56 = new SimpleIntegerProperty(0);
		member.spInf57 = new SimpleIntegerProperty(0);
		member.spInf58 = new SimpleIntegerProperty(0);
		member.spInf59 = new SimpleIntegerProperty(0);

		member.spInf60 = new SimpleIntegerProperty(0);
		member.spInf61 = new SimpleIntegerProperty(0);
		member.spInf62 = new SimpleIntegerProperty(0);

		member.spInf63Encrypted = new SimpleStringProperty("");
		member.spInf63Decrypted = new SimpleStringProperty("");
		member.spInf64 = new SimpleIntegerProperty(0);
		member.spInf65 = new SimpleIntegerProperty(0);
		member.spInf66 = new SimpleIntegerProperty(0);
		member.spInf67 = new SimpleIntegerProperty(0);
		member.spInf68 = new SimpleIntegerProperty(0);
		member.spInf69 = new SimpleIntegerProperty(0);
		member.spInf70 = new SimpleIntegerProperty(0);
		member.spInf71 = new SimpleIntegerProperty(0);
		member.spInf72 = new SimpleIntegerProperty(0);
		member.spInf73 = new SimpleIntegerProperty(0);
		member.spInf74 = new SimpleIntegerProperty(0);
		member.spInf75 = new SimpleIntegerProperty(0);

		member.spInf76 = new SimpleIntegerProperty(0);

		return member;
	}

	public static Member territoryLastAssignText(String text) {
		Member member = new Member();

		member.spMemberID = new SimpleIntegerProperty(0);
		member.spInf1Encrypted = new SimpleStringProperty("");
		member.spInf1Decrypted = new SimpleStringProperty("");
		member.spInf2Encrypted = new SimpleStringProperty("");
		member.spInf2Decrypted = new SimpleStringProperty(text);
		member.spInf3Encrypted = new SimpleStringProperty("");
		member.spInf3Decrypted = new SimpleStringProperty("");
		member.spInf4 = new SimpleIntegerProperty(0);
		member.spInf5 = new SimpleIntegerProperty(0);
		member.spInf6 = new SimpleIntegerProperty(0);
		member.spInf7 = new SimpleIntegerProperty(0);
		member.spInf8 = new SimpleIntegerProperty(0);
		member.spInf9 = new SimpleIntegerProperty(0);
		member.spInf10 = new SimpleIntegerProperty(0);
		member.spInf11 = new SimpleIntegerProperty(0);
		member.spInf12 = new SimpleIntegerProperty(0);
		member.spInf13 = new SimpleIntegerProperty(0);
		member.spInf14 = new SimpleIntegerProperty(0);
		member.spInf15 = new SimpleIntegerProperty(0);
		member.spInf16 = new SimpleIntegerProperty(0);
		member.spInf17 = new SimpleIntegerProperty(0);
		member.spInf18 = new SimpleIntegerProperty(0);
		member.spInf19 = new SimpleIntegerProperty(0);
		member.spInf20 = new SimpleIntegerProperty(0);
		member.spInf21 = new SimpleIntegerProperty(0);
		member.spInf22 = new SimpleIntegerProperty(0);
		member.spInf23 = new SimpleIntegerProperty(0);
		member.spInf24 = new SimpleIntegerProperty(0);
		member.spInf25 = new SimpleIntegerProperty(0);
		member.spInf26 = new SimpleIntegerProperty(0);
		member.spInf27 = new SimpleIntegerProperty(0);
		member.spInf28 = new SimpleIntegerProperty(0);
		member.spInf29 = new SimpleIntegerProperty(0);
		member.spInf30 = new SimpleIntegerProperty(0);
		member.spInf31 = new SimpleIntegerProperty(0);
		member.spInf32 = new SimpleIntegerProperty(0);
		member.spInf33 = new SimpleIntegerProperty(0);
		member.spInf34 = new SimpleIntegerProperty(0);
		member.spInf35 = new SimpleIntegerProperty(0);
		member.spInf36 = new SimpleIntegerProperty(0);
		member.spInf37 = new SimpleIntegerProperty(0);
		member.spInf38 = new SimpleIntegerProperty(0);
		member.spInf39Encrypted = new SimpleStringProperty("");
		member.spInf39Decrypted = new SimpleStringProperty("");
		member.spInf40Encrypted = new SimpleStringProperty("");
		member.spInf40Decrypted = new SimpleStringProperty("");
		member.spInf41Encrypted = new SimpleStringProperty("");
		member.spInf41Decrypted = new SimpleStringProperty("");
		member.spInf42 = new SimpleIntegerProperty(0);
		member.spInf43 = new SimpleIntegerProperty(0);
		member.spInf44 = new SimpleIntegerProperty(0);
		member.spInf45 = new SimpleIntegerProperty(0);
		member.spInf46 = new SimpleIntegerProperty(0);
		member.spInf47 = new SimpleStringProperty("");
		member.spInf48 = new SimpleIntegerProperty(0);
		member.spInf49 = new SimpleIntegerProperty(0);
		member.spInf50 = new SimpleIntegerProperty(0);
		member.spInf51 = new SimpleIntegerProperty(0);
		member.spInf52Encrypted = new SimpleStringProperty("");
		member.spInf52Decrypted = new SimpleStringProperty("");
		member.spInf53Encrypted = new SimpleStringProperty("");
		member.spInf53Decrypted = new SimpleStringProperty("");
		member.spInf54 = new SimpleIntegerProperty(0);
		member.spInf55 = new SimpleIntegerProperty(0);
		member.spInf56 = new SimpleIntegerProperty(0);
		member.spInf57 = new SimpleIntegerProperty(0);
		member.spInf58 = new SimpleIntegerProperty(0);
		member.spInf59 = new SimpleIntegerProperty(0);

		member.spInf60 = new SimpleIntegerProperty(0);
		member.spInf61 = new SimpleIntegerProperty(0);
		member.spInf62 = new SimpleIntegerProperty(0);

		member.spInf63Encrypted = new SimpleStringProperty("");
		member.spInf63Decrypted = new SimpleStringProperty("");
		member.spInf64 = new SimpleIntegerProperty(0);
		member.spInf65 = new SimpleIntegerProperty(0);
		member.spInf66 = new SimpleIntegerProperty(0);
		member.spInf67 = new SimpleIntegerProperty(0);
		member.spInf68 = new SimpleIntegerProperty(0);
		member.spInf69 = new SimpleIntegerProperty(0);
		member.spInf70 = new SimpleIntegerProperty(0);
		member.spInf71 = new SimpleIntegerProperty(0);
		member.spInf72 = new SimpleIntegerProperty(0);
		member.spInf73 = new SimpleIntegerProperty(0);
		member.spInf74 = new SimpleIntegerProperty(0);
		member.spInf75 = new SimpleIntegerProperty(0);

		member.spInf76 = new SimpleIntegerProperty(0);

		return member;
	}
	
	public static Member overseerMember(Language language) {

		Member member = new Member();

		member.spMemberID = new SimpleIntegerProperty(-1);
		member.spInf1Encrypted = new SimpleStringProperty("");
		member.spInf1Decrypted = new SimpleStringProperty("");
		member.spInf2Encrypted = new SimpleStringProperty("");
		member.spInf2Decrypted = new SimpleStringProperty(language.getString("territory.overseer"));
		member.spInf3Encrypted = new SimpleStringProperty("");
		member.spInf3Decrypted = new SimpleStringProperty("");
		member.spInf4 = new SimpleIntegerProperty(0);
		member.spInf5 = new SimpleIntegerProperty(0);
		member.spInf6 = new SimpleIntegerProperty(0);
		member.spInf7 = new SimpleIntegerProperty(0);
		member.spInf8 = new SimpleIntegerProperty(0);
		member.spInf9 = new SimpleIntegerProperty(0);
		member.spInf10 = new SimpleIntegerProperty(0);
		member.spInf11 = new SimpleIntegerProperty(0);
		member.spInf12 = new SimpleIntegerProperty(0);
		member.spInf13 = new SimpleIntegerProperty(0);
		member.spInf14 = new SimpleIntegerProperty(0);
		member.spInf15 = new SimpleIntegerProperty(0);
		member.spInf16 = new SimpleIntegerProperty(0);
		member.spInf17 = new SimpleIntegerProperty(0);
		member.spInf18 = new SimpleIntegerProperty(0);
		member.spInf19 = new SimpleIntegerProperty(0);
		member.spInf20 = new SimpleIntegerProperty(0);
		member.spInf21 = new SimpleIntegerProperty(0);
		member.spInf22 = new SimpleIntegerProperty(0);
		member.spInf23 = new SimpleIntegerProperty(0);
		member.spInf24 = new SimpleIntegerProperty(0);
		member.spInf25 = new SimpleIntegerProperty(0);
		member.spInf26 = new SimpleIntegerProperty(0);
		member.spInf27 = new SimpleIntegerProperty(0);
		member.spInf28 = new SimpleIntegerProperty(0);
		member.spInf29 = new SimpleIntegerProperty(0);
		member.spInf30 = new SimpleIntegerProperty(0);
		member.spInf31 = new SimpleIntegerProperty(0);
		member.spInf32 = new SimpleIntegerProperty(0);
		member.spInf33 = new SimpleIntegerProperty(0);
		member.spInf34 = new SimpleIntegerProperty(0);
		member.spInf35 = new SimpleIntegerProperty(0);
		member.spInf36 = new SimpleIntegerProperty(0);
		member.spInf37 = new SimpleIntegerProperty(0);
		member.spInf38 = new SimpleIntegerProperty(0);
		member.spInf39Encrypted = new SimpleStringProperty("");
		member.spInf39Decrypted = new SimpleStringProperty("");
		member.spInf40Encrypted = new SimpleStringProperty("");
		member.spInf40Decrypted = new SimpleStringProperty("");
		member.spInf41Encrypted = new SimpleStringProperty("");
		member.spInf41Decrypted = new SimpleStringProperty("");
		member.spInf42 = new SimpleIntegerProperty(0);
		member.spInf43 = new SimpleIntegerProperty(0);
		member.spInf44 = new SimpleIntegerProperty(0);
		member.spInf45 = new SimpleIntegerProperty(0);
		member.spInf46 = new SimpleIntegerProperty(0);
		member.spInf47 = new SimpleStringProperty("");
		member.spInf48 = new SimpleIntegerProperty(0);
		member.spInf49 = new SimpleIntegerProperty(0);
		member.spInf50 = new SimpleIntegerProperty(0);
		member.spInf51 = new SimpleIntegerProperty(0);
		member.spInf52Encrypted = new SimpleStringProperty("");
		member.spInf52Decrypted = new SimpleStringProperty("");
		member.spInf53Encrypted = new SimpleStringProperty("");
		member.spInf53Decrypted = new SimpleStringProperty("");
		member.spInf54 = new SimpleIntegerProperty(0);
		member.spInf55 = new SimpleIntegerProperty(0);
		member.spInf56 = new SimpleIntegerProperty(0);
		member.spInf57 = new SimpleIntegerProperty(0);
		member.spInf58 = new SimpleIntegerProperty(0);
		member.spInf59 = new SimpleIntegerProperty(0);

		member.spInf60 = new SimpleIntegerProperty(0);
		member.spInf61 = new SimpleIntegerProperty(0);
		member.spInf62 = new SimpleIntegerProperty(0);

		member.spInf63Encrypted = new SimpleStringProperty("");
		member.spInf63Decrypted = new SimpleStringProperty("");
		member.spInf64 = new SimpleIntegerProperty(0);
		member.spInf65 = new SimpleIntegerProperty(0);
		member.spInf66 = new SimpleIntegerProperty(0);
		member.spInf67 = new SimpleIntegerProperty(0);
		member.spInf68 = new SimpleIntegerProperty(0);
		member.spInf69 = new SimpleIntegerProperty(0);
		member.spInf70 = new SimpleIntegerProperty(0);
		member.spInf71 = new SimpleIntegerProperty(0);
		member.spInf72 = new SimpleIntegerProperty(0);
		member.spInf73 = new SimpleIntegerProperty(0);
		member.spInf74 = new SimpleIntegerProperty(0);
		member.spInf75 = new SimpleIntegerProperty(0);

		member.spInf76 = new SimpleIntegerProperty(0);

		return member;
	}

	public boolean isDisabled() {
		return this.getSpInf76() == 1;
	}

	public final String getNameStyle1() {
		return (!this.getSpInf1Decrypted().isEmpty()) ? (this.checkSurname() + ", " + this.getSpInf1Decrypted())
				: this.checkSurname();
	}

	public final String getNameStyle2() {
		return (!this.getSpInf3Decrypted().isEmpty()) ? (this.checkSurname() + ", " + this.getSpInf3Decrypted())
				: this.checkSurname();
	}

	public final String getNameStyle3() {
		return (!this.getSpInf1Decrypted().isEmpty()) ? (this.checkSurname() + " " + this.getSpInf1Decrypted())
				: this.checkSurname();
	}

	public final String getNameStyle4() {
		return (!this.getSpInf3Decrypted().isEmpty()) ? (this.checkSurname() + " " + this.getSpInf3Decrypted())
				: this.checkSurname();
	}

	private String checkSurname() {

		if (this.getSpInf50() == 1)
			return this.getSpInf39Decrypted();

		return this.getSpInf2Decrypted();
	}

	@Override
	public String toString() {
		return getNameStyle1();
	}

	public static Member getFromID(ObservableList<Member> membersList, int id, Language language) {

		if (membersList != null)
			for (Member member : membersList)
				if (member.getSpMemberID() == id)
					return member;

		return Member.emptyMember(language);
	}

	public final IntegerProperty spMemberIDProperty() {
		return this.spMemberID;
	}

	public final int getSpMemberID() {
		return this.spMemberIDProperty().get();
	}

	public final void setSpMemberID(final int spMemberID) {
		this.spMemberIDProperty().set(spMemberID);
	}

	public final StringProperty spInf1EncryptedProperty() {
		return this.spInf1Encrypted;
	}

	public final String getSpInf1Encrypted() {
		return this.spInf1EncryptedProperty().get();
	}

	public final void setSpInf1Encrypted(final String spInf1Encrypted) {
		this.spInf1EncryptedProperty().set(spInf1Encrypted);
	}

	public final StringProperty spInf2EncryptedProperty() {
		return this.spInf2Encrypted;
	}

	public final String getSpInf2Encrypted() {
		return this.spInf2EncryptedProperty().get();
	}

	public final void setSpInf2Encrypted(final String spInf2Encrypted) {
		this.spInf2EncryptedProperty().set(spInf2Encrypted);
	}

	public final StringProperty spInf3EncryptedProperty() {
		return this.spInf3Encrypted;
	}

	public final String getSpInf3Encrypted() {
		return this.spInf3EncryptedProperty().get();
	}

	public final void setSpInf3Encrypted(final String spInf3Encrypted) {
		this.spInf3EncryptedProperty().set(spInf3Encrypted);
	}

	public final StringProperty spInf1DecryptedProperty() {
		return this.spInf1Decrypted;
	}

	public final String getSpInf1Decrypted() {
		return this.spInf1DecryptedProperty().get();
	}

	public final void setSpInf1Decrypted(final String spInf1Decrypted) {
		this.spInf1DecryptedProperty().set(spInf1Decrypted);
	}

	public final StringProperty spInf2DecryptedProperty() {
		return this.spInf2Decrypted;
	}

	public final String getSpInf2Decrypted() {
		return this.spInf2DecryptedProperty().get();
	}

	public final void setSpInf2Decrypted(final String spInf2Decrypted) {
		this.spInf2DecryptedProperty().set(spInf2Decrypted);
	}

	public final StringProperty spInf3DecryptedProperty() {
		return this.spInf3Decrypted;
	}

	public final String getSpInf3Decrypted() {
		return this.spInf3DecryptedProperty().get();
	}

	public final void setSpInf3Decrypted(final String spInf3Decrypted) {
		this.spInf3DecryptedProperty().set(spInf3Decrypted);
	}

	public final IntegerProperty spInf4Property() {
		return this.spInf4;
	}

	public final int getSpInf4() {
		return this.spInf4Property().get();
	}

	public final void setSpInf4(final int spInf4) {
		this.spInf4Property().set(spInf4);
	}

	public final IntegerProperty spInf5Property() {
		return this.spInf5;
	}

	public final int getSpInf5() {
		return this.spInf5Property().get();
	}

	public final void setSpInf5(final int spInf5) {
		this.spInf5Property().set(spInf5);
	}

	public final IntegerProperty spInf6Property() {
		return this.spInf6;
	}

	public final int getSpInf6() {
		return this.spInf6Property().get();
	}

	public final void setSpInf6(final int spInf6) {
		this.spInf6Property().set(spInf6);
	}

	public final IntegerProperty spInf7Property() {
		return this.spInf7;
	}

	public final int getSpInf7() {
		return this.spInf7Property().get();
	}

	public final void setSpInf7(final int spInf7) {
		this.spInf7Property().set(spInf7);
	}

	public final IntegerProperty spInf8Property() {
		return this.spInf8;
	}

	public final int getSpInf8() {
		return this.spInf8Property().get();
	}

	public final void setSpInf8(final int spInf8) {
		this.spInf8Property().set(spInf8);
	}

	public final IntegerProperty spInf9Property() {
		return this.spInf9;
	}

	public final int getSpInf9() {
		return this.spInf9Property().get();
	}

	public final void setSpInf9(final int spInf9) {
		this.spInf9Property().set(spInf9);
	}

	public final IntegerProperty spInf10Property() {
		return this.spInf10;
	}

	public final int getSpInf10() {
		return this.spInf10Property().get();
	}

	public final void setSpInf10(final int spInf10) {
		this.spInf10Property().set(spInf10);
	}

	public final IntegerProperty spInf11Property() {
		return this.spInf11;
	}

	public final int getSpInf11() {
		return this.spInf11Property().get();
	}

	public final void setSpInf11(final int spInf11) {
		this.spInf11Property().set(spInf11);
	}

	public final IntegerProperty spInf12Property() {
		return this.spInf12;
	}

	public final int getSpInf12() {
		return this.spInf12Property().get();
	}

	public final void setSpInf12(final int spInf12) {
		this.spInf12Property().set(spInf12);
	}

	public final IntegerProperty spInf13Property() {
		return this.spInf13;
	}

	public final int getSpInf13() {
		return this.spInf13Property().get();
	}

	public final void setSpInf13(final int spInf13) {
		this.spInf13Property().set(spInf13);
	}

	public final IntegerProperty spInf14Property() {
		return this.spInf14;
	}

	public final int getSpInf14() {
		return this.spInf14Property().get();
	}

	public final void setSpInf14(final int spInf14) {
		this.spInf14Property().set(spInf14);
	}

	public final IntegerProperty spInf15Property() {
		return this.spInf15;
	}

	public final int getSpInf15() {
		return this.spInf15Property().get();
	}

	public final void setSpInf15(final int spInf15) {
		this.spInf15Property().set(spInf15);
	}

	public final IntegerProperty spInf16Property() {
		return this.spInf16;
	}

	public final int getSpInf16() {
		return this.spInf16Property().get();
	}

	public final void setSpInf16(final int spInf16) {
		this.spInf16Property().set(spInf16);
	}

	public final IntegerProperty spInf17Property() {
		return this.spInf17;
	}

	public final int getSpInf17() {
		return this.spInf17Property().get();
	}

	public final void setSpInf17(final int spInf17) {
		this.spInf17Property().set(spInf17);
	}

	public final IntegerProperty spInf18Property() {
		return this.spInf18;
	}

	public final int getSpInf18() {
		return this.spInf18Property().get();
	}

	public final void setSpInf18(final int spInf18) {
		this.spInf18Property().set(spInf18);
	}

	public final IntegerProperty spInf19Property() {
		return this.spInf19;
	}

	public final int getSpInf19() {
		return this.spInf19Property().get();
	}

	public final void setSpInf19(final int spInf19) {
		this.spInf19Property().set(spInf19);
	}

	public final IntegerProperty spInf20Property() {
		return this.spInf20;
	}

	public final int getSpInf20() {
		return this.spInf20Property().get();
	}

	public final void setSpInf20(final int spInf20) {
		this.spInf20Property().set(spInf20);
	}

	public final IntegerProperty spInf21Property() {
		return this.spInf21;
	}

	public final int getSpInf21() {
		return this.spInf21Property().get();
	}

	public final void setSpInf21(final int spInf21) {
		this.spInf21Property().set(spInf21);
	}

	public final IntegerProperty spInf22Property() {
		return this.spInf22;
	}

	public final int getSpInf22() {
		return this.spInf22Property().get();
	}

	public final void setSpInf22(final int spInf22) {
		this.spInf22Property().set(spInf22);
	}

	public final IntegerProperty spInf23Property() {
		return this.spInf23;
	}

	public final int getSpInf23() {
		return this.spInf23Property().get();
	}

	public final void setSpInf23(final int spInf23) {
		this.spInf23Property().set(spInf23);
	}

	public final IntegerProperty spInf24Property() {
		return this.spInf24;
	}

	public final int getSpInf24() {
		return this.spInf24Property().get();
	}

	public final void setSpInf24(final int spInf24) {
		this.spInf24Property().set(spInf24);
	}

	public final IntegerProperty spInf25Property() {
		return this.spInf25;
	}

	public final int getSpInf25() {
		return this.spInf25Property().get();
	}

	public final void setSpInf25(final int spInf25) {
		this.spInf25Property().set(spInf25);
	}

	public final IntegerProperty spInf26Property() {
		return this.spInf26;
	}

	public final int getSpInf26() {
		return this.spInf26Property().get();
	}

	public final void setSpInf26(final int spInf26) {
		this.spInf26Property().set(spInf26);
	}

	public final IntegerProperty spInf27Property() {
		return this.spInf27;
	}

	public final int getSpInf27() {
		return this.spInf27Property().get();
	}

	public final void setSpInf27(final int spInf27) {
		this.spInf27Property().set(spInf27);
	}

	public final IntegerProperty spInf28Property() {
		return this.spInf28;
	}

	public final int getSpInf28() {
		return this.spInf28Property().get();
	}

	public final void setSpInf28(final int spInf28) {
		this.spInf28Property().set(spInf28);
	}

	public final IntegerProperty spInf29Property() {
		return this.spInf29;
	}

	public final int getSpInf29() {
		return this.spInf29Property().get();
	}

	public final void setSpInf29(final int spInf29) {
		this.spInf29Property().set(spInf29);
	}

	public final StringProperty spInf39EncryptedProperty() {
		return this.spInf39Encrypted;
	}

	public final String getSpInf39Encrypted() {
		return this.spInf39EncryptedProperty().get();
	}

	public final void setSpInf39Encrypted(final String spInf39Encrypted) {
		this.spInf39EncryptedProperty().set(spInf39Encrypted);
	}

	public final StringProperty spInf39DecryptedProperty() {
		return this.spInf39Decrypted;
	}

	public final String getSpInf39Decrypted() {
		return this.spInf39DecryptedProperty().get();
	}

	public final void setSpInf39Decrypted(final String spInf39Decrypted) {
		this.spInf39DecryptedProperty().set(spInf39Decrypted);
	}

	public final StringProperty spInf40EncryptedProperty() {
		return this.spInf40Encrypted;
	}

	public final String getSpInf40Encrypted() {
		return this.spInf40EncryptedProperty().get();
	}

	public final void setSpInf40Encrypted(final String spInf40Encrypted) {
		this.spInf40EncryptedProperty().set(spInf40Encrypted);
	}

	public final StringProperty spInf40DecryptedProperty() {
		return this.spInf40Decrypted;
	}

	public final String getSpInf40Decrypted() {
		return this.spInf40DecryptedProperty().get();
	}

	public final void setSpInf40Decrypted(final String spInf40Decrypted) {
		this.spInf40DecryptedProperty().set(spInf40Decrypted);
	}

	public final StringProperty spInf41EncryptedProperty() {
		return this.spInf41Encrypted;
	}

	public final String getSpInf41Encrypted() {
		return this.spInf41EncryptedProperty().get();
	}

	public final void setSpInf41Encrypted(final String spInf41Encrypted) {
		this.spInf41EncryptedProperty().set(spInf41Encrypted);
	}

	public final StringProperty spInf41DecryptedProperty() {
		return this.spInf41Decrypted;
	}

	public final String getSpInf41Decrypted() {
		return this.spInf41DecryptedProperty().get();
	}

	public final void setSpInf41Decrypted(final String spInf41Decrypted) {
		this.spInf41DecryptedProperty().set(spInf41Decrypted);
	}

	public final IntegerProperty spInf30Property() {
		return this.spInf30;
	}

	public final int getSpInf30() {
		return this.spInf30Property().get();
	}

	public final void setSpInf30(final int spInf30) {
		this.spInf30Property().set(spInf30);
	}

	public final IntegerProperty spInf31Property() {
		return this.spInf31;
	}

	public final int getSpInf31() {
		return this.spInf31Property().get();
	}

	public final void setSpInf31(final int spInf31) {
		this.spInf31Property().set(spInf31);
	}

	public final IntegerProperty spInf32Property() {
		return this.spInf32;
	}

	public final int getSpInf32() {
		return this.spInf32Property().get();
	}

	public final void setSpInf32(final int spInf32) {
		this.spInf32Property().set(spInf32);
	}

	public final IntegerProperty spInf33Property() {
		return this.spInf33;
	}

	public final int getSpInf33() {
		return this.spInf33Property().get();
	}

	public final void setSpInf33(final int spInf33) {
		this.spInf33Property().set(spInf33);
	}

	public final IntegerProperty spInf34Property() {
		return this.spInf34;
	}

	public final int getSpInf34() {
		return this.spInf34Property().get();
	}

	public final void setSpInf34(final int spInf34) {
		this.spInf34Property().set(spInf34);
	}

	public final IntegerProperty spInf35Property() {
		return this.spInf35;
	}

	public final int getSpInf35() {
		return this.spInf35Property().get();
	}

	public final void setSpInf35(final int spInf35) {
		this.spInf35Property().set(spInf35);
	}

	public final IntegerProperty spInf36Property() {
		return this.spInf36;
	}

	public final int getSpInf36() {
		return this.spInf36Property().get();
	}

	public final void setSpInf36(final int spInf36) {
		this.spInf36Property().set(spInf36);
	}

	public final IntegerProperty spInf37Property() {
		return this.spInf37;
	}

	public final int getSpInf37() {
		return this.spInf37Property().get();
	}

	public final void setSpInf37(final int spInf37) {
		this.spInf37Property().set(spInf37);
	}

	public final IntegerProperty spInf38Property() {
		return this.spInf38;
	}

	public final int getSpInf38() {
		return this.spInf38Property().get();
	}

	public final void setSpInf38(final int spInf38) {
		this.spInf38Property().set(spInf38);
	}

	public final IntegerProperty spInf42Property() {
		return this.spInf42;
	}

	public final int getSpInf42() {
		return this.spInf42Property().get();
	}

	public final void setSpInf42(final int spInf42) {
		this.spInf42Property().set(spInf42);
	}

	public final IntegerProperty spInf43Property() {
		return this.spInf43;
	}

	public final int getSpInf43() {
		return this.spInf43Property().get();
	}

	public final void setSpInf43(final int spInf43) {
		this.spInf43Property().set(spInf43);
	}

	public final IntegerProperty spInf44Property() {
		return this.spInf44;
	}

	public final int getSpInf44() {
		return this.spInf44Property().get();
	}

	public final void setSpInf44(final int spInf44) {
		this.spInf44Property().set(spInf44);
	}

	public final IntegerProperty spInf45Property() {
		return this.spInf45;
	}

	public final int getSpInf45() {
		return this.spInf45Property().get();
	}

	public final void setSpInf45(final int spInf45) {
		this.spInf45Property().set(spInf45);
	}

	public final IntegerProperty spInf46Property() {
		return this.spInf46;
	}

	public final int getSpInf46() {
		return this.spInf46Property().get();
	}

	public final void setSpInf46(final int spInf46) {
		this.spInf46Property().set(spInf46);
	}

	public final StringProperty spInf47Property() {
		return this.spInf47;
	}

	public final String getSpInf47() {
		return this.spInf47Property().get();
	}

	public final void setSpInf47(final String spInf47) {
		this.spInf47Property().set(spInf47);
	}

	public final IntegerProperty spInf48Property() {
		return this.spInf48;
	}

	public final int getSpInf48() {
		return this.spInf48Property().get();
	}

	public final void setSpInf48(final int spInf48) {
		this.spInf48Property().set(spInf48);
	}

	public final IntegerProperty spInf49Property() {
		return this.spInf49;
	}

	public final int getSpInf49() {
		return this.spInf49Property().get();
	}

	public final void setSpInf49(final int spInf49) {
		this.spInf49Property().set(spInf49);
	}

	public final IntegerProperty spInf50Property() {
		return this.spInf50;
	}

	public final int getSpInf50() {
		return this.spInf50Property().get();
	}

	public final void setSpInf50(final int spInf50) {
		this.spInf50Property().set(spInf50);
	}

	public final IntegerProperty spInf51Property() {
		return this.spInf51;
	}

	public final int getSpInf51() {
		return this.spInf51Property().get();
	}

	public final void setSpInf51(final int spInf51) {
		this.spInf51Property().set(spInf51);
	}

	public final StringProperty spInf52EncryptedProperty() {
		return this.spInf52Encrypted;
	}

	public final String getSpInf52Encrypted() {
		return this.spInf52EncryptedProperty().get();
	}

	public final void setSpInf52Encrypted(final String spInf52Encrypted) {
		this.spInf52EncryptedProperty().set(spInf52Encrypted);
	}

	public final StringProperty spInf52DecryptedProperty() {
		return this.spInf52Decrypted;
	}

	public final String getSpInf52Decrypted() {
		return this.spInf52DecryptedProperty().get();
	}

	public final void setSpInf52Decrypted(final String spInf52Decrypted) {
		this.spInf52DecryptedProperty().set(spInf52Decrypted);
	}

	public final StringProperty spInf53EncryptedProperty() {
		return this.spInf53Encrypted;
	}

	public final String getSpInf53Encrypted() {
		return this.spInf53EncryptedProperty().get();
	}

	public final void setSpInf53Encrypted(final String spInf53Encrypted) {
		this.spInf53EncryptedProperty().set(spInf53Encrypted);
	}

	public final StringProperty spInf53DecryptedProperty() {
		return this.spInf53Decrypted;
	}

	public final String getSpInf53Decrypted() {
		return this.spInf53DecryptedProperty().get();
	}

	public final void setSpInf53Decrypted(final String spInf53Decrypted) {
		this.spInf53DecryptedProperty().set(spInf53Decrypted);
	}

	public final IntegerProperty spInf54Property() {
		return this.spInf54;
	}

	public final int getSpInf54() {
		return this.spInf54Property().get();
	}

	public final void setSpInf54(final int spInf54) {
		this.spInf54Property().set(spInf54);
	}

	public final IntegerProperty spInf55Property() {
		return this.spInf55;
	}

	public final int getSpInf55() {
		return this.spInf55Property().get();
	}

	public final void setSpInf55(final int spInf55) {
		this.spInf55Property().set(spInf55);
	}

	public final IntegerProperty spInf56Property() {
		return this.spInf56;
	}

	public final int getSpInf56() {
		return this.spInf56Property().get();
	}

	public final void setSpInf56(final int spInf56) {
		this.spInf56Property().set(spInf56);
	}

	public final IntegerProperty spInf57Property() {
		return this.spInf57;
	}

	public final int getSpInf57() {
		return this.spInf57Property().get();
	}

	public final void setSpInf57(final int spInf57) {
		this.spInf57Property().set(spInf57);
	}

	public final IntegerProperty spInf58Property() {
		return this.spInf58;
	}

	public final int getSpInf58() {
		return this.spInf58Property().get();
	}

	public final void setSpInf58(final int spInf58) {
		this.spInf58Property().set(spInf58);
	}

	public final IntegerProperty spInf59Property() {
		return this.spInf59;
	}

	public final int getSpInf59() {
		return this.spInf59Property().get();
	}

	public final void setSpInf59(final int spInf59) {
		this.spInf59Property().set(spInf59);
	}

	public final IntegerProperty spInf60Property() {
		return this.spInf60;
	}

	public final int getSpInf60() {
		return this.spInf60Property().get();
	}

	public final void setSpInf60(final int spInf60) {
		this.spInf60Property().set(spInf60);
	}

	public final IntegerProperty spInf61Property() {
		return this.spInf61;
	}

	public final int getSpInf61() {
		return this.spInf61Property().get();
	}

	public final void setSpInf61(final int spInf61) {
		this.spInf61Property().set(spInf61);
	}

	public final IntegerProperty spInf62Property() {
		return this.spInf62;
	}

	public final int getSpInf62() {
		return this.spInf62Property().get();
	}

	public final void setSpInf62(final int spInf62) {
		this.spInf62Property().set(spInf62);
	}

	public final StringProperty spInf63EncryptedProperty() {
		return this.spInf63Encrypted;
	}

	public final String getSpInf63Encrypted() {
		return this.spInf63EncryptedProperty().get();
	}

	public final void setSpInf63Encrypted(final String spInf63Encrypted) {
		this.spInf63EncryptedProperty().set(spInf63Encrypted);
	}

	public final StringProperty spInf63DecryptedProperty() {
		return this.spInf63Decrypted;
	}

	public final String getSpInf63Decrypted() {
		return this.spInf63DecryptedProperty().get();
	}

	public final void setSpInf63Decrypted(final String spInf63Decrypted) {
		this.spInf63DecryptedProperty().set(spInf63Decrypted);
	}

	public final IntegerProperty spInf64Property() {
		return this.spInf64;
	}

	public final int getSpInf64() {
		return this.spInf64Property().get();
	}

	public final void setSpInf64(final int spInf64) {
		this.spInf64Property().set(spInf64);
	}

	public final IntegerProperty spInf65Property() {
		return this.spInf65;
	}

	public final int getSpInf65() {
		return this.spInf65Property().get();
	}

	public final void setSpInf65(final int spInf65) {
		this.spInf65Property().set(spInf65);
	}

	public final IntegerProperty spInf66Property() {
		return this.spInf66;
	}

	public final int getSpInf66() {
		return this.spInf66Property().get();
	}

	public final void setSpInf66(final int spInf66) {
		this.spInf66Property().set(spInf66);
	}

	public final IntegerProperty spInf67Property() {
		return this.spInf67;
	}

	public final int getSpInf67() {
		return this.spInf67Property().get();
	}

	public final void setSpInf67(final int spInf67) {
		this.spInf67Property().set(spInf67);
	}

	public final IntegerProperty spInf68Property() {
		return this.spInf68;
	}

	public final int getSpInf68() {
		return this.spInf68Property().get();
	}

	public final void setSpInf68(final int spInf68) {
		this.spInf68Property().set(spInf68);
	}

	public final IntegerProperty spInf69Property() {
		return this.spInf69;
	}

	public final int getSpInf69() {
		return this.spInf69Property().get();
	}

	public final void setSpInf69(final int spInf69) {
		this.spInf69Property().set(spInf69);
	}

	public final IntegerProperty spInf70Property() {
		return this.spInf70;
	}

	public final int getSpInf70() {
		return this.spInf70Property().get();
	}

	public final void setSpInf70(final int spInf70) {
		this.spInf70Property().set(spInf70);
	}

	public final IntegerProperty spInf71Property() {
		return this.spInf71;
	}

	public final int getSpInf71() {
		return this.spInf71Property().get();
	}

	public final void setSpInf71(final int spInf71) {
		this.spInf71Property().set(spInf71);
	}

	public final IntegerProperty spInf72Property() {
		return this.spInf72;
	}

	public final int getSpInf72() {
		return this.spInf72Property().get();
	}

	public final void setSpInf72(final int spInf72) {
		this.spInf72Property().set(spInf72);
	}

	public final IntegerProperty spInf73Property() {
		return this.spInf73;
	}

	public final int getSpInf73() {
		return this.spInf73Property().get();
	}

	public final void setSpInf73(final int spInf73) {
		this.spInf73Property().set(spInf73);
	}

	public final IntegerProperty spInf74Property() {
		return this.spInf74;
	}

	public final int getSpInf74() {
		return this.spInf74Property().get();
	}

	public final void setSpInf74(final int spInf74) {
		this.spInf74Property().set(spInf74);
	}

	public final IntegerProperty spInf75Property() {
		return this.spInf75;
	}

	public final int getSpInf75() {
		return this.spInf75Property().get();
	}

	public final void setSpInf75(final int spInf75) {
		this.spInf75Property().set(spInf75);
	}

	public final IntegerProperty spInf76Property() {
		return this.spInf76;
	}

	public final int getSpInf76() {
		return this.spInf76Property().get();
	}

	public final void setSpInf76(final int spInf76) {
		this.spInf76Property().set(spInf76);
	}
}

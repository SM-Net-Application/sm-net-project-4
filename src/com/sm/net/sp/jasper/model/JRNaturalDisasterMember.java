package com.sm.net.sp.jasper.model;

import com.sm.net.sp.model.Member;

public class JRNaturalDisasterMember {

	private String surname;
	private String firstName;
	private String surnameWife;
	private String mobile;
	private String mail;

	public JRNaturalDisasterMember(Member member) {
		this.surname = member.getSpInf2Decrypted();
		this.firstName = member.getSpInf1Decrypted();
		this.surnameWife = member.getSpInf39Decrypted();
		this.mobile = member.getSpInf40Decrypted();
		this.mail = member.getSpInf41Decrypted();
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSurnameWife() {
		return surnameWife;
	}

	public void setSurnameWife(String surnameWife) {
		this.surnameWife = surnameWife;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}

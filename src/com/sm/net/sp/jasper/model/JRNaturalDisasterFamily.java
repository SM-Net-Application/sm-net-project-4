package com.sm.net.sp.jasper.model;

import java.util.ArrayList;

import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;

import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JRNaturalDisasterFamily {

	private ArrayList<JRNaturalDisasterMember> members;
	private JRBeanCollectionDataSource jrMembersDataSource;
	private String street;
	private String number;
	private String zipCode;
	private String city;
	private String telephone;

	public JRNaturalDisasterFamily(Family family, ObservableList<Member> membersList) {

		this.street = family.getSpInf2Decrypted();
		this.number = family.getSpInf3Decrypted();
		this.zipCode = family.getSpInf4Decrypted();
		this.city = family.getSpInf5Decrypted();
		this.telephone = family.getSpInf7Decrypted();

		buildMemberList(family.getSpFamID(), membersList);
	}

	private void buildMemberList(int spFamID, ObservableList<Member> membersList) {

		for (Member member : membersList)
			if (member.getSpInf5() == spFamID)
				this.members.add(new JRNaturalDisasterMember(member));

		this.jrMembersDataSource = new JRBeanCollectionDataSource(this.members);
	}

	public ArrayList<JRNaturalDisasterMember> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<JRNaturalDisasterMember> members) {
		this.members = members;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public JRBeanCollectionDataSource getJrMembersDataSource() {
		return jrMembersDataSource;
	}

	public void setJrMembersDataSource(JRBeanCollectionDataSource jrMembersDataSource) {
		this.jrMembersDataSource = jrMembersDataSource;
	}
}

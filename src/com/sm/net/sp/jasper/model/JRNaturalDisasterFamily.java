package com.sm.net.sp.jasper.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;

import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JRNaturalDisasterFamily {

	private ArrayList<JRNaturalDisasterMember> members;
	private JRBeanCollectionDataSource jrMembersDataSource;
	private JasperReport memberJasperReport;
	private String street;
	private String number;
	private String zipCode;
	private String city;
	private String telephone;
	private String address;
	private Map<String, Object> parameters;

	public JRNaturalDisasterFamily(Family family, JasperReport memberJasperReport, ObservableList<Member> membersList) {

		this.members = new ArrayList<>();
		this.memberJasperReport = memberJasperReport;
		this.street = family.getSpInf2Decrypted();
		this.number = family.getSpInf3Decrypted();
		this.zipCode = family.getSpInf4Decrypted();
		this.city = family.getSpInf5Decrypted();
		this.telephone = family.getSpInf7Decrypted();

		buildAddress();
		buildMemberList(family.getSpFamID(), membersList);
		buildParameters();
	}

	private void buildParameters() {
		this.parameters = new HashMap<String, Object>();
		parameters.put("totalMembers", this.members.size());
	}

	private void buildAddress() {

		this.address = "";

		if (!this.street.isEmpty())
			this.address += this.street;

		if (!this.number.isEmpty())
			this.address += " " + this.number;

		if (!this.address.isEmpty() && (!this.zipCode.isEmpty() || !this.city.isEmpty()))
			this.address += "<br>";

		if (!this.zipCode.isEmpty())
			this.address += this.zipCode;

		if (!this.city.isEmpty())
			this.address += " " + this.city;

		if (!this.address.isEmpty() && !this.telephone.isEmpty())
			this.address += "<br>";

		if (!this.telephone.isEmpty())
			this.address += this.telephone;
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

	public JasperReport getMemberJasperReport() {
		return memberJasperReport;
	}

	public void setMemberJasperReport(JasperReport memberJasperReport) {
		this.memberJasperReport = memberJasperReport;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
}

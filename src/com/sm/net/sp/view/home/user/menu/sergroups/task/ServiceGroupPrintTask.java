package com.sm.net.sp.view.home.user.menu.sergroups.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

import com.sm.net.sp.Meta;
import com.sm.net.sp.jasper.Jasper;
import com.sm.net.sp.jasper.model.JRServiceGroupRow;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.SerGroup;
import com.sm.net.sp.settings.Settings;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ServiceGroupPrintTask implements TaskInterface {

	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;
	private String congregationName;
	private ObservableList<SerGroup> list;
	private boolean complete;

	private ObservableList<Member> membersList;
	private ObservableList<Family> familiesList;

	public ServiceGroupPrintTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, String congregationName,
			ObservableList<Member> membersList, ObservableList<Family> familiesList, ObservableList<SerGroup> list,
			boolean complete) {
		super();

		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
		this.congregationName = congregationName;
		this.membersList = membersList;
		this.familiesList = familiesList;
		this.list = list;
		this.complete = complete;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		String jrxmlServiceGroup = "";

		switch (this.list.size()) {
		case 5:
			jrxmlServiceGroup = Jasper.Layouts.SM_NET_SERVICE_GROUP5.getAbsolutePath();
			break;
		default:
			break;
		}

		if (!jrxmlServiceGroup.isEmpty()) {

			try {

				JasperReport jasperReportServiceGroup = JasperCompileManager.compileReport(jrxmlServiceGroup);

				String congregationNamePattern = this.settings.getLanguage()
						.getString("jasper.layout.meeting.congregation");
				String congregationName = String.format(congregationNamePattern, this.congregationName);

				String programmName = this.settings.getLanguage().getString("jasper.layout.servicegroup.programmname");

				String programmType = this.complete
						? this.settings.getLanguage().getString("jasper.layout.servicegroup.programmtype")
						: "";

				String printDateText = this.settings.getLanguage()
						.getString("jasper.layout.servicegroup.printdatetext");

				String printDatePattern = this.settings.getLanguage()
						.getString("jasper.layout.servicegroup.printdatepattern");

				String printDateValue = DateTimeFormatter.ofPattern(printDatePattern).format(LocalDate.now());

				String overseerText = this.settings.getLanguage().getString("jasper.layout.servicegroup.overseertext");

				String assistantText = this.settings.getLanguage()
						.getString("jasper.layout.servicegroup.assistanttext");

				String familyText = this.settings.getLanguage().getString("jasper.layout.servicegroup.familytext");
				String familyNumText = this.settings.getLanguage()
						.getString("jasper.layout.servicegroup.familynumtext");

				String serviceGroupName1 = "";
				String serviceGroupName2 = "";
				String serviceGroupName3 = "";
				String serviceGroupName4 = "";
				String serviceGroupName5 = "";

				serviceGroupName1 = this.list.get(0).getSpInf1Decrypted();

				if (this.list.size() > 1)
					serviceGroupName2 = this.list.get(1).getSpInf1Decrypted();

				if (this.list.size() > 2)
					serviceGroupName3 = this.list.get(2).getSpInf1Decrypted();

				if (this.list.size() > 3)
					serviceGroupName4 = this.list.get(3).getSpInf1Decrypted();

				if (this.list.size() > 4)
					serviceGroupName5 = this.list.get(4).getSpInf1Decrypted();

				String overseer1 = "";
				String overseer2 = "";
				String overseer3 = "";
				String overseer4 = "";
				String overseer5 = "";

				overseer1 = this.list.get(0).getOverseer();

				if (this.list.size() > 1)
					overseer2 = this.list.get(1).getOverseer();

				if (this.list.size() > 2)
					overseer3 = this.list.get(2).getOverseer();

				if (this.list.size() > 3)
					overseer4 = this.list.get(3).getOverseer();

				if (this.list.size() > 4)
					overseer5 = this.list.get(4).getOverseer();

				String assistant1 = "";
				String assistant2 = "";
				String assistant3 = "";
				String assistant4 = "";
				String assistant5 = "";

				assistant1 = this.list.get(0).getAssistant();

				if (this.list.size() > 1)
					assistant2 = this.list.get(1).getAssistant();

				if (this.list.size() > 2)
					assistant3 = this.list.get(2).getAssistant();

				if (this.list.size() > 3)
					assistant4 = this.list.get(3).getAssistant();

				if (this.list.size() > 4)
					assistant5 = this.list.get(4).getAssistant();

				// FAMILY BUILD

				ArrayList<FamilyInfo> familiesGroup1 = new ArrayList<>();
				ArrayList<FamilyInfo> familiesGroup2 = new ArrayList<>();
				ArrayList<FamilyInfo> familiesGroup3 = new ArrayList<>();
				ArrayList<FamilyInfo> familiesGroup4 = new ArrayList<>();
				ArrayList<FamilyInfo> familiesGroup5 = new ArrayList<>();

				familiesGroup1 = buildFamily(this.list.get(0).getSpSerGrID());

				if (this.list.size() > 1)
					familiesGroup2 = buildFamily(this.list.get(1).getSpSerGrID());

				if (this.list.size() > 2)
					familiesGroup3 = buildFamily(this.list.get(2).getSpSerGrID());

				if (this.list.size() > 3)
					familiesGroup4 = buildFamily(this.list.get(3).getSpSerGrID());

				if (this.list.size() > 4)
					familiesGroup5 = buildFamily(this.list.get(4).getSpSerGrID());

				// BUILD ROWS

				ArrayList<JRServiceGroupRow> rows = buildJRServiceGroupRows(familiesGroup1, familiesGroup2,
						familiesGroup3, familiesGroup4, familiesGroup5);

				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rows);

				// String weekReportFile =
				// Jasper.Layouts.SM_NET_MEETINGS_WEEK_COMPLETE.getAbsolutePath();

//			ArrayList<JRWeek> jrWeeks = new ArrayList<>();
//			for (Week week : weeks) {
//
//				JRWeek newJRWeek = JRWeek.newObject(week, membersList, language, extendedName, true);
//
//				String congregationNameHeader = String
//						.format(language.getString("jasper.layout.meeting.congregation"), congregationName);
//
//				newJRWeek.setCongregationName(congregationNameHeader);
//
//				String programmName = JRWeek.getProgrammNameHeader(week, language);
//
//				newJRWeek.setProgrammName(programmName);
//
//				jrWeeks.add(newJRWeek);
//			}

				// JRBeanCollectionDataSource jrWeeksDataSource = new
				// JRBeanCollectionDataSource(jrWeeks);

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("congregationName", congregationName);
				parameters.put("programmName", programmName);
				parameters.put("programmType", programmType);
				parameters.put("printDateText", printDateText);
				parameters.put("printDateValue", printDateValue);

				parameters.put("serviceGroupName1", serviceGroupName1);
				parameters.put("serviceGroupName2", serviceGroupName2);
				parameters.put("serviceGroupName3", serviceGroupName3);
				parameters.put("serviceGroupName4", serviceGroupName4);
				parameters.put("serviceGroupName5", serviceGroupName5);

				parameters.put("overseer1", overseer1);
				parameters.put("overseer2", overseer2);
				parameters.put("overseer3", overseer3);
				parameters.put("overseer4", overseer4);
				parameters.put("overseer5", overseer5);

				parameters.put("assistant1", assistant1);
				parameters.put("assistant2", assistant2);
				parameters.put("assistant3", assistant3);
				parameters.put("assistant4", assistant4);
				parameters.put("assistant5", assistant5);

				parameters.put("overseerText", overseerText);
				parameters.put("assistantText", assistantText);

				parameters.put("familyText", familyText);
				parameters.put("familyNumText", familyNumText);

				String familyFooterPattern = this.settings.getLanguage()
						.getString("jasper.layout.servicegroup.familyfooterpattern");

				parameters.put("familyGroup1", String.format(familyFooterPattern, familiesGroup1.size()));
				parameters.put("familyGroup2", String.format(familyFooterPattern, familiesGroup2.size()));
				parameters.put("familyGroup3", String.format(familyFooterPattern, familiesGroup3.size()));
				parameters.put("familyGroup4", String.format(familyFooterPattern, familiesGroup4.size()));
				parameters.put("familyGroup5", String.format(familyFooterPattern, familiesGroup5.size()));

				parameters.put("familyNumGroup1", getTotal(familiesGroup1));
				parameters.put("familyNumGroup2", getTotal(familiesGroup2));
				parameters.put("familyNumGroup3", getTotal(familiesGroup3));
				parameters.put("familyNumGroup4", getTotal(familiesGroup4));
				parameters.put("familyNumGroup5", getTotal(familiesGroup5));

//				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportServiceGroup, parameters,
//						new JREmptyDataSource());

				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportServiceGroup, parameters,
						dataSource);

				JasperViewer jv = new JasperViewer(jasperPrint, false);
				jv.setTitle(Meta.Application.getFullTitle());
				jv.setIconImage(SwingFXUtils.fromFXImage(Meta.Resources.getImageApplicationIcon(), null));
				jv.setVisible(true);

			} catch (JRException e) {
				this.alertBuilder.error(this.viewStage, e.getMessage());
			}

		} else {
			hashMap.put("status", "0");
		}
	}

	private String getTotal(ArrayList<FamilyInfo> familiesGroup) {

		int total = 0;

		for (FamilyInfo fi : familiesGroup)
			total += fi.getFamilyNum();

		return String.valueOf(total);
	}

	private ArrayList<JRServiceGroupRow> buildJRServiceGroupRows(ArrayList<FamilyInfo> f1, ArrayList<FamilyInfo> f2,
			ArrayList<FamilyInfo> f3, ArrayList<FamilyInfo> f4, ArrayList<FamilyInfo> f5) {

		ArrayList<JRServiceGroupRow> rows = new ArrayList<JRServiceGroupRow>();

		int size = 0;

		if (f1.size() > size)
			size = f1.size();

		if (f2.size() > size)
			size = f2.size();

		if (f3.size() > size)
			size = f3.size();

		if (f4.size() > size)
			size = f4.size();

		if (f5.size() > size)
			size = f5.size();

		for (int i = 0; i < size; i++) {

			String name1 = "";
			String name2 = "";
			String name3 = "";
			String name4 = "";
			String name5 = "";

			int num1 = -1;
			int num2 = -1;
			int num3 = -1;
			int num4 = -1;
			int num5 = -1;

			if (f1.size() > i) {
				name1 = f1.get(i).getFamilyName();
				num1 = f1.get(i).getFamilyNum();
			}
			if (f2.size() > i) {
				name2 = f2.get(i).getFamilyName();
				num2 = f2.get(i).getFamilyNum();
			}
			if (f3.size() > i) {
				name3 = f3.get(i).getFamilyName();
				num3 = f3.get(i).getFamilyNum();
			}
			if (f4.size() > i) {
				name4 = f4.get(i).getFamilyName();
				num4 = f4.get(i).getFamilyNum();
			}
			if (f5.size() > i) {
				name5 = f5.get(i).getFamilyName();
				num5 = f5.get(i).getFamilyNum();
			}

			rows.add(new JRServiceGroupRow(name1, num1, name2, num2, name3, num3, name4, num4, name5, num5));
		}

		return rows;
	}

	private ArrayList<FamilyInfo> buildFamily(int serviceGroupID) {

		ArrayList<FamilyInfo> list = new ArrayList<>();

		StreamSupport.stream(this.familiesList.spliterator(), false)
				.filter(family -> (family.getSpInf6() == serviceGroupID)).forEach(family -> checkFamily(family, list));

		list.sort((o1, o2) -> o1.getFamilyName().compareTo(o2.getFamilyName()));

		return list;
	}

	private void checkFamily(Family family, ArrayList<FamilyInfo> list) {

		if (complete)
			checkFamilyAdd(family, list);
		else {
			boolean exclude = family.getSpInf8() == 1;
			if (!exclude)
				checkFamilyAdd(family, list);
		}

	}

	private void checkFamilyAdd(Family family, ArrayList<FamilyInfo> list) {

		String familyName = family.getSpInf1Decrypted();
		int familyNum = checkFamilyMembers(family.getSpFamID());

		if (checkFamilyMembersInactive(family.getSpFamID()))
			familyName += "*";

		if (familyNum > 0) {
			FamilyInfo familyInfo = new FamilyInfo(familyName, familyNum);
			list.add(familyInfo);
		}
	}

	private int checkFamilyMembers(int familyID) {

		int count = 0;

		for (Member m : this.membersList)
			if (m.getSpInf5() == familyID)
				if (m.getSpInf7() == 1 || m.getSpInf8() == 1) // Proclamatore
					count++;

		return count;
	}

	private boolean checkFamilyMembersInactive(int familyID) {

		for (Member m : this.membersList)
			if (m.getSpInf5() == familyID)
				if (m.getSpInf38() == 1) // Inattivo
					return true;

		return false;
	}

	@Override
	public void feedback(HashMap<String, Object> hashMap) {

		Object object = hashMap.get("status");
		if (object != null) {

			int status = (int) hashMap.get("status");

			switch (status) {
			case 0:
				this.alertBuilder.error(this.viewStage,
						this.settings.getLanguage().getString("servicegroup.print.error.layoutnotfound"));
				break;

			default:
				break;
			}
		}
	}

	public class FamilyInfo {

		private String familyName;
		private int familyNum;

		public FamilyInfo(String familyName, int familyNum) {
			super();
			this.familyName = familyName;
			this.familyNum = familyNum;
		}

		public String getFamilyName() {
			return familyName;
		}

		public void setFamilyName(String familyName) {
			this.familyName = familyName;
		}

		public int getFamilyNum() {
			return familyNum;
		}

		public void setFamilyNum(int familyNum) {
			this.familyNum = familyNum;
		}
	}
}

package com.sm.net.sp.jasper.model;

import java.util.ArrayList;

import com.sm.net.project.Language;
import com.sm.net.sp.jasper.Jasper;
import com.sm.net.sp.model.ChristiansPart;
import com.sm.net.sp.model.MinistryPart;
import com.sm.net.sp.model.Week;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JRWeek {

	private String weekHeader;
	private String treasuresHeader;
	private String ministryPartHeader;
	private String christiansPartHeader;

	private JasperReport jasperReportMinistryPart;
	private JasperReport jasperReportChristiansPart;
	private JRBeanCollectionDataSource jrDataSourceMinistryPart;
	private JRBeanCollectionDataSource jrDataSourceChristiansPart;

	public JRWeek() {
		super();
		reset();
	}

	private void reset() {

		this.weekHeader = "";
		this.treasuresHeader = "";
		this.ministryPartHeader = "";
		this.christiansPartHeader = "";
		this.jasperReportMinistryPart = null;
		this.jasperReportChristiansPart = null;
		this.jrDataSourceMinistryPart = null;
		this.jrDataSourceChristiansPart = null;
	}

	public static JRWeek newObject(Week week, Language language) throws JRException {

		JRWeek jrWeek = new JRWeek();

		String ministryPartReport = Jasper.Layouts.SP_MINISTRY_PART_ROW_LAYOUT.getAbsolutePath();
		String christiansPartReport = Jasper.Layouts.SP_CHRISTIANS_PART_ROW_LAYOUT.getAbsolutePath();

		ArrayList<JRMinistryPart> jrMinistryPart = new ArrayList<>();
		for (MinistryPart ministryPart : week.getMinistryPartList())
			jrMinistryPart.add(JRMinistryPart.newObject(ministryPart, language));

		jrWeek.setJrDataSourceMinistryPart(new JRBeanCollectionDataSource(jrMinistryPart));

		ArrayList<JRChristiansPart> jrChristiansPart = new ArrayList<>();
		for (ChristiansPart christiansPart : week.getChristiansPartList())
			jrChristiansPart.add(JRChristiansPart.newObject(christiansPart, language));

		jrWeek.setJrDataSourceChristiansPart(new JRBeanCollectionDataSource(jrChristiansPart));
		jrWeek.setJasperReportMinistryPart(JasperCompileManager.compileReport(ministryPartReport));
		jrWeek.setJasperReportChristiansPart(JasperCompileManager.compileReport(christiansPartReport));

		jrWeek.setWeekHeader("SETTIMANA DEL ::: | GENESI 1-3");
		jrWeek.setTreasuresHeader("TESORI DELLA PAROLA DI DIO");
		jrWeek.setMinistryPartHeader("EFFICACI NEL MINISTERO");
		jrWeek.setChristiansPartHeader("VITA CRISTIANA");

		return jrWeek;
	}

	public String getWeekHeader() {
		return weekHeader;
	}

	public void setWeekHeader(String weekHeader) {
		this.weekHeader = weekHeader;
	}

	public String getTreasuresHeader() {
		return treasuresHeader;
	}

	public void setTreasuresHeader(String treasuresHeader) {
		this.treasuresHeader = treasuresHeader;
	}

	public String getMinistryPartHeader() {
		return ministryPartHeader;
	}

	public void setMinistryPartHeader(String ministryPartHeader) {
		this.ministryPartHeader = ministryPartHeader;
	}

	public String getChristiansPartHeader() {
		return christiansPartHeader;
	}

	public void setChristiansPartHeader(String christiansPartHeader) {
		this.christiansPartHeader = christiansPartHeader;
	}

	public JasperReport getJasperReportMinistryPart() {
		return jasperReportMinistryPart;
	}

	public void setJasperReportMinistryPart(JasperReport jasperReportMinistryPart) {
		this.jasperReportMinistryPart = jasperReportMinistryPart;
	}

	public JasperReport getJasperReportChristiansPart() {
		return jasperReportChristiansPart;
	}

	public void setJasperReportChristiansPart(JasperReport jasperReportChristiansPart) {
		this.jasperReportChristiansPart = jasperReportChristiansPart;
	}

	public JRBeanCollectionDataSource getJrDataSourceMinistryPart() {
		return jrDataSourceMinistryPart;
	}

	public void setJrDataSourceMinistryPart(JRBeanCollectionDataSource jrDataSourceMinistryPart) {
		this.jrDataSourceMinistryPart = jrDataSourceMinistryPart;
	}

	public JRBeanCollectionDataSource getJrDataSourceChristiansPart() {
		return jrDataSourceChristiansPart;
	}

	public void setJrDataSourceChristiansPart(JRBeanCollectionDataSource jrDataSourceChristiansPart) {
		this.jrDataSourceChristiansPart = jrDataSourceChristiansPart;
	}
}

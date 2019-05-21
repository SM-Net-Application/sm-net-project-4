package com.sm.net.sp.jasper.model;

import java.util.ArrayList;

import com.sm.net.sp.jasper.Jasper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JRWeek2 {

	private JasperReport jasperReportMinistryPart;
	private JRBeanCollectionDataSource jrDataSourceMinistryPart;

	public JRWeek2() {
		super();
	}

	public static JRWeek2 newObject(String week) throws JRException {

		JRWeek2 jrWeek = new JRWeek2();

		String ministryPartReport = Jasper.Layouts.SP_MINISTRY_PART_ROW_LAYOUT.getAbsolutePath();

		ArrayList<JRMinistryPart2> jrMinistryPart = new ArrayList<>();
		jrMinistryPart.add(new JRMinistryPart2("REPORT 3 WEEK " + week, "ROW 1"));
		jrMinistryPart.add(new JRMinistryPart2("REPORT 3 WEEK " + week, "ROW 2"));
		jrMinistryPart.add(new JRMinistryPart2("REPORT 3 WEEK " + week, "ROW 3"));

		jrWeek.setJrDataSourceMinistryPart(new JRBeanCollectionDataSource(jrMinistryPart));

		jrWeek.setJasperReportMinistryPart(JasperCompileManager.compileReport(ministryPartReport));

		return jrWeek;
	}

	public JasperReport getJasperReportMinistryPart() {
		return jasperReportMinistryPart;
	}

	public void setJasperReportMinistryPart(JasperReport jasperReportMinistryPart) {
		this.jasperReportMinistryPart = jasperReportMinistryPart;
	}

	public JRBeanCollectionDataSource getJrDataSourceMinistryPart() {
		return jrDataSourceMinistryPart;
	}

	public void setJrDataSourceMinistryPart(JRBeanCollectionDataSource jrDataSourceMinistryPart) {
		this.jrDataSourceMinistryPart = jrDataSourceMinistryPart;
	}
}

package com.sm.net.sp.view.home.user.menu.infotable.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sm.net.sp.Meta;
import com.sm.net.sp.jasper.Jasper;
import com.sm.net.sp.jasper.model.InfoTableEvent;
import com.sm.net.sp.model.PostNews;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.model.WeekMemorial;
import com.sm.net.sp.utils.DateUtils;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.util.Crypt;
import com.smnet.core.task.TaskInterface;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class InfoTablePrintTask implements TaskInterface {

	private SupportPlannerView application;
	private Stage stage;
	private HashMap<String, String> infos;
	private boolean withEvents;

	private ObservableList<PostNews> postNewsList;
	private ObservableList<WeekMemorial> memorial;
	private ObservableList<WeekConvention> convention;

	public InfoTablePrintTask(SupportPlannerView application, Stage stage, HashMap<String, String> infos,
			boolean withEvents, ObservableList<PostNews> postNewsList, ObservableList<WeekMemorial> memorial,
			ObservableList<WeekConvention> convention) {

		this.application = application;
		this.stage = stage;
		this.infos = infos;
		this.withEvents = withEvents;

		this.postNewsList = postNewsList;
		this.memorial = memorial;
		this.convention = convention;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		try {

			String programmReportFile = Jasper.Layouts.SM_NET_INFOTABLE.getAbsolutePath();
			String postReportFile = Jasper.Layouts.SM_NET_INFOTABLE_POST.getAbsolutePath();
			String eventReportFile = Jasper.Layouts.SM_NET_INFOTABLE_EVENT.getAbsolutePath();

			JasperReport programmJasperReport = JasperCompileManager.compileReport(programmReportFile);
			JasperReport postJasperReport = JasperCompileManager.compileReport(postReportFile);
			JasperReport eventJasperReport = JasperCompileManager.compileReport(eventReportFile);

			JRBeanCollectionDataSource jrPosts = new JRBeanCollectionDataSource(this.postNewsList);
			ArrayList<InfoTableEvent> events = new ArrayList<>();

			LocalDate now = LocalDate.now();
			int nowWeekNr = Integer.valueOf(DateUtils.getWeekNr(now)).intValue();

			for (WeekConvention weekConvention : convention) {

				InfoTableEvent infoTableEvent = InfoTableEvent.newInstance(this.application, now, nowWeekNr,
						weekConvention);

				if (infoTableEvent != null)
					events.add(infoTableEvent);
			}

			events.sort((o1, o2) -> o1.getEventDate().compareTo(o2.getEventDate()));

			JRBeanCollectionDataSource jrEvents = new JRBeanCollectionDataSource(events);

			String congregation = Crypt.decrypt(this.infos.get("inf1"),
					this.application.getSettings().getDatabaseSecretKey());

			String programmName = this.application.getSettings().getLanguage()
					.getString("jasper.layout.meeting.infotable");

			if (this.withEvents)
				programmName = this.application.getSettings().getLanguage()
						.getString("jasper.layout.meeting.infotablewithevents");

			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("congregationName", String.format(
					this.application.getSettings().getLanguage().getString("jasper.layout.meeting.congregation"),
					congregation));

			parameters.put("programmName", programmName);

			parameters.put("showEvents", this.withEvents);
			parameters.put("noEvents", events.isEmpty());
			parameters.put("noPosts", this.postNewsList.isEmpty());

			parameters.put("events",
					this.application.getSettings().getLanguage().getString("jasper.layout.infotable.events"));
			parameters.put("posts",
					this.application.getSettings().getLanguage().getString("jasper.layout.infotable.posts"));

			parameters.put("eventsJasperReport", eventJasperReport);
			parameters.put("eventsDataSource", jrEvents);

			parameters.put("postJasperReport", postJasperReport);
			parameters.put("postDataSource", jrPosts);

			JasperPrint jasperPrint = JasperFillManager.fillReport(programmJasperReport, parameters,
					new JREmptyDataSource());

			JasperViewer jv = new JasperViewer(jasperPrint, false);
			jv.setTitle(Meta.Application.getFullTitle());
			jv.setIconImage(SwingFXUtils.fromFXImage(Meta.Resources.getImageApplicationIcon(), null));
			jv.setVisible(true);

			hashMap.put("status", 0);
			hashMap.put("error", "");

		} catch (Exception e) {

			hashMap.put("status", 1);
			hashMap.put("error", e.getMessage());
		}

	}

	@Override
	public void feedback(HashMap<String, Object> hashMap) {

		int status = (int) hashMap.get("status");
		if (status == 1)
			this.application.getAlertBuilder2().error(this.stage, (String) hashMap.get("error"));
	}
}

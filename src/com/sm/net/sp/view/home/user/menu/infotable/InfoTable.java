package com.sm.net.sp.view.home.user.menu.infotable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.EnumPrintLayouts;
import com.sm.net.sp.model.PostNews;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.model.WeekMemorial;
import com.sm.net.sp.model.WeekOverseer;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.infotable.task.InfoTableInitDataLoadTask;
import com.sm.net.sp.view.home.user.menu.infotable.task.InfoTablePrintTask;
import com.sm.net.sp.view.printlayout.PrintLayout;
import com.smnet.core.task.TaskManager;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InfoTable {

	@FXML
	private ImageView headerImageView;
	@FXML
	private Label headerLabel;

	@FXML
	private TableView<PostNews> tableView;
	@FXML
	private TableColumn<PostNews, LocalDate> dateTableColumn;
	@FXML
	private TableColumn<PostNews, String> docTitleTableColumn;
	@FXML
	private TableColumn<PostNews, String> newsTitleTableColumn;
	@FXML
	private Button printButton;

	@FXML
	private Label docNameLabel;
	@FXML
	private Label docTitleLabel;
	@FXML
	private Label destLabel;
	@FXML
	private Label titleLabel;
	@FXML
	private Label textLabel;
	@FXML
	private TextField docNameTextField;
	@FXML
	private TextField docTitleTextField;
	@FXML
	private TextField destTextField;
	@FXML
	private TextField titleTextField;
	@FXML
	private TextArea textTextArea;

	private Settings settings;
	private Language language;
	private Stage stageSupportPlannerView;
	private SupportPlannerView application;

	private ObservableList<PostNews> postNewsList;
	private ObservableList<WeekConvention> convention;
	private ObservableList<WeekMemorial> memorial;
	private ObservableList<WeekOverseer> overseer;
	private ObservableList<Week> meeting;
	private HashMap<String, String> generalInfo;

	@FXML
	private void initialize() {
		styleClasses();
		tableColumnsCells();
	}

	private void tableColumnsCells() {

		this.dateTableColumn.setCellFactory(c -> tableCellForLocalDate());
		this.dateTableColumn.setCellValueFactory(cellData -> {

			String spInf1 = String.valueOf(cellData.getValue().getSpInf1());
			LocalDate date = LocalDate.parse(spInf1, DateTimeFormatter.ofPattern("yyyyMMdd"));
			if (date != null)
				return new SimpleObjectProperty<LocalDate>(date);

			return null;
		});

		this.docTitleTableColumn.setCellValueFactory(cellData -> cellData.getValue().getSpInf6Property());
//		this.newsDestTableColumn.setCellValueFactory(cellData -> cellData.getValue().getSpInf2Property());
		this.newsTitleTableColumn.setCellValueFactory(cellData -> cellData.getValue().getSpInf3Property());
	}

	private TableCell<PostNews, LocalDate> tableCellForLocalDate() {

		return new TableCell<PostNews, LocalDate>() {
			@Override
			protected void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);

				if (empty)
					setText(null);
				else
					setText(DateTimeFormatter.ofPattern("dd.MM.yyyy").format(item));
			}
		};
	}

	private void styleClasses() {

		this.headerImageView.setFitWidth(50);
		this.headerImageView.setFitHeight(50);
		this.headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.INFOTABLE, 50, 50));

		this.headerLabel.getStyleClass().add("label_header_001");

		this.tableView.getStyleClass().add("table_view_001");
		this.dateTableColumn.getStyleClass().add("table_column_002");

		this.printButton.getStyleClass().add("button_image_001");

		this.docNameLabel.getStyleClass().add("label_set_001");
		this.docTitleLabel.getStyleClass().add("label_set_001");
		this.destLabel.getStyleClass().add("label_set_001");
		this.titleLabel.getStyleClass().add("label_set_001");
		this.textLabel.getStyleClass().add("label_set_001");

		this.docNameTextField.getStyleClass().add("text_field_001");
		this.docTitleTextField.getStyleClass().add("text_field_001");
		this.destTextField.getStyleClass().add("text_field_001");
		this.titleTextField.getStyleClass().add("text_field_001");
		this.textTextArea.getStyleClass().add("text_area_001");
	}

	public void objectInitialize() {

		this.language = this.settings.getLanguage();

		listeners();
		viewUpdate();
		initData();
	}

	private void viewUpdate() {

		this.headerLabel.setText(this.language.getString("sp.menu.infotable"));

		this.dateTableColumn.setMinWidth(100);
		this.dateTableColumn.setMaxWidth(100);

		this.docNameTextField.setEditable(false);
		this.docTitleTextField.setEditable(false);
		this.destTextField.setEditable(false);
		this.titleTextField.setEditable(false);
		this.textTextArea.setEditable(false);

		this.destLabel.setText(this.language.getString("infotable.label.dest"));
		this.docNameLabel.setText(this.language.getString("infotable.label.docname"));
		this.docTitleLabel.setText(this.language.getString("infotable.label.doctitle"));
		this.titleLabel.setText(this.language.getString("infotable.label.title"));
		this.textLabel.setText(this.language.getString("infotable.label.text"));

		this.dateTableColumn.setText(this.language.getString("infotable.tablecolumn.date"));
		this.docTitleTableColumn.setText(this.language.getString("infotable.tablecolumn.doctitle"));
		this.newsTitleTableColumn.setText(this.language.getString("infotable.tablecolumn.title"));

		Tooltip addTooltip = new Tooltip(this.language.getString("infotable.tooltip.print"));
		addTooltip.getStyleClass().add("tooltip_001");
		this.printButton.setTooltip(addTooltip);
		this.printButton.setText(null);
		this.printButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.PRINT));
	}

	public void initData() {

		this.postNewsList = FXCollections.observableArrayList();
		this.convention = FXCollections.observableArrayList();
		this.memorial = FXCollections.observableArrayList();
		this.overseer = FXCollections.observableArrayList();
		this.meeting = FXCollections.observableArrayList();
		this.generalInfo = new HashMap<String, String>();

		this.tableView.setItems(this.postNewsList);

		String waitMessage = this.language.getString("infotable.wait.init");

		TaskManager.run(this.application.getAlertBuilder2(), this.stageSupportPlannerView, waitMessage,
				new InfoTableInitDataLoadTask(this.application.getAlertBuilder2(), this.settings,
						this.stageSupportPlannerView, this));

	}

	public void updatePostNewsList(ObservableList<PostNews> postNewsList) {

		this.postNewsList.clear();
		this.postNewsList.addAll(postNewsList);
		Platform.runLater(() -> this.tableView.refresh());
	}

	private void listeners() {

		this.tableView.getSelectionModel().selectedIndexProperty()
				.addListener((o, oldV, newV) -> selectPost(newV.intValue()));

		this.printButton.setOnAction(e -> print());
	}

	private void print() {

		EnumPrintLayouts selectedLayout = PrintLayout.dialogPrintLayout(this.stageSupportPlannerView, language, null,
				EnumPrintLayouts.INFOTABLE_POST_EVENTS, EnumPrintLayouts.INFOTABLE_POST);

		if (selectedLayout != null) {

			String waitMessage = this.language.getString("infotable.wait.print");

			switch (selectedLayout) {

			case INFOTABLE_POST:

				TaskManager.run(this.application.getAlertBuilder2(), this.stageSupportPlannerView, waitMessage,
						new InfoTablePrintTask(this.application, this.stageSupportPlannerView, this.generalInfo, false,
								this.postNewsList, this.memorial, this.convention, this.overseer, this.meeting));

				break;

			case INFOTABLE_POST_EVENTS:

				TaskManager.run(this.application.getAlertBuilder2(), this.stageSupportPlannerView, waitMessage,
						new InfoTablePrintTask(this.application, this.stageSupportPlannerView, this.generalInfo, true,
								this.postNewsList, this.memorial, this.convention, this.overseer, this.meeting));

				break;

			default:
				break;
			}

		}
	}

	private void selectPost(int intValue) {

		if (intValue > -1) {

			PostNews postNews = this.tableView.getSelectionModel().getSelectedItem();
			if (postNews != null) {

				String dest = postNews.getSpInf2();
				String title = postNews.getSpInf3();
				String text = postNews.getSpInf4();
				String docName = postNews.getSpInf5();
				String docTitle = postNews.getSpInf6();

				this.destTextField.setText(dest);
				this.titleTextField.setText(title);
				this.textTextArea.setText(text);
				this.docNameTextField.setText(docName);
				this.docTitleTextField.setText(docTitle);
			}
		}
	}

	public void resetPost() {

		this.destTextField.setText("");
		this.titleTextField.setText("");
		this.textTextArea.setText("");
		this.docNameTextField.setText("");
		this.docTitleTextField.setText("");
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Stage getStageSupportPlannerView() {
		return stageSupportPlannerView;
	}

	public void setStageSupportPlannerView(Stage stageSupportPlannerView) {
		this.stageSupportPlannerView = stageSupportPlannerView;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public ImageView getHeaderImageView() {
		return headerImageView;
	}

	public void setHeaderImageView(ImageView headerImageView) {
		this.headerImageView = headerImageView;
	}

	public Label getHeaderLabel() {
		return headerLabel;
	}

	public void setHeaderLabel(Label headerLabel) {
		this.headerLabel = headerLabel;
	}

	public Button getAddButton() {
		return printButton;
	}

	public void setAddButton(Button addButton) {
		this.printButton = addButton;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public ObservableList<PostNews> getPostNewsList() {
		return postNewsList;
	}

	public void setPostNewsList(ObservableList<PostNews> postNewsList) {
		this.postNewsList = postNewsList;
	}

	public ObservableList<WeekConvention> getConvention() {
		return convention;
	}

	public void setConvention(ObservableList<WeekConvention> convention) {
		this.convention = convention;
	}

	public ObservableList<WeekMemorial> getMemorial() {
		return memorial;
	}

	public void setMemorial(ObservableList<WeekMemorial> memorial) {
		this.memorial = memorial;
	}

	public HashMap<String, String> getGeneralInfo() {
		return generalInfo;
	}

	public void setGeneralInfo(HashMap<String, String> generalInfo) {
		this.generalInfo = generalInfo;
	}

	public ObservableList<WeekOverseer> getOverseer() {
		return overseer;
	}

	public void setOverseer(ObservableList<WeekOverseer> overseer) {
		this.overseer = overseer;
	}

	public ObservableList<Week> getMeeting() {
		return meeting;
	}

	public void setMeeting(ObservableList<Week> meeting) {
		this.meeting = meeting;
	}
}

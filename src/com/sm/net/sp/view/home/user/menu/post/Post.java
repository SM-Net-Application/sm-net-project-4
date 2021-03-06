package com.sm.net.sp.view.home.user.menu.post;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.StreamSupport;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.PDFDest;
import com.sm.net.sp.model.PDFReplace;
import com.sm.net.sp.model.PostNews;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.post.task.PostDeleteTask;
import com.sm.net.sp.view.home.user.menu.post.task.PostInfoTableUpdateTask;
import com.sm.net.sp.view.home.user.menu.post.task.PostInitDataLoadTask;
import com.smnet.core.task.TaskManager;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Post {

	@FXML
	private ImageView headerImageView;
	@FXML
	private Label headerLabel;

	@FXML
	private TabPane tabPane;
	@FXML
	private Tab listTab;

	@FXML
	private TableView<PostNews> tableView;
	@FXML
	private TableColumn<PostNews, ImageView> infoTableTableColumn;
	@FXML
	private TableColumn<PostNews, LocalDate> dateTableColumn;
	@FXML
	private TableColumn<PostNews, String> docTitleTableColumn;
//	@FXML
//	private TableColumn<PostNews, String> newsDestTableColumn;
	@FXML
	private TableColumn<PostNews, String> newsTitleTableColumn;
	@FXML
	private Button addButton;

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

	@FXML
	private Button infotableButton;
	@FXML
	private TextField filterTextField;
	@FXML
	private Button deleteButton;

	private Settings settings;
	private Language language;
	private Stage stageSupportPlannerView;
	private SupportPlannerView application;

	private ObservableList<PDFDest> pdfDestList;
	private ObservableList<PDFReplace> pdfReplaceList;
	private ObservableList<PostNews> postNewsList;

	@FXML
	private void initialize() {
		styleClasses();
		tableColumnsCells();
	}

	private void tableColumnsCells() {

		this.infoTableTableColumn.setCellValueFactory(cellData -> {

			if (cellData.getValue().getSpInf7().intValue() == 1) {
				ImageView iv = Meta.Resources.imageForButtonSmall(Meta.Resources.INFOTABLE);
				return new SimpleObjectProperty<ImageView>(iv);
			}

			return null;
		});

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
		this.headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.POST, 50, 50));

		this.headerLabel.getStyleClass().add("label_header_001");

		this.tabPane.getStyleClass().add("tab_pane_001");
		this.listTab.getStyleClass().add("tab_001");

		this.tableView.getStyleClass().add("table_view_001");
		this.infoTableTableColumn.getStyleClass().add("table_column_002");
		this.dateTableColumn.getStyleClass().add("table_column_002");

		this.addButton.getStyleClass().add("button_image_001");

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

		this.infotableButton.getStyleClass().add("button_image_001");
		this.filterTextField.getStyleClass().add("text_field_001");
		this.deleteButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {

		this.language = this.settings.getLanguage();

		listeners();
		viewUpdate();
		initData();
	}

	private void viewUpdate() {

		this.headerLabel.setText(this.language.getString("sp.menu.post"));

		this.listTab.setText(this.language.getString("post.tab.database"));

		this.infoTableTableColumn.setText("");
		this.infoTableTableColumn.setMinWidth(50);
		this.infoTableTableColumn.setMaxWidth(50);
		this.infoTableTableColumn.setResizable(false);

		this.dateTableColumn.setMinWidth(100);
		this.dateTableColumn.setMaxWidth(100);

		this.docNameTextField.setEditable(false);
		this.docTitleTextField.setEditable(false);
		this.destTextField.setEditable(false);
		this.titleTextField.setEditable(false);
		this.textTextArea.setEditable(false);

		this.destLabel.setText(this.language.getString("post.label.dest"));
		this.docNameLabel.setText(this.language.getString("post.label.docname"));
		this.docTitleLabel.setText(this.language.getString("post.label.doctitle"));
		this.titleLabel.setText(this.language.getString("post.label.title"));
		this.textLabel.setText(this.language.getString("post.label.text"));

		this.dateTableColumn.setText(this.language.getString("post.tablecolumn.date"));
		this.docTitleTableColumn.setText(this.language.getString("post.tablecolumn.doctitle"));
		this.infoTableTableColumn.setText("");
		this.newsTitleTableColumn.setText(this.language.getString("post.tablecolumn.title"));

		Tooltip addTooltip = new Tooltip(this.language.getString("post.tooltip.add"));
		addTooltip.getStyleClass().add("tooltip_001");
		this.addButton.setTooltip(addTooltip);
		this.addButton.setText(null);
		this.addButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.PDF_ADD));

		Tooltip infotableTooltip = new Tooltip(this.language.getString("post.tooltip.infotable"));
		infotableTooltip.getStyleClass().add("tooltip_001");
		this.infotableButton.setTooltip(infotableTooltip);
		this.infotableButton.setText(null);
		this.infotableButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.INFOTABLE));

		Tooltip deleteTooltip = new Tooltip(this.language.getString("post.tooltip.delete"));
		deleteTooltip.getStyleClass().add("tooltip_001");
		this.deleteButton.setTooltip(deleteTooltip);
		this.deleteButton.setText(null);
		this.deleteButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DELETE));
	}

	public void initData() {

		this.pdfDestList = FXCollections.observableArrayList();
		this.pdfReplaceList = FXCollections.observableArrayList();
		this.postNewsList = FXCollections.observableArrayList();

		this.tableView.setItems(this.postNewsList);

		loadPosts();

	}

	public void loadPosts() {

		String waitMessage = this.language.getString("post.wait.init");

		TaskManager.run(this.application.getAlertBuilder2(), this.stageSupportPlannerView, waitMessage,
				new PostInitDataLoadTask(this.application.getAlertBuilder2(), this.settings,
						this.stageSupportPlannerView, this));
	}

	public void updatePostNewsList(ObservableList<PostNews> postNewsList) {

		this.postNewsList.clear();
		this.postNewsList.addAll(postNewsList);
		Platform.runLater(() -> this.tableView.refresh());
	}

	private void listeners() {
		listenerUserAddButton();
//		listenerUserDeleteButton();

		this.tableView.getSelectionModel().selectedIndexProperty()
				.addListener((o, oldV, newV) -> selectPost(newV.intValue()));

		this.filterTextField.textProperty().addListener((observable, oldValue, newValue) -> updateFilter(newValue));

		this.infotableButton.setOnAction(event -> checkInfoTable());
		this.deleteButton.setOnAction(event -> deletePost());
	}

	private void deletePost() {

		if (this.application.getUser().isSpUserSU())
			deletePostIsAllowed();
		else {
			if (this.application.getUser().isSpInf23())
				deletePostIsAllowed();
			else {
				String content = this.language.getString("post.error.authpdfimport");
				this.application.getAlertBuilder2().error(this.stageSupportPlannerView, content);
			}
		}
	}

	private void deletePostIsAllowed() {

		if (this.tableView.getSelectionModel().getSelectedIndex() > -1) {

			PostNews postNews = this.tableView.getSelectionModel().getSelectedItem();
			if (postNews != null) {

				String content = this.language.getString("post.delete.confirm");

				if (this.application.getAlertBuilder2().confirm(this.stageSupportPlannerView, content,
						postNews.getSpInf3())) {

					String waitMessage = this.language.getString("post.delete.wait");

					TaskManager.run(this.application.getAlertBuilder2(), this.stageSupportPlannerView, waitMessage,
							new PostDeleteTask(this.application.getAlertBuilder2(), this.application.getSettings(),
									this.stageSupportPlannerView, postNews, this));
				}
			}

		} else {
			String content = this.language.getString("post.error.nopostselected");
			this.application.getAlertBuilder2().error(this.stageSupportPlannerView, content);
		}
	}

	public void updateDeletedPost(PostNews postNews) {

		this.postNewsList.remove(postNews);
		this.tableView.getItems().remove(postNews);

		Platform.runLater(() -> {
			this.tableView.refresh();
			this.tableView.getSelectionModel().clearSelection();
		});

		this.resetPost();
	}

	private void checkInfoTable() {

		if (this.tableView.getSelectionModel().getSelectedIndex() > -1) {

			PostNews postNews = this.tableView.getSelectionModel().getSelectedItem();
			if (postNews != null) {

				String spInf7 = "1";
				String content = this.language.getString("post.infotable.confirmadd");
				if (postNews.getSpInf7().intValue() == 1) {
					spInf7 = "0";
					content = this.language.getString("post.infotable.confirmremove");
				}

				if (this.application.getAlertBuilder2().confirm(this.stageSupportPlannerView, content,
						postNews.getSpInf3())) {

					String waitMessage = this.language.getString("post.infotable.save");

					TaskManager.run(this.application.getAlertBuilder2(), this.stageSupportPlannerView, waitMessage,
							new PostInfoTableUpdateTask(this.application.getAlertBuilder2(),
									this.application.getSettings(), this.stageSupportPlannerView, postNews, spInf7,
									this));
				}
			}

		} else {
			String content = this.language.getString("post.error.nopostselected");
			this.application.getAlertBuilder2().error(this.stageSupportPlannerView, content);
		}
	}

	public void updatePostInfoTableStatus(PostNews postNews, String spInf7) {

		postNews.setSpInf7(Integer.valueOf(spInf7));
		Platform.runLater(() -> this.tableView.refresh());
	}

	private void updateFilter(String newValue) {

		if (newValue.isEmpty())
			this.tableView.setItems(this.postNewsList);
		else {
			ObservableList<PostNews> filteredList = buildList(newValue);
			this.tableView.setItems(filteredList);
		}

		this.tableView.refresh();
	}

	private ObservableList<PostNews> buildList(String filter) {

		ObservableList<PostNews> list = FXCollections.observableArrayList();

		StreamSupport.stream(this.postNewsList.spliterator(), false).filter(news -> matchFilter(news, filter))
				.forEach(project -> list.add(project));

		return list;
	}

	private boolean matchFilter(PostNews news, String match) {

		String filter = match.toLowerCase();

		return news.getSpInf2().toLowerCase().contains(filter) || news.getSpInf3().toLowerCase().contains(filter)
				|| news.getSpInf4().toLowerCase().contains(filter) || news.getSpInf5().toLowerCase().contains(filter)
				|| news.getSpInf6().toLowerCase().contains(filter);
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

//	private void listenerUserDeleteButton() {
//
//		this.deleteButton.setOnAction(event -> {
//
//			if (this.placesTableView.getSelectionModel().getSelectedIndex() > -1) {
//
//				Place item = this.placesTableView.getSelectionModel().getSelectedItem();
//
//				if (this.application.getAlertBuilder2().confirm(this.stageSupportPlannerView,
//						this.language.getString("places.delete.confirm"), item.getDescr().get())) {
//
//					String waitMessage = this.language.getString("places.wait.delete");
//
////					TaskManager.run(this.application.getAlertBuilder2(), this.stageSupportPlannerView, waitMessage,
////							new PlaceDeleteTask(this.application.getAlertBuilder2(), this.settings,
////									this.stageSupportPlannerView, item, this));
//				}
//			}
//
//		});
//	}

	private void listenerUserAddButton() {

		addButton.setOnAction(event -> newPost());
	}

	private void newPost() {

		if (this.application.getUser().isSpUserSU())
			showNewPostTab();
		else {
			if (this.application.getUser().isSpInf23())
				showNewPostTab();
			else {
				String content = this.language.getString("post.error.authpdfimport");
				this.application.getAlertBuilder2().error(this.stageSupportPlannerView, content);
			}
		}
	}

	private void showNewPostTab() {

		String newPost = language.getString("post.tab.new");

		if (!isAlreadyOpen(this.tabPane, newPost)) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.POST_IMPORT_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				PostImport ctrl = (PostImport) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setApplication(this.application);
				ctrl.setPdfDestList(this.pdfDestList);
				ctrl.setPdfReplaceList(this.pdfReplaceList);
				ctrl.setPost(this);

				Tab newTab = new Tab(newPost, layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tab_001");
				newTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PDF_ADD));

				ctrl.setParentTabPane(this.tabPane);
				ctrl.setThisTab(newTab);

				this.tabPane.getTabs().add(newTab);
				this.tabPane.getSelectionModel().select(newTab);

				ctrl.objectInitialize();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isAlreadyOpen(TabPane tabPane, String label) {

		for (Tab tab : tabPane.getTabs())
			if (tab.getText().equals(label)) {
				tabPane.getSelectionModel().select(tab);
				return true;
			}

		return false;
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
		return addButton;
	}

	public void setAddButton(Button addButton) {
		this.addButton = addButton;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public ObservableList<PDFDest> getPdfDestList() {
		return pdfDestList;
	}

	public void setPdfDestList(ObservableList<PDFDest> pdfDestList) {
		this.pdfDestList = pdfDestList;
	}

	public ObservableList<PDFReplace> getPdfReplaceList() {
		return pdfReplaceList;
	}

	public void setPdfReplaceList(ObservableList<PDFReplace> pdfReplaceList) {
		this.pdfReplaceList = pdfReplaceList;
	}

	public ObservableList<PostNews> getPostNewsList() {
		return postNewsList;
	}

	public void setPostNewsList(ObservableList<PostNews> postNewsList) {
		this.postNewsList = postNewsList;
	}
}

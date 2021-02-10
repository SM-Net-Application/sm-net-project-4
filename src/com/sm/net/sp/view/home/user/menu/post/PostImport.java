package com.sm.net.sp.view.home.user.menu.post;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.EnumPDFReplaceType;
import com.sm.net.sp.model.PDFDest;
import com.sm.net.sp.model.PDFReplace;
import com.sm.net.sp.model.PostImportDoc;
import com.sm.net.sp.model.PostImportNews;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.post.task.PostImportTask;
import com.smnet.core.task.TaskManager;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PostImport {

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab documentTab;
	@FXML
	private Tab newsTab;

	@FXML
	private Label fileLabel;
	@FXML
	private TextField fileTextField;
	@FXML
	private Button fileButton;

	@FXML
	private Label titleLabel;
	@FXML
	private TextField titleTextField;

	@FXML
	private Label dateFileLabel;
	@FXML
	private DatePicker dateFileDatePicker;

	@FXML
	private Label textLabel;
	@FXML
	private TextArea textArea;
	@FXML
	private Button textButton;

	@FXML
	private Label destLabel;
	@FXML
	private Label newsLabel;
	@FXML
	private ListView<String> destListView;
	@FXML
	private ListView<PostImportNews> newsListView;
	@FXML
	private TextField newsTextField;
	@FXML
	private TextArea newsTextArea;
	@FXML
	private Button newsDeleteButton;
	@FXML
	private Button newsImportButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private SupportPlannerView application;

	private TabPane parentTabPane;
	private Tab thisTab;

	private File lastFile;
	private File lastDirectory;

	private ObservableList<PDFDest> pdfDestList;
	private ObservableList<PDFReplace> pdfReplaceList;
	private PostImportDoc postDoc;

	private ObservableList<String> destinatari;
	private ObservableList<PostImportNews> comunicazioni;

	private String bufferNewsTitle;
	private String bufferNewsText;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		this.tabPane.getStyleClass().add("tab_pane_003");
		this.documentTab.getStyleClass().add("tab_001");
		this.newsTab.getStyleClass().add("tab_001");

		this.fileLabel.getStyleClass().add("label_set_001");
		this.fileTextField.getStyleClass().add("text_field_001");
		this.fileButton.getStyleClass().add("button_image_001");

		this.dateFileLabel.getStyleClass().add("label_set_001");
		this.dateFileDatePicker.getStyleClass().add("combo_box_001");

		this.titleLabel.getStyleClass().add("label_set_001");
		this.titleTextField.getStyleClass().add("text_field_001");

		this.textLabel.getStyleClass().add("label_set_001");
		this.textArea.getStyleClass().add("text_area_001");
		this.textButton.getStyleClass().add("button_image_001");

		this.destLabel.getStyleClass().add("label_001");
		this.newsLabel.getStyleClass().add("label_001");
		this.destListView.getStyleClass().add("list_view_001");
		this.newsListView.getStyleClass().add("list_view_001");
		this.newsTextField.getStyleClass().add("text_field_001");
		this.newsTextArea.getStyleClass().add("text_area_001");

		this.newsDeleteButton.getStyleClass().add("button_image_001");
		this.newsImportButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {

		initValue();
		viewUpdate();
		listeners();
	}

	private void initValue() {

		this.destinatari = FXCollections.observableArrayList();
		this.comunicazioni = FXCollections.observableArrayList();

		this.destListView.setItems(destinatari);
		this.newsListView.setItems(comunicazioni);
	}

	private void listeners() {

		this.fileButton.setOnAction(event -> selectFile());
		this.textButton.setOnAction(event -> elaborateText());
		this.newsDeleteButton.setOnAction(event -> removeSelectedNews());
		this.newsImportButton.setOnAction(event -> saveNews());

		this.destListView.getSelectionModel().selectedIndexProperty()
				.addListener((o, oldV, newV) -> checkNews(newV.intValue()));
		this.newsListView.getSelectionModel().selectedIndexProperty()
				.addListener((o, oldV, newV) -> viewNews(newV.intValue()));

		this.newsTextField.focusedProperty().addListener((o, oldV, newV) -> editNewsTitle(newV.booleanValue()));
		this.newsTextArea.focusedProperty().addListener((o, oldV, newV) -> editNewsText(newV.booleanValue()));
	}

	private void saveNews() {

		if (this.postDoc != null) {

			if (this.postDoc.getDocNewst().size() > 0) {

				String content = this.language.getString("post.import.newsimport.confirm");
				if (this.application.getAlertBuilder2().confirm(this.ownerStage, content)) {

					String waitMessage = this.application.getSettings().getLanguage().getString("post.import.savewait");

					TaskManager.run(this.getApplication().getAlertBuilder2(), this.ownerStage, waitMessage,
							new PostImportTask(this, this.application.getAlertBuilder2(),
									this.application.getSettings(), this.ownerStage, this.postDoc));

				}

			} else {
				String content = this.language.getString("post.import.error.nonews");
				this.application.getAlertBuilder2().error(this.ownerStage, content);
			}

		} else {
			String content = this.language.getString("post.import.error.docempty");
			this.application.getAlertBuilder2().error(this.ownerStage, content);
		}
	}

	public void closeTab() {
		this.parentTabPane.getTabs().remove(this.thisTab);
	}

	private void removeSelectedNews() {

		if (this.newsListView.getSelectionModel().getSelectedIndex() > -1) {

			PostImportNews postNews = this.newsListView.getSelectionModel().getSelectedItem();

			String header = this.language.getString("post.import.newsremove.confirm");
			String content = postNews.getTitle();
			if (this.application.getAlertBuilder2().confirm(this.ownerStage, header, content)) {

				this.postDoc.getDocNewst().remove(postNews);
				this.comunicazioni.remove(postNews);

				this.newsTextField.setText("");
				this.newsTextArea.setText("");

				Platform.runLater(() -> this.newsListView.refresh());
				this.newsListView.getSelectionModel().clearSelection();
			}

		} else {
			String content = this.language.getString("post.import.error.newsremove");
			this.application.getAlertBuilder2().error(this.ownerStage, content);
		}
	}

	private void editNewsTitle(boolean focus) {

		if (focus) {
			this.bufferNewsTitle = this.newsTextField.getText();
		} else {

			String newTitle = this.newsTextField.getText();
			if (!newTitle.equals(this.bufferNewsTitle)) {

				PostImportNews item = this.newsListView.getSelectionModel().getSelectedItem();
				if (item != null) {

					item.setTitle(newTitle);
					Platform.runLater(() -> this.newsListView.refresh());
				}
			}
		}
	}

	private void editNewsText(boolean focus) {

		if (focus) {
			this.bufferNewsText = this.newsTextArea.getText();
		} else {

			String newText = this.newsTextArea.getText();
			if (!newText.equals(this.bufferNewsText)) {

				PostImportNews item = this.newsListView.getSelectionModel().getSelectedItem();
				if (item != null) {

					item.setText(newText);
					Platform.runLater(() -> this.newsListView.refresh());
				}
			}
		}
	}

	private void viewNews(int index) {

		if (index > -1) {
			PostImportNews news = this.newsListView.getSelectionModel().getSelectedItem();
			this.newsTextField.setText(news.getTitle());
			this.newsTextArea.setText(news.getText());
		}
	}

	private void checkNews(int index) {

		if (index > -1) {

			String destinatario = this.destListView.getSelectionModel().getSelectedItem();

			if (this.postDoc != null) {

				this.comunicazioni.clear();
				this.newsTextField.setText("");
				this.newsTextArea.setText("");

				for (PostImportNews postNews : postDoc.getDocNewst())
					if (postNews.getDest().equals(destinatario))
						this.comunicazioni.add(postNews);

				Platform.runLater(() -> this.newsListView.refresh());
			}
		}
	}

	private void elaborateText() {

		if (checkFields()) {

			if (postDoc != null) {
				String content = this.language.getStringWithNewLine("post.import.elaborate.confirm");
				if (this.application.getAlertBuilder2().confirm(this.ownerStage, content))
					elaborate();
			} else
				elaborate();
		}
	}

	private void elaborate() {

		String title = this.titleTextField.getText();
		LocalDate date = this.dateFileDatePicker.getValue();
		String text = this.textArea.getText();

		this.postDoc = PostImportDoc.newIstance(this.application, this.pdfDestList, this.lastFile.getName(), date, title,
				text);

		if (this.postDoc != null) {
			buildList();
			this.tabPane.getSelectionModel().select(this.newsTab);
		}
	}

	private void buildList() {

		for (PostImportNews news : this.postDoc.getDocNewst()) {

			String dest = news.getDest();
			if (!destinatari.contains(dest))
				destinatari.add(dest);
		}

		destinatari.sort((o1, o2) -> o1.compareTo(o2));
		Platform.runLater(() -> destListView.refresh());
	}

	private boolean checkFields() {

		if (this.titleTextField.getText().isEmpty()) {
			this.application.getAlertBuilder2().error(this.ownerStage, language.getString("post.import.error.title"));
			return false;
		}

		if (this.dateFileDatePicker.getValue() == null) {
			this.application.getAlertBuilder2().error(this.ownerStage, language.getString("post.import.error.date"));
			return false;
		}
		if (this.textArea.getText().isEmpty()) {
			this.application.getAlertBuilder2().error(this.ownerStage, language.getString("post.import.error.text"));
			return false;
		}

		return true;
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.tabPane.setTabMinHeight(75);
		this.tabPane.setTabMaxHeight(75);

		Tooltip documentTabTooltip = new Tooltip(this.language.getString("post.import.tab.document"));
		documentTabTooltip.getStyleClass().add("tooltip_001");
		this.documentTab.setTooltip(documentTabTooltip);
		this.documentTab.setText("");
		this.documentTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PDF));

		this.fileLabel.setText(this.language.getString("post.import.label.file"));

		Tooltip fileButtonTooltip = new Tooltip(this.language.getString("post.import.button.file"));
		fileButtonTooltip.getStyleClass().add("tooltip_001");
		this.fileButton.setTooltip(fileButtonTooltip);
		this.fileButton.setText("");
		this.fileButton.setGraphic(Meta.Resources.imageForButtonSmall((Meta.Resources.FOLDER)));

		this.dateFileLabel.setText(this.language.getString("post.import.label.date"));
		this.textLabel.setText(this.language.getString("post.import.label.text"));

		this.titleLabel.setText(this.language.getString("post.import.label.title"));

		Tooltip textButtonTooltip = new Tooltip(this.language.getString("post.import.button.text"));
		textButtonTooltip.getStyleClass().add("tooltip_001");
		this.textButton.setTooltip(textButtonTooltip);
		this.textButton.setText("");
		this.textButton.setGraphic(Meta.Resources.imageForButtonSmall((Meta.Resources.CONFIG)));

		Tooltip newsTabTooltip = new Tooltip(this.language.getString("post.import.tab.news"));
		newsTabTooltip.getStyleClass().add("tooltip_001");
		this.newsTab.setTooltip(newsTabTooltip);
		this.newsTab.setText("");
		this.newsTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.NEWS));

		this.destLabel.setText(this.language.getString("post.import.label.dest"));
		this.newsLabel.setText(this.language.getString("post.import.label.news"));

		Tooltip newsImportButtonTooltip = new Tooltip(this.language.getString("post.import.button.import"));
		newsImportButtonTooltip.getStyleClass().add("tooltip_001");
		this.newsImportButton.setTooltip(newsImportButtonTooltip);
		this.newsImportButton.setText("");
		this.newsImportButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));

		Tooltip newsDeleteButtonTooltip = new Tooltip(this.language.getString("post.import.button.delete"));
		newsDeleteButtonTooltip.getStyleClass().add("tooltip_001");
		this.newsDeleteButton.setTooltip(newsDeleteButtonTooltip);
		this.newsDeleteButton.setText("");
		this.newsDeleteButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DELETE));
	}

	private void selectFile() {

		if (this.fileTextField.getText().isEmpty())
			selectFile(false);
		else {
			String content = this.language.getStringWithNewLine("post.import.new.confirm");
			if (this.application.getAlertBuilder2().confirm(this.ownerStage, content))
				selectFile(true);
		}
	}

	@SuppressWarnings("deprecation")
	private void selectFile(boolean reset) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");

		if (lastDirectory != null)
			fileChooser.setInitialDirectory(lastDirectory);
		else
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));

		File file = fileChooser.showOpenDialog(this.ownerStage);
		if (file != null) {

			if (reset)
				resetFields();

			this.lastFile = file;
			this.lastDirectory = file.getParentFile();
			this.fileTextField.setText(file.getAbsolutePath());
			this.dateFileDatePicker.setValue(checkLocalDateByFilename(file));

			try {

				this.textArea.clear();

				AutoDetectParser parser = new AutoDetectParser();
				BodyContentHandler handler = new BodyContentHandler(-1);

				Metadata tikaMetadata = new Metadata();
				InputStream input;
				input = TikaInputStream.get(file, tikaMetadata);
				parser.parse(input, handler, tikaMetadata, new ParseContext());

				String text = handler.toString();

				for (PDFReplace pdfReplace : pdfReplaceList) {

					switch (EnumPDFReplaceType.valueByID(pdfReplace.getSpInf1())) {
					case REGEX:
						text = text.replaceAll(pdfReplace.getSpInf2(), pdfReplace.getSpInf3());
						break;
					case TEXT:
						text = text.replace(pdfReplace.getSpInf2(), pdfReplace.getSpInf3());
						break;
					}
				}

				text = text.trim();

				this.textArea.setText(text);
				this.titleTextField.setText(checkTitle(text));
				// String checkedText = checkText(text);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (TikaException e) {
				e.printStackTrace();
			}
		}
	}

	private String checkTitle(String text) {

		String title = "";

		String[] strings = text.split("\n");
		if (strings.length > 0)
			title += strings[0].trim();

		if (strings.length > 1) {
			if (!title.isEmpty())
				title += " - ";
			title += strings[1].trim();
		}

		return title;
	}

	private LocalDate checkLocalDateByFilename(File file) {

		String name = file.getName();
		if (name.matches("^.+[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]\\.[Pp][Dd][Ff]$")) {
			String dateText = name.substring(name.length() - 12, name.length() - 4);
			LocalDate date = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("yyyyMMdd"));
			return date;
		}
		return null;
	}

	private void resetFields() {

		this.dateFileDatePicker.setValue(null);
		this.titleTextField.setText("");
		this.textArea.setText("");
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
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

	public File getLastFile() {
		return lastFile;
	}

	public void setLastFile(File lastFile) {
		this.lastFile = lastFile;
	}

	public TabPane getParentTabPane() {
		return parentTabPane;
	}

	public void setParentTabPane(TabPane parentTabPane) {
		this.parentTabPane = parentTabPane;
	}

	public Tab getThisTab() {
		return thisTab;
	}

	public void setThisTab(Tab thisTab) {
		this.thisTab = thisTab;
	}
}

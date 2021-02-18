package com.sm.net.sp.model;

import java.time.LocalDate;
import java.util.ArrayList;

import com.sm.net.sp.view.SupportPlannerView;

import javafx.collections.ObservableList;

public class PostImportDoc {

	private String docName;
	private String docTitle;
	private LocalDate docDate;
	private ArrayList<PostImportNews> docNews;

	public PostImportDoc(String docName, String docTitle, LocalDate docDate) {
		super();
		this.docName = docName;
		this.docTitle = docTitle;
		this.docDate = docDate;
		this.docNews = new ArrayList<>();
	}

	public static PostImportDoc newIstance(SupportPlannerView application, ObservableList<PDFDest> pdfDestList,
			String docName, LocalDate docDate, String docTitle, String text) {

		PostImportDoc postDoc = new PostImportDoc(docName, docTitle, docDate);

		elaborateText(application, pdfDestList, postDoc, text);

		return postDoc;
	}

	private static void elaborateText(SupportPlannerView application, ObservableList<PDFDest> pdfDestList,
			PostImportDoc postDoc, String text) {

		String regExTitolo = application.getSettings().getLanguage().getString("post.pdf.regextitle");

		ArrayList<String> initialText = new ArrayList<>();
		PDFDest currDest = null;
		String currFoundDest = "";
		String currTitolo = "";
		StringBuilder currNews = new StringBuilder();

		boolean destinatario = false;
		boolean titolo = false;
		boolean titoloComeTesto = false;

		String[] strings = text.split("\n");
		for (int i = 0; i < strings.length; i++) {

			String currText = strings[i].trim();
			if (currText.isEmpty() || initialText.contains(currText))
				continue;

			if (!destinatario) {
				currDest = checkDest(pdfDestList, currText);
				if (currDest != null) {
					currFoundDest = currDest.getSpInf1();
					destinatario = true;
				} else
					initialText.add(currText);

			} else {

				if (!titolo) {

					if (currText.matches(regExTitolo)) {

						titolo = true;
						currTitolo = checkTitle(currText);
						i--;
						titoloComeTesto = true;
					}

				} else {

					if (titoloComeTesto) {

						currNews.append(currText);
						currNews.append("\n");

						titoloComeTesto = false;

					} else {

						currDest = checkDest(pdfDestList, currText);
						if (currDest != null) {
							destinatario = false;
							titolo = false;
							i--;

							// Add news
							addNews(postDoc, currFoundDest, currTitolo, currNews);
							currFoundDest = currDest.getSpInf1();
							currTitolo = "";

							continue;
						}

						if (currText.matches(regExTitolo)) {
							titolo = false;
							i--;

							// Add news
							addNews(postDoc, currFoundDest, currTitolo, currNews);
							currTitolo = "";

							continue;
						}

						currNews.append(currText);
						currNews.append("\n");
					}
				}
			}
		}

		if (currNews.length() > 0)
			addNews(postDoc, currFoundDest, currTitolo, currNews);
	}

	private static String checkTitle(String currText) {

		String title = currText;

		int firstPoint = title.indexOf(".");
		if (firstPoint > -1)
			title = title.substring(firstPoint + 1).trim();

		int secondPoint = title.lastIndexOf(".");
		if (secondPoint > -1)
			title = title.substring(0, secondPoint).trim();
		else {
			int doublePoint = title.indexOf(":");
			if (doublePoint > -1)
				title = title.substring(0, doublePoint).trim();
		}

		return title;
	}

	private static void addNews(PostImportDoc postDoc, String currFoundDest, String currTitolo,
			StringBuilder currNews) {

		String currNewsText = currNews.toString();
		if (!currFoundDest.isEmpty() && !currTitolo.isEmpty() && !currNewsText.isEmpty()) {
			PostImportNews postNews = new PostImportNews(currFoundDest, currTitolo, currNewsText);
			postDoc.getDocNewst().add(postNews);
			currNews.setLength(0);
		}
	}

	private static PDFDest checkDest(ObservableList<PDFDest> pdfDestList, String currText) {

		for (PDFDest pd : pdfDestList) {
			String spInf2 = pd.getSpInf2();
			if (currText.matches(spInf2))
				return pd;
		}

		return null;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public LocalDate getDocDate() {
		return docDate;
	}

	public void setDocDate(LocalDate docDate) {
		this.docDate = docDate;
	}

	public ArrayList<PostImportNews> getDocNewst() {
		return docNews;
	}

	public void setDocNews(ArrayList<PostImportNews> docNews) {
		this.docNews = docNews;
	}
}

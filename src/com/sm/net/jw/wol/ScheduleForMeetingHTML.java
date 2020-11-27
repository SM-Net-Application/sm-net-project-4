package com.sm.net.jw.wol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import com.sm.net.easy.html.EasyHtml;
import com.sm.net.project.Language;

public class ScheduleForMeetingHTML {
	private Language language;
	private LocalDate date;
	private ArrayList<String> relevantHTMLRows;
	private ArrayList<String> relevantRows;

	public ScheduleForMeetingHTML(Language language, LocalDate date) {
		this.language = language;
		this.date = date;
	}

	public void download() {
		String wolLink = WatchtowerOnlineLibrary.createLink(this.language, this.date);
		if (!wolLink.isEmpty()) {
			String sourceCode = EasyHtml.getSourceCode(wolLink);
			this.relevantHTMLRows = WatchtowerOnlineLibrary.relevantHTMLRows(sourceCode);
			this.defineRelevantRows();
		}

	}

	private void defineRelevantRows() {
		this.relevantRows = new ArrayList<>();
		Iterator var2 = this.relevantHTMLRows.iterator();

		while (var2.hasNext()) {
			String row = (String) var2.next();
			this.relevantRows.add(EasyHtml.removeTag(EasyHtml.replaceNoBreakSpace(row)).trim());
		}

	}

	public void printRelevantHTMLRows() {
		if (this.relevantHTMLRows != null) {
			Iterator var2 = this.relevantHTMLRows.iterator();

			while (var2.hasNext()) {
				String row = (String) var2.next();
				System.out.println(row);
			}
		} else {
			System.out.println("Relevant HTML rows: null");
		}

	}

	public void printRelevantRows() {
		if (this.relevantRows != null) {
			Iterator var2 = this.relevantRows.iterator();

			while (var2.hasNext()) {
				String row = (String) var2.next();
				System.out.println(row);
			}
		} else {
			System.out.println("Relevant rows: null");
		}

	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ArrayList<String> getRelevantHTMLRows() {
		return this.relevantHTMLRows;
	}

	public void setRelevantHTMLRows(ArrayList<String> relevantHTMLRows) {
		this.relevantHTMLRows = relevantHTMLRows;
	}

	public ArrayList<String> getRelevantRows() {
		return this.relevantRows;
	}

	public void setRelevantRows(ArrayList<String> relevantRows) {
		this.relevantRows = relevantRows;
	}
}

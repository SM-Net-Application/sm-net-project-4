package com.sm.net.sp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class TerritoryModul {

	private int index;
	private int nameIndex;
	private int dateIndex;

	private int count;

	private HashMap<String, String> infos;

	public TerritoryModul(int index, TerritoryObj territoryObj) {
		super();

		this.index = index;

		if (index <= 5)
			this.nameIndex = index;
		else
			this.nameIndex = index + 130;

		switch (index) {
		case 6:
			this.dateIndex = 271;
			break;
		case 7:
			this.dateIndex = 273;
			break;
		case 8:
			this.dateIndex = 275;
			break;
		case 9:
			this.dateIndex = 277;
			break;
		case 10:
			this.dateIndex = 279;
			break;
		default:
			this.dateIndex = index * 2 - 1;
			break;
		}

		this.count = 0;

		this.infos = new HashMap<>();
		this.infos.put(String.format("Terr_%d", index), territoryObj.getSpInf7());

	}

	public void processEntity(TerritoryRegistryEntity entity, DateTimeFormatter dtf) {

		Member publisher = entity.getPublisher();

		if (this.count < 27) {

			this.count++;

			this.infos.put(String.format("Name_%03d", this.nameIndex), publisher.getNameStyle3());
			// this.nameIndex++;
			this.nameIndex += 5;

			LocalDate startDate = entity.getStartDate();
			this.infos.put(String.format("Date_%03d", this.dateIndex), dtf.format(startDate));
			this.dateIndex++;

			LocalDate endDate = entity.getEndDate();

			if (endDate != null)
				this.infos.put(String.format("Date_%03d", this.dateIndex), dtf.format(endDate));
			else
				this.infos.put(String.format("Date_%03d", this.dateIndex), "");

			this.dateIndex += 9;
		}

	}

	public HashMap<String, String> getInfos() {
		return infos;
	}

	public void setInfos(HashMap<String, String> infos) {
		this.infos = infos;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	public int getDateIndex() {
		return dateIndex;
	}

	public void setDateIndex(int dateIndex) {
		this.dateIndex = dateIndex;
	}
}

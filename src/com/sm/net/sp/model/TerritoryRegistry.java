package com.sm.net.sp.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TerritoryRegistry {

	private ObservableList<TerritoryRegistryEntity> registry;

	public TerritoryRegistry() {
		super();
		this.registry = FXCollections.observableArrayList();
	}

	public ObservableList<TerritoryRegistryEntity> getRegistry() {
		return registry;
	}

	public void setRegistry(ObservableList<TerritoryRegistryEntity> registry) {
		this.registry = registry;
	}

	public void update(ObservableList<TerritoryRegistryEntity> list) {
		this.registry.clear();
		this.registry.addAll(list);
	}

	public void updateTerritoryAssignedMember(ObservableList<TerritoryObj> territoryList,
			ObservableList<Member> memberList) {

		for (TerritoryObj territoryObj : territoryList) {

			int territoryID = territoryObj.getSpTerritoryID();
			ObservableList<TerritoryRegistryEntity> territoryEntityList = this.findTerritoryEntityList(territoryID);

			if (territoryEntityList.size() > 0) {

				TerritoryRegistryEntity lastEntity = territoryEntityList.get(0);
				if (lastEntity.getSpInf4().isEmpty()) {

					Integer memberID = lastEntity.getSpInf2();

					Member member = this.findMemberByID(memberList, memberID);
					if (member != null) {
						territoryObj.updateAssignedMember(member);
						territoryObj.updateAssignedDate(lastEntity.getStartDate());
					}
				}
			}
		}
	}

	private Member findMemberByID(ObservableList<Member> memberList, Integer memberID) {

		for (Member member : memberList) {
			if (member.getSpMemberID() == memberID) {
				return member;
			}
		}

		return null;
	}

	public ObservableList<TerritoryRegistryEntity> findTerritoryEntityList(int territoryID) {

		ObservableList<TerritoryRegistryEntity> entityList = FXCollections.observableArrayList();

		for (TerritoryRegistryEntity territoryRegistryEntity : this.registry) {
			if (territoryRegistryEntity.getSpInf1() == territoryID) {
				entityList.add(territoryRegistryEntity);
			}
		}

		entityList.sort((o1, o2) -> o2.getStartDate().compareTo(o1.getStartDate()));

		return entityList;
	}

	public ObservableList<TerritoryObj> findActualTerritoriesByPublisher(ObservableList<TerritoryObj> territoryList,
			Member publisher) {

		ObservableList<TerritoryObj> assignedList = FXCollections.observableArrayList();

		for (TerritoryRegistryEntity territoryRegistryEntity : this.registry) {
			if (territoryRegistryEntity.getSpInf2() == publisher.getSpMemberID()) {
				if (territoryRegistryEntity.getSpInf4().isEmpty()) {
					TerritoryObj territoryObj = findTerritoryByID(territoryList, territoryRegistryEntity.getSpInf1());
					if (territoryObj != null)
						assignedList.add(territoryObj);
				}
			}
		}

		assignedList.sort((o1, o2) -> o1.getSpInf7().compareTo(o2.getSpInf7()));

		return assignedList;
	}

	private TerritoryObj findTerritoryByID(ObservableList<TerritoryObj> territoryList, Integer territoryID) {

		for (TerritoryObj territoryObj : territoryList) {
			if (territoryObj.getSpTerritoryID() == territoryID) {
				return territoryObj;
			}
		}

		return null;
	}

	public TerritoryRegistryEntity findActualTerritoriesEntity(ObservableList<TerritoryObj> territoryList,
			Member publisher, TerritoryObj territoryObj) {

		for (TerritoryRegistryEntity territoryRegistryEntity : this.registry) {
			if (territoryRegistryEntity.getSpInf2() == publisher.getSpMemberID()) {
				if (territoryRegistryEntity.getSpInf4().isEmpty()
						&& territoryRegistryEntity.getSpInf1() == territoryObj.getSpTerritoryID()) {
					return territoryRegistryEntity;
				}
			}
		}

		return null;
	}

	public ArrayList<ArrayList<TerritoryModul>> build(ObservableList<TerritoryObj> territoryList,
			ObservableList<Member> membersList, DateTimeFormatter dtf) {

		ArrayList<ArrayList<TerritoryModul>> list = new ArrayList<>();

		int territoryCount = 0;
		ArrayList<TerritoryModul> currList = null;
		for (TerritoryObj territoryObj : territoryList) {

			if (territoryCount == 0)
				currList = new ArrayList<>();
			else if (territoryCount == 10) {
				list.add(new ArrayList<>(currList));
				currList = new ArrayList<>();
				territoryCount = 0;
			}

			territoryCount++;

			ObservableList<TerritoryRegistryEntity> entityList = this
					.findTerritoryEntityList(territoryObj.getSpTerritoryID());
			entityList.forEach(entity -> entity.updatePublisher(membersList));

			TerritoryModul territoryModul = new TerritoryModul(territoryCount, territoryObj);

			for (TerritoryRegistryEntity entity : entityList) {
				territoryModul.processEntity(entity, dtf);
			}

			currList.add(territoryModul);
		}

		if (currList != null)
			list.add(currList);

		return list;
	}
}

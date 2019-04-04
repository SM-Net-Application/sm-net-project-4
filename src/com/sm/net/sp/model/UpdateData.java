package com.sm.net.sp.model;

import javafx.collections.ObservableList;

public interface UpdateData {

	public abstract void updateUsers(ObservableList<User> list);

	public abstract void updateUsers();

	public abstract void updateMembers(ObservableList<Member> list);

	public abstract void updateMembers();

	public abstract void updateFamilies(ObservableList<Family> list);

	public abstract void updateFamilies();

	public abstract void updateSerGroups(ObservableList<SerGroup> list);

	public abstract void updateSerGroups();

	public abstract void updateWeeks(ObservableList<Week> list);

	public abstract void updateWeeks();

	public abstract void checkInfo(boolean exists);
}

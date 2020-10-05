package com.sm.net.sp.view.history;

import com.sm.net.sp.model.ChristiansPart;
import com.sm.net.sp.model.Privileges;

public interface UpgradeableComboBoxSelection {

	public abstract void updateSelectedComboBox(Privileges privilege, int memberID);

	public abstract void updateSelectedChristianPart(ChristiansPart christiansPart, int memberID);
}

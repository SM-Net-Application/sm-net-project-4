package com.sm.net.sp.view;

import org.json.JSONObject;

public interface SupportPlannerCallback {

	public abstract void setUserLogin(JSONObject jsonObject);

	public abstract void viewSupportPlannerHomeUser();

	public abstract void downloadNewVersion();
}

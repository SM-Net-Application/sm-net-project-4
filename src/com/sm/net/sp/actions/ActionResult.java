package com.sm.net.sp.actions;

public enum ActionResult {

	SUCCESS("ACTIONRESULT001"), KEY_NOT_VALID("ACTIONRESULT002");

	private String name;

	private ActionResult(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

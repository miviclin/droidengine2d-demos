package com.miviclin.droidengine2ddemos.gamestate.stacking;

public enum State {
	MAIN_MENU(0),
	LEVEL_SELECT_MENU(1),
	LEVEL_1(2);

	private int id;

	private State(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}

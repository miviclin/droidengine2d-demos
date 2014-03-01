package com.miviclin.droidengine2ddemos;

import android.content.Context;

public enum DemosGroup {
	RECTANGLES(R.string.group_rectangles),
	MATERIALS(R.string.group_materials),
	TEXTURE_REGIONS(R.string.group_texture_regions),
	TEXT(R.string.group_text),
	ANIMATION(R.string.group_animation),
	AUDIO(R.string.group_audio),
	GAME_STATES(R.string.group_gamestate_management),
	INPUT(R.string.group_input);

	private int resId;

	private DemosGroup(int resId) {
		this.resId = resId;
	}

	public int getResId() {
		return resId;
	}

	public String getString(Context context) {
		return context.getString(resId);
	}
}

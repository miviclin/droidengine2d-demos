package com.miviclin.droidengine2ddemos;

import android.content.Context;

public enum DemosGroup {
	RECTANGLES(R.string.group_rectangles),
	MATERIALS(R.string.group_materials),
	TEXT(R.string.group_text),
	ANIMATION(R.string.group_animation),
	AUDIO(R.string.group_audio),
	SCENES(R.string.group_scenes),
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

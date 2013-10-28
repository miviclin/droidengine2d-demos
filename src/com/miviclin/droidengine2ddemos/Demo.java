package com.miviclin.droidengine2ddemos;

import android.content.Context;

public enum Demo {
	RECTANGLES_BASIC(R.string.group_rectangles_basic),
	RECTANGLES_ROTATION_BASIC(R.string.group_rectangles_rotation_basic),
	RECTANGLES_ROTATION_ANCHOR_POINT(R.string.group_rectangles_rotation_anchor_point),
	TEXT_CUSTOM_FONTS(R.string.group_text_custom_fonts),
	MATERIAL_BLENDING_OPTIONS(R.string.group_materials_blending_options),
	MATERIAL_TEXTURE_HSV(R.string.group_materials_texture_hsv);

	private int resId;

	private Demo(int resId) {
		this.resId = resId;
	}

	public int getResId() {
		return resId;
	}

	public String getString(Context context) {
		return context.getString(resId);
	}
}

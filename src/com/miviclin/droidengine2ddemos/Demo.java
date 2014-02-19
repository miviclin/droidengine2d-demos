package com.miviclin.droidengine2ddemos;

import android.content.Context;

import com.miviclin.droidengine2d.EngineActivity;
import com.miviclin.droidengine2ddemos.gamestate.switching.GameStateSwitchingDemoActivity;
import com.miviclin.droidengine2ddemos.input.accelerometer.AccelerometerDemoActivity;
import com.miviclin.droidengine2ddemos.input.key.KeyInputDemoActivity;
import com.miviclin.droidengine2ddemos.input.touch.TouchInputDemoActivity;
import com.miviclin.droidengine2ddemos.material.blendingoptions.BlendingOptionsActivity;
import com.miviclin.droidengine2ddemos.material.color.ColorMaterialActivity;
import com.miviclin.droidengine2ddemos.material.texture.TextureMaterialActivity;
import com.miviclin.droidengine2ddemos.material.texturecolor.TextureColorMaterialActivity;
import com.miviclin.droidengine2ddemos.material.texturehsv.TextureHsvMaterialActivity;
import com.miviclin.droidengine2ddemos.material.transparenttexture.TransparentTextureMaterialActivity;
import com.miviclin.droidengine2ddemos.rectangle.basic.RectanglesActivity;
import com.miviclin.droidengine2ddemos.rectangle.rotation.anchorpoint.AnchorPointRotationActivity;
import com.miviclin.droidengine2ddemos.rectangle.rotation.basic.BasicRotationActivity;
import com.miviclin.droidengine2ddemos.text.customfonts.CustomFontsActivity;

public enum Demo {
	RECTANGLES_BASIC(R.string.group_rectangles_basic, RectanglesActivity.class),
	RECTANGLES_ROTATION_BASIC(R.string.group_rectangles_rotation_basic, BasicRotationActivity.class),
	RECTANGLES_ROTATION_ANCHOR_POINT(R.string.group_rectangles_rotation_anchor_point, AnchorPointRotationActivity.class),
	TEXT_CUSTOM_FONTS(R.string.group_text_custom_fonts, CustomFontsActivity.class),
	MATERIAL_BLENDING_OPTIONS(R.string.group_materials_blending_options, BlendingOptionsActivity.class),
	MATERIAL_COLOR(R.string.group_materials_color, ColorMaterialActivity.class),
	MATERIAL_TEXTURE(R.string.group_materials_texture, TextureMaterialActivity.class),
	MATERIAL_TEXTURE_COLOR(R.string.group_materials_texture_color, TextureColorMaterialActivity.class),
	MATERIAL_TEXTURE_HSV(R.string.group_materials_texture_hsv, TextureHsvMaterialActivity.class),
	MATERIAL_TRANSPARENT_TEXTURE(R.string.group_materials_transparent_texture, TransparentTextureMaterialActivity.class),
	INPUT_TOUCH(R.string.group_input_touch, TouchInputDemoActivity.class),
	INPUT_KEY(R.string.group_input_key, KeyInputDemoActivity.class),
	INPUT_ACCELEROMETER(R.string.group_input_accelerometer, AccelerometerDemoActivity.class),
	GAMESTATE_SWITCHING(R.string.group_gamestate_management_switching, GameStateSwitchingDemoActivity.class);

	private int resId;
	private Class<? extends EngineActivity> demoActivityClass;

	private Demo(int resId) {
		this.resId = resId;
	}

	private Demo(int resId, Class<? extends EngineActivity> demoActivityClass) {
		this.resId = resId;
		this.demoActivityClass = demoActivityClass;
	}

	public int getResId() {
		return resId;
	}

	public Class<? extends EngineActivity> getDemoActivityClass() {
		return demoActivityClass;
	}

	public String getString(Context context) {
		return context.getString(resId);
	}
}

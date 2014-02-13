package com.miviclin.droidengine2ddemos.material.texturehsv;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class TextureHsvMaterialGame extends Game {

	public TextureHsvMaterialGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize(float viewWidth, float viewHeight) {
		getScreenManager().registerScreen(0, new TextureHsvMaterialScreen(viewWidth, viewHeight, this), true);
		System.gc();
	}

}

package com.miviclin.droidengine2ddemos.material.texturecolor;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class TextureColorMaterialGame extends Game {

	public TextureColorMaterialGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize(float viewWidth, float viewHeight) {
		getScreenManager().registerScreen(0, new TextureColorMaterialScreen(viewWidth, viewHeight, this), true);
		System.gc();
	}

}

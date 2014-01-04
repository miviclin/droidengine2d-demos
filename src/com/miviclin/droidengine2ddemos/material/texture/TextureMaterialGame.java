package com.miviclin.droidengine2ddemos.material.texture;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class TextureMaterialGame extends Game {

	public TextureMaterialGame(GLView glView, Activity activity) {
		super("", glView, activity);
	}

	@Override
	public void initialize(float viewWidth, float viewHeight) {
		getScreenManager().registerScreen(0, new TextureMaterialScreen(viewWidth, viewHeight, this), true);
		System.gc();
	}

}

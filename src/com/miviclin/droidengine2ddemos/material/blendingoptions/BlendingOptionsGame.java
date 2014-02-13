package com.miviclin.droidengine2ddemos.material.blendingoptions;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class BlendingOptionsGame extends Game {

	public BlendingOptionsGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getScreenManager().registerScreen(0, new BlendingOptionsScreen(this), true);
		System.gc();
	}

}

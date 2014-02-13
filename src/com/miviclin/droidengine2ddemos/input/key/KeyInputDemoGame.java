package com.miviclin.droidengine2ddemos.input.key;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class KeyInputDemoGame extends Game {

	public KeyInputDemoGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getScreenManager().registerScreen(0, new KeyInputDemoScreen(this), true);
		System.gc();
	}

}

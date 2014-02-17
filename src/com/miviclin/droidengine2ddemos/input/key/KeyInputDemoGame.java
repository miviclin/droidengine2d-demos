package com.miviclin.droidengine2ddemos.input.key;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class KeyInputDemoGame extends AbstractGame {

	public KeyInputDemoGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new KeyInputDemoGameState(this), true);
	}

}

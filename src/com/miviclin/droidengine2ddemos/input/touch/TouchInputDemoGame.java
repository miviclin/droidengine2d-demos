package com.miviclin.droidengine2ddemos.input.touch;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class TouchInputDemoGame extends AbstractGame {

	public TouchInputDemoGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new TouchInputDemoGameState(this), true);
	}

}

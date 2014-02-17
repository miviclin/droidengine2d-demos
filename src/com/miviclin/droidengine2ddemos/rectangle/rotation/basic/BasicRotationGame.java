package com.miviclin.droidengine2ddemos.rectangle.rotation.basic;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class BasicRotationGame extends AbstractGame {

	public BasicRotationGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new BasicRotationGameState(this), true);
	}

}

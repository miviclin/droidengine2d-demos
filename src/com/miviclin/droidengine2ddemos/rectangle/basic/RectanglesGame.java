package com.miviclin.droidengine2ddemos.rectangle.basic;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class RectanglesGame extends AbstractGame {

	public RectanglesGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new RectanglesGameState(this), true);
	}

}

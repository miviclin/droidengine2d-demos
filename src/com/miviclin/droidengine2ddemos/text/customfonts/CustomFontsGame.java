package com.miviclin.droidengine2ddemos.text.customfonts;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class CustomFontsGame extends Game {

	public CustomFontsGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new CustomFontsGameState(this), true);
		System.gc();
	}

}

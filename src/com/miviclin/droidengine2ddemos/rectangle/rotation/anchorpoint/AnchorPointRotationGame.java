package com.miviclin.droidengine2ddemos.rectangle.rotation.anchorpoint;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class AnchorPointRotationGame extends Game {

	public AnchorPointRotationGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new AnchorPointRotationGameState(this), true);
		System.gc();
	}

}

package com.miviclin.droidengine2ddemos.rectangle.rotation.anchorpoint;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class AnchorPointRotationGame extends AbstractGame {

	public AnchorPointRotationGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new AnchorPointRotationGameState(this), true);
	}

}

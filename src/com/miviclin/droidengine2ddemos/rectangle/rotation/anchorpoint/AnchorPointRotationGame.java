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
		getScreenManager().registerScreen(0, new AnchorPointRotationScreen(this), true);
		System.gc();
	}

}

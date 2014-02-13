package com.miviclin.droidengine2ddemos.rectangle.rotation.anchorpoint;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class AnchorPointRotationGame extends Game {

	public AnchorPointRotationGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize(float viewWidth, float viewHeight) {
		getScreenManager().registerScreen(0, new AnchorPointRotationScreen(viewWidth, viewHeight, this), true);
		System.gc();
	}

}

package com.miviclin.droidengine2ddemos.input.touch;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class TouchInputDemoGame extends Game {

	public TouchInputDemoGame(GLView glView, Activity activity) {
		super("", glView, activity);
	}

	@Override
	public void initialize(float viewWidth, float viewHeight) {
		getScreenManager().registerScreen(0, new TouchInputDemoScreen(viewWidth, viewHeight, this), true);
		System.gc();
	}

}

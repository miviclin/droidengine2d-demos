package com.miviclin.droidengine2ddemos.input.accelerometer;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class AccelerometerDemoGame extends Game {

	public AccelerometerDemoGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getScreenManager().registerScreen(0, new AccelerometerDemoScreen(this), true);
		System.gc();
	}

}

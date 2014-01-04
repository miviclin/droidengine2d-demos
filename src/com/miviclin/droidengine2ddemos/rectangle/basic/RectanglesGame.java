package com.miviclin.droidengine2ddemos.rectangle.basic;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class RectanglesGame extends Game {

	public RectanglesGame(GLView glView, Activity activity) {
		super("", glView, activity);
	}

	@Override
	public void initialize(float viewWidth, float viewHeight) {
		getScreenManager().registerScreen(0, new RectanglesScreen(viewWidth, viewHeight, this), true);
		System.gc();
	}

}

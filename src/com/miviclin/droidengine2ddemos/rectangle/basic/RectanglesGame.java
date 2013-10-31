package com.miviclin.droidengine2ddemos.rectangle.basic;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class RectanglesGame extends Game {

	public RectanglesGame(GLView glView, Activity activity) {
		super("", glView, activity);
	}

	@Override
	public void initialize() {
		getSceneManager().registerScene("demo_rotation", new RectanglesScene(this), true);
		System.gc();
	}

}
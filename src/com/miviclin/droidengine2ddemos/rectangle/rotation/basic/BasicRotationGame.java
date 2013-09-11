package com.miviclin.droidengine2ddemos.rectangle.rotation.basic;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class BasicRotationGame extends Game {
	
	public BasicRotationGame(GLView glView, Activity activity) {
		super("", glView, activity);
	}
	
	@Override
	public void initialize() {
		getSceneManager().registerScene("demo_rotation", new BasicRotationScene(this), true);
		System.gc();
	}
	
}

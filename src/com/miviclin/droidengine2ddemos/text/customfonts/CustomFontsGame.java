package com.miviclin.droidengine2ddemos.text.customfonts;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class CustomFontsGame extends Game {
	
	public CustomFontsGame(GLView glView, Activity activity) {
		super("", glView, activity);
	}
	
	@Override
	public void initialize() {
		getSceneManager().registerScene("demo_custom_fonts", new CustomFontsScene(this), true);
		System.gc();
	}
	
}

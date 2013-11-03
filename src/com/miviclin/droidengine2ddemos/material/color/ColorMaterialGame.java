package com.miviclin.droidengine2ddemos.material.color;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class ColorMaterialGame extends Game {

	public ColorMaterialGame(GLView glView, Activity activity) {
		super("", glView, activity);
	}

	@Override
	public void initialize() {
		getSceneManager().registerScene("demo_color_material", new ColorMaterialScene(this), true);
		System.gc();
	}

}

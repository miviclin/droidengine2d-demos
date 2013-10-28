package com.miviclin.droidengine2ddemos.material.texturehsv;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class TextureHsvMaterialGame extends Game {

	public TextureHsvMaterialGame(GLView glView, Activity activity) {
		super("", glView, activity);
	}

	@Override
	public void initialize() {
		getSceneManager().registerScene("demo_texture_hsv_material", new TextureHsvMaterialScene(this), true);
		System.gc();
	}

}

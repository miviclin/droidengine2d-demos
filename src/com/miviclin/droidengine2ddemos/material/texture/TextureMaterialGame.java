package com.miviclin.droidengine2ddemos.material.texture;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class TextureMaterialGame extends Game {

	public TextureMaterialGame(GLView glView, Activity activity) {
		super("", glView, activity);
	}

	@Override
	public void initialize() {
		getSceneManager().registerScene("demo_texture_material", new TextureMaterialScene(this), true);
		System.gc();
	}

}

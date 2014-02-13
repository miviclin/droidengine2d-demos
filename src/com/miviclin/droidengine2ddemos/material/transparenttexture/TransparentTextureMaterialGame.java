package com.miviclin.droidengine2ddemos.material.transparenttexture;

import android.app.Activity;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;

public class TransparentTextureMaterialGame extends Game {

	public TransparentTextureMaterialGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getScreenManager().registerScreen(0, new TransparentTextureMaterialScreen(this), true);
		System.gc();
	}

}

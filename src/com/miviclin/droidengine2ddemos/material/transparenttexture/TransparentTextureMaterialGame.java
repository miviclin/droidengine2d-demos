package com.miviclin.droidengine2ddemos.material.transparenttexture;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class TransparentTextureMaterialGame extends AbstractGame {

	public TransparentTextureMaterialGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new TransparentTextureMaterialGameState(this), true);
	}

}

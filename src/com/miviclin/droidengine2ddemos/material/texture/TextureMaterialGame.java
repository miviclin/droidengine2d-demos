package com.miviclin.droidengine2ddemos.material.texture;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class TextureMaterialGame extends AbstractGame {

	public TextureMaterialGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new TextureMaterialGameState(this), true);
	}

}

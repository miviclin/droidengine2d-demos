package com.miviclin.droidengine2ddemos.material.texturecolor;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class TextureColorMaterialGame extends AbstractGame {

	public TextureColorMaterialGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new TextureColorMaterialGameState(this), true);
	}

}

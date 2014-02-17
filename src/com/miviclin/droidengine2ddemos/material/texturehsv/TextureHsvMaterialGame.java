package com.miviclin.droidengine2ddemos.material.texturehsv;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class TextureHsvMaterialGame extends AbstractGame {

	public TextureHsvMaterialGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new TextureHsvMaterialGameState(this), true);
	}

}

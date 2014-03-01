package com.miviclin.droidengine2ddemos.textureregion;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class TextureRegionFlippingDemoGame extends AbstractGame {

	public TextureRegionFlippingDemoGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new TextureRegionFlippingDemoGameState(this), true);
	}

}

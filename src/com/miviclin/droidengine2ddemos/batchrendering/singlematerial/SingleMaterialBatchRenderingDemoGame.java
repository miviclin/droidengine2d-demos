package com.miviclin.droidengine2ddemos.batchrendering.singlematerial;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class SingleMaterialBatchRenderingDemoGame extends AbstractGame {

	public SingleMaterialBatchRenderingDemoGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new SingleMaterialBatchRenderingDemoGameState(this), true);
	}

}

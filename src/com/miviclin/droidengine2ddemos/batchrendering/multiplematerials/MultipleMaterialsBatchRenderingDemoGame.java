package com.miviclin.droidengine2ddemos.batchrendering.multiplematerials;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class MultipleMaterialsBatchRenderingDemoGame extends AbstractGame {

	private MultipleMaterialsBatchRenderingCase caseToShow;

	public MultipleMaterialsBatchRenderingDemoGame(GLView glView, Activity activity,
			MultipleMaterialsBatchRenderingCase caseToShow) {

		super(glView, activity);
		this.caseToShow = caseToShow;
	}

	@Override
	public void initialize() {
		int id = 0;
		getGameStateManager().registerGameState(id, new MultipleMaterialsBatchRenderingDemoGameState(this, caseToShow));
		getGameStateManager().pushActiveGameState(id);
	}

}

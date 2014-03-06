package com.miviclin.droidengine2ddemos.animation.loopmodeenabled;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class AnimationLoopEnabledDemoGame extends AbstractGame {

	public AnimationLoopEnabledDemoGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new AnimationLoopEnabledDemoGameState(this), true);
	}

}

package com.miviclin.droidengine2ddemos.animation.loopmodedisabled;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class AnimationLoopDisabledDemoGame extends AbstractGame {

	public AnimationLoopDisabledDemoGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new AnimationLoopDisabledDemoGameState(this), true);
	}

}

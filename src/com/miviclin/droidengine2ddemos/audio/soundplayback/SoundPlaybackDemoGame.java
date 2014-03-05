package com.miviclin.droidengine2ddemos.audio.soundplayback;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class SoundPlaybackDemoGame extends AbstractGame {

	public SoundPlaybackDemoGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new SoundPlaybackDemoGameState(this), true);
	}

}

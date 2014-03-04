package com.miviclin.droidengine2ddemos.audio.musicplayback;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class MusicPlaybackDemoGame extends AbstractGame {

	public MusicPlaybackDemoGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(0, new MusicPlaybackDemoGameState(this), true);
	}

}

package com.miviclin.droidengine2ddemos.gamestate.switching;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class GameStateSwitchingDemoGame extends AbstractGame {

	public GameStateSwitchingDemoGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(State.MAIN_MENU.getId(), new MainMenu(this));
		getGameStateManager().registerGameState(State.LEVEL_SELECT_MENU.getId(), new LevelSelectMenu(this));
		getGameStateManager().registerGameState(State.LEVEL_1.getId(), new Level(this));

		getGameStateManager().pushActiveGameState(State.MAIN_MENU.getId());
	}

}

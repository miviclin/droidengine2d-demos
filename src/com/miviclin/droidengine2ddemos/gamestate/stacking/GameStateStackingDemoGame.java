package com.miviclin.droidengine2ddemos.gamestate.stacking;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;

public class GameStateStackingDemoGame extends AbstractGame {

	public GameStateStackingDemoGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(State.MAIN_MENU.getId(), new MainMenu(this));
		getGameStateManager().registerGameState(State.LEVEL_SELECT_MENU.getId(), new LevelSelectMenu(this));
		getGameStateManager().registerGameState(State.LEVEL_1.getId(), new Level(this));
		getGameStateManager().registerGameState(State.DIALOG.getId(), new DialogGameState(this));

		getGameStateManager().pushActiveGameState(State.MAIN_MENU.getId());
	}

}

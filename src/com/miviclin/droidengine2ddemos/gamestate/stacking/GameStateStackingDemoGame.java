package com.miviclin.droidengine2ddemos.gamestate.stacking;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.graphics.GLView;
import com.miviclin.droidengine2ddemos.gamestate.GameStates;
import com.miviclin.droidengine2ddemos.gamestate.MainMenu;

public class GameStateStackingDemoGame extends AbstractGame {

	public GameStateStackingDemoGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		getGameStateManager().registerGameState(GameStates.MAIN_MENU, new MainMenu(this));
		getGameStateManager().registerGameState(GameStates.LEVEL_SELECT_MENU, new LevelSelectMenu(this));
		getGameStateManager().registerGameState(GameStates.BACK_BUTTON_DIALOG, new DialogGameState(this));

		getGameStateManager().pushActiveGameState(GameStates.MAIN_MENU);
	}

}

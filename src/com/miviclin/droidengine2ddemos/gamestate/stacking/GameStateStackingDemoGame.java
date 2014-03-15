/*   Copyright 2013-2014 Miguel Vicente Linares
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
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

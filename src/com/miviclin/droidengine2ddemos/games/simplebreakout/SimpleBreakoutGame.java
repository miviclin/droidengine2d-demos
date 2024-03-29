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
package com.miviclin.droidengine2ddemos.games.simplebreakout;

import android.app.Activity;

import com.miviclin.droidengine2d.AbstractGame;
import com.miviclin.droidengine2d.gamestate.GameStateManager;
import com.miviclin.droidengine2d.graphics.GLView;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;

public class SimpleBreakoutGame extends AbstractGame {

	public SimpleBreakoutGame(GLView glView, Activity activity) {
		super(glView, activity);
	}

	@Override
	public void initialize() {
		TextureAtlas buttonsTextureAtlas = new TexturePackerAtlas();
		buttonsTextureAtlas.loadFromFile("textures/buttons-atlas.xml", getActivity());
		getTextureManager().addTextureAtlas(buttonsTextureAtlas);

		GameStateManager gameStateManager = getGameStateManager();

		gameStateManager.registerGameState(SimpleBreakoutGameStates.MAIN_MENU, new MainMenuGameState(this));
		gameStateManager.registerGameState(SimpleBreakoutGameStates.LEVEL, new LevelGameState(this));
		gameStateManager.registerGameState(SimpleBreakoutGameStates.GAME_LOST, new GameLostGameState(this));
		gameStateManager.registerGameState(SimpleBreakoutGameStates.GAME_WON, new GameWonGameState(this));

		gameStateManager.pushActiveGameState(SimpleBreakoutGameStates.MAIN_MENU);
	}

}

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

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;

public class GameLostGameState extends ButtonDialogGameState {

	public GameLostGameState(Game game) {
		super(game);
	}

	@Override
	public void onRegister() {
		super.onRegister();

	}

	@Override
	protected String getText() {
		return "You Loose!!!";
	}

	@Override
	protected float getFontSize() {
		return getCamera().getViewportWidth() * 0.1f;
	}

	@Override
	protected TextureRegion getButtonPressedTextureRegion() {
		return getTextureManager().getTextureRegion("btn-large-main-menu-selected.png");
	}

	@Override
	protected TextureRegion getButtonReleasedTextureRegion() {
		return getTextureManager().getTextureRegion("btn-large-main-menu-normal.png");
	}

	@Override
	protected void onButtonClick() {
		getGameStateManager().popActiveGameStates(2);
		getGameStateManager().pushActiveGameState(SimpleBreakoutGameStates.MAIN_MENU);
	}

}

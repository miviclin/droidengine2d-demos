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

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2ddemos.gamestate.GameStates;
import com.miviclin.droidengine2ddemos.gamestate.LevelSelectMenuDefault;
import com.miviclin.droidengine2ddemos.util.Button;

public class LevelSelectMenu extends LevelSelectMenuDefault {

	public LevelSelectMenu(Game game) {
		super(game);
	}

	@Override
	public void onRegister() {
		super.onRegister();

		TextureRegion buttonPressedTexture = getTextureManager().getTextureRegion("btn-small-1-selected.png");
		TextureRegion buttonReleasedTexture = getTextureManager().getTextureRegion("btn-small-1-normal.png");
		Button btnLevel1 = getBtnLevel1();
		btnLevel1.setButtonPressedTexture(buttonPressedTexture);
		btnLevel1.setButtonReleasedTexture(buttonReleasedTexture);

		btnLevel1.addOnClickListener(new Button.OnClickListener() {

			@Override
			public void onButtonPressed(Button button) {
			}

			@Override
			public void onButtonReleased(Button button) {
				getGameStateManager().pushActiveGameState(GameStates.BACK_BUTTON_DIALOG);
			}
		});
	}

}

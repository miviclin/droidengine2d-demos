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
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.gamestate.GameStateBase;
import com.miviclin.droidengine2ddemos.util.Button;

public class MainMenuGameState extends GameStateBase {

	private Button btnPlay;

	public MainMenuGameState(Game game) {
		super(game);
	}

	@Override
	public void draw(Graphics g) {
		g.setBackgroundColor(getBackgroundColor());
		g.drawRect(btnPlay.getMaterial(), btnPlay.getTransform());
	}

	@Override
	public void onRegister() {
		super.onRegister();

		setupBtnLevelSelect();
	}

	private void setupBtnLevelSelect() {
		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();

		TextureRegion buttonPressedTexture = getTextureManager().getTextureRegion("btn-large-play-selected.png");
		TextureRegion buttonReleasedTexture = getTextureManager().getTextureRegion("btn-large-play-normal.png");
		Vector2 buttonPosition = new Vector2(viewWidth / 2, viewHeight / 2);
		float ratio = buttonReleasedTexture.getWidth() / buttonReleasedTexture.getHeight();
		float margin = 20.0f;
		float buttonWidth = viewWidth - margin;
		float buttonHeight = buttonWidth / ratio;
		Vector2 buttonScale = new Vector2(buttonWidth, buttonHeight);
		Transform buttonTransform = new Transform(buttonPosition, buttonScale);

		btnPlay = new Button(buttonTransform, buttonPressedTexture, buttonReleasedTexture);
		btnPlay.addOnClickListener(new Button.OnClickListener() {

			@Override
			public void onButtonPressed(Button button) {
			}

			@Override
			public void onButtonReleased(Button button) {
				getGameStateManager().switchActiveGameState(SimpleBreakoutGameStates.LEVEL);
			}
		});

		addButton(btnPlay);
	}

}

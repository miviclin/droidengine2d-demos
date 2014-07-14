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
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureColorMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.gamestate.GameStateBase;
import com.miviclin.droidengine2ddemos.util.Button;

public class MainMenuGameState extends GameStateBase {

	private Logo logo;
	private Button btnPlay;

	public MainMenuGameState(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		logo.update(delta);
	}

	@Override
	public void draw(Graphics g) {
		logo.draw(g);
		g.drawRect(btnPlay.getMaterial(), btnPlay.getTransform());
	}

	@Override
	public void onRegister() {
		super.onRegister();

		TextureAtlas logoTextureAtlas = new TexturePackerAtlas();
		logoTextureAtlas.loadFromFile("textures/simple-breakout-logo-atlas.xml", getActivity());
		getTextureManager().addTextureAtlas(logoTextureAtlas);

		setupLogo();
		setupBtnPlay(logo);
	}

	private void setupLogo() {
		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();

		TextureRegion logoBaseTexture = getTextureManager().getTextureRegion("simple-breakout-logo-normal.png");
		TextureRegion logoOverlayedTexture = getTextureManager().getTextureRegion("simple-breakout-logo-selected.png");

		TextureColorMaterial baseMaterial = new TextureColorMaterial(logoBaseTexture, new Color(1, 1, 1));
		TextureColorMaterial overlayedMaterial = new TextureColorMaterial(logoOverlayedTexture, new Color(0, 1, 1, 0));

		float ratio = logoOverlayedTexture.getWidth() / logoOverlayedTexture.getHeight();
		float margin = 20.0f;
		float width = viewWidth - margin;
		float height = width / ratio;
		Vector2 scale = new Vector2(width, height);

		float positionY = viewHeight - (viewHeight / 3) - margin;
		positionY = ((viewHeight - positionY) < (height / 2)) ? viewHeight - (height / 2) - margin : positionY;

		Vector2 position = new Vector2(viewWidth / 2, positionY);
		Transform transform = new Transform(position, scale);
		logo = new Logo(transform, baseMaterial, overlayedMaterial);
	}

	private void setupBtnPlay(Logo logo) {
		float viewWidth = getCamera().getViewportWidth();

		TextureRegion buttonPressedTexture = getTextureManager().getTextureRegion("btn-large-play-selected.png");
		TextureRegion buttonReleasedTexture = getTextureManager().getTextureRegion("btn-large-play-normal.png");
		float ratio = buttonReleasedTexture.getWidth() / buttonReleasedTexture.getHeight();
		float margin = 20.0f;
		float buttonWidth = viewWidth - margin;
		float buttonHeight = buttonWidth / ratio;
		Vector2 buttonScale = new Vector2(buttonWidth, buttonHeight);

		float positionY = logo.getPosition().getY() - logo.getScale().getY() - margin;
		Vector2 buttonPosition = new Vector2(viewWidth / 2, positionY);
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

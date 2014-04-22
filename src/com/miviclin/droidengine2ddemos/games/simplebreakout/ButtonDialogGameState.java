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
import com.miviclin.droidengine2d.graphics.material.ColorMaterial;
import com.miviclin.droidengine2d.graphics.text.BitmapFont;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.gamestate.GameStateBase;
import com.miviclin.droidengine2ddemos.util.Button;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public abstract class ButtonDialogGameState extends GameStateBase {

	private Rectangle<ColorMaterial> background;
	private ButtonDialog dialog;

	public ButtonDialogGameState(Game game) {
		super(game);
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(background.getMaterial(), background.getTransform());
		dialog.draw(g);
	}

	@Override
	public void onRegister() {
		super.onRegister();

		float viewWidth = getGame().getCamera().getViewportWidth();
		float viewHeight = getGame().getCamera().getViewportHeight();

		Vector2 backgroundPosition = new Vector2(viewWidth / 2, viewHeight / 2);
		Vector2 backgroundDimensions = new Vector2(viewWidth, viewHeight);
		Transform backgroundTransform = new Transform(backgroundPosition, backgroundDimensions);
		Color backgroundColor = new Color(0.0f, 0.0f, 0.0f, 0.8f);
		background = new Rectangle<ColorMaterial>(backgroundTransform, new ColorMaterial(backgroundColor));

		Vector2 dialogWindowPosition = new Vector2(viewWidth / 2, viewHeight / 2);
		float dialogWindowWidth = viewWidth * 0.80f;
		float dialogWindowHeight = dialogWindowWidth;
		Vector2 dialogWindowDimensions = new Vector2(dialogWindowWidth, dialogWindowHeight);
		float titleBarHeight = Math.min(100.0f, dialogWindowHeight * 0.20f);

		BitmapFont font = new BitmapFont();
		font.loadFromFile("fonts/arial.fnt", getActivity());
		getTextureManager().addFontTextures(font);

		dialog = new ButtonDialog(dialogWindowPosition, dialogWindowDimensions, titleBarHeight, setupButton(),
				getText(), font, getFontSize());
	}

	private Button setupButton() {
		TextureRegion buttonPressedTexture = getButtonPressedTextureRegion();
		TextureRegion buttonReleasedTexture = getButtonReleasedTextureRegion();

		// Position and scale are set inside the constructor of ButtonDialog
		Vector2 buttonPosition = new Vector2(1, 1);
		Vector2 buttonScale = new Vector2(1, 1);
		Transform buttonTransform = new Transform(buttonPosition, buttonScale);

		Button button = new Button(buttonTransform, buttonPressedTexture, buttonReleasedTexture);
		button = new Button(buttonTransform, buttonPressedTexture, buttonReleasedTexture);
		button.addOnClickListener(new Button.OnClickListener() {

			@Override
			public void onButtonPressed(Button button) {
			}

			@Override
			public void onButtonReleased(Button button) {
				onButtonClick();
			}
		});

		addButton(button);

		return button;
	}

	protected abstract String getText();

	protected abstract float getFontSize();

	protected abstract TextureRegion getButtonPressedTextureRegion();

	protected abstract TextureRegion getButtonReleasedTextureRegion();

	protected abstract void onButtonClick();

}

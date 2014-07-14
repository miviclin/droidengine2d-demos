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

import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.ColorMaterial;
import com.miviclin.droidengine2d.graphics.text.BitmapFont;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Button;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class ButtonDialog {

	private Rectangle<ColorMaterial> container;
	private Rectangle<ColorMaterial> titleBar;
	private Button button;
	private String text;
	private Vector2 textPosition;
	private Color textColor;
	private BitmapFont font;
	private float fontSizePx;

	public ButtonDialog(Vector2 dialogPosition, Vector2 dialogDimensions, float titleBarHeight, Button button,
			String text, BitmapFont font, float fontSizePx) {

		Transform containerTransform = new Transform(dialogPosition, dialogDimensions);
		Color containerColor = new Color(1.0f, 0.0f, 1.0f);
		this.container = new Rectangle<ColorMaterial>(containerTransform, new ColorMaterial(containerColor));

		float titleBarY = dialogPosition.getY() + (dialogDimensions.getY() / 2) - (titleBarHeight / 2);
		Vector2 titleBarPosition = new Vector2(dialogPosition.getX(), titleBarY);
		Vector2 titleBarDimensions = new Vector2(dialogDimensions.getX(), titleBarHeight);
		Transform titleBarTransform = new Transform(titleBarPosition, titleBarDimensions);
		Color titleBarColor = new Color(0.0f, 1.0f, 0.0f);
		this.titleBar = new Rectangle<ColorMaterial>(titleBarTransform, new ColorMaterial(titleBarColor));

		this.button = button;

		float textureRegionWidth = button.getMaterial().getTextureRegion().getWidth();
		float textureRegionHeight = button.getMaterial().getTextureRegion().getHeight();
		float ratio = textureRegionWidth / textureRegionHeight;
		float buttonMargin = dialogDimensions.getX() * 0.20f;
		float buttonWidth = dialogDimensions.getX() - buttonMargin;
		float buttonHeight = buttonWidth / ratio;
		Vector2 buttonScale = new Vector2(buttonWidth, buttonHeight);
		this.button.getTransform().getScale().set(buttonScale);
		this.button.getTransform().getOrigin().set(buttonScale.getX() / 2, buttonScale.getY() / 2);

		float buttonX = dialogPosition.getX();
		float bottomOfContainer = containerTransform.getPosition().getY() - containerTransform.getOrigin().getY();
		float buttonY = bottomOfContainer + (buttonScale.getY() / 2) + (buttonMargin / 2);
		Vector2 buttonPosition = new Vector2(buttonX, buttonY);
		this.button.getTransform().getPosition().set(buttonPosition);

		this.text = text;

		float textX = buttonX - (font.measureLineWidth(text, fontSizePx) / 2.0f);
		float lineHeight = font.measureLineHeight(fontSizePx);
		this.textPosition = new Vector2(textX, buttonY + buttonHeight + lineHeight);
		this.textColor = new Color(0, 0, 0);
		this.font = font;
		this.fontSizePx = fontSizePx;
	}

	public void draw(Graphics g) {
		g.drawRect(container.getMaterial(), container.getTransform());
		g.drawRect(titleBar.getMaterial(), titleBar.getTransform());
		g.drawRect(button.getMaterial(), button.getTransform());
		g.drawText(text, font, textPosition, fontSizePx, textColor);
	}

	public Rectangle<ColorMaterial> getContainer() {
		return container;
	}

	public Rectangle<ColorMaterial> getTitleBar() {
		return titleBar;
	}

	public Button getBtnBack() {
		return button;
	}

}

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
package com.miviclin.droidengine2ddemos.text.customfonts;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.text.BitmapFont;
import com.miviclin.droidengine2d.util.math.Vector2;

public class CustomFontsGameState extends GameStateAdapter {

	private Color backgroundColor;

	private BitmapFont fontArial;
	private BitmapFont fontRosewood;
	private BitmapFont font04b03;

	private Vector2 textArialPosition, textRosewoodPosition, text04b03Position;
	private Vector2 rotationPoint;
	private Color textArialColor, textRosewoodColor1, textRosewoodColor2, text04b03Color;
	private String textArial, textRosewood, text04b03;

	private float textSizePx;

	public CustomFontsGameState(Game game) {
		super(game);
	}

	@Override
	public void draw(Graphics g) {
		g.setBackgroundColor(backgroundColor);
		g.drawText(textRosewood, fontRosewood, textRosewoodPosition, textSizePx, rotationPoint, 45, textRosewoodColor1);
		g.drawText(textRosewood, fontRosewood, textRosewoodPosition, textSizePx, textRosewoodColor2);
		g.drawText(textArial, fontArial, textArialPosition, textSizePx, textArialColor);
		g.drawText(text04b03, font04b03, text04b03Position, textSizePx, text04b03Color);
	}

	@Override
	public void onRegister() {
		backgroundColor = new Color(0.5f, 0.5f, 0.5f);

		final float viewWidth = getCamera().getViewportWidth();
		final float viewHeight = getCamera().getViewportHeight();

		fontArial = new BitmapFont();
		fontArial.loadFromFile("fonts/arial.fnt", getActivity());
		getTextureManager().addFontTextures(fontArial);

		fontRosewood = new BitmapFont();
		fontRosewood.loadFromFile("fonts/rosewood.fnt", getActivity());
		getTextureManager().addFontTextures(fontRosewood);

		font04b03 = new BitmapFont();
		font04b03.loadFromFile("fonts/04b03.fnt", getActivity());
		getTextureManager().addFontTextures(font04b03);

		textSizePx = viewHeight / 9.5f;

		textArialPosition = new Vector2(viewWidth / 8.0f, viewHeight / 3.0f);
		textArialColor = new Color(1, 1, 1);
		textArial = "Testing Arial";

		textRosewoodPosition = new Vector2(viewWidth / 8.0f, viewHeight / 2.0f);
		textRosewoodColor1 = new Color(0.7f, 0.7f, 0.7f);
		textRosewoodColor2 = new Color(0, 1, 1);
		textRosewood = "Testing Rosewood";
		float lineWidth = fontRosewood.measureLineWidth(textRosewood, textSizePx);
		rotationPoint = new Vector2(textRosewoodPosition.getX() + lineWidth / 2,
				textRosewoodPosition.getY() + fontRosewood.measureLineHeight(textSizePx) / 2);

		text04b03Position = new Vector2(viewWidth / 8.0f, viewHeight - (viewHeight / 3.0f));
		text04b03Color = new Color(0, 0, 0);
		text04b03 = "Testing 04b03";
	}

}

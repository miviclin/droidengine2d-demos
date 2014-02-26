package com.miviclin.droidengine2ddemos.text.customfonts;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameState;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.text.BitmapFont;
import com.miviclin.droidengine2d.util.math.Vector2;

public class CustomFontsGameState extends GameState {

	private Color backgroundColor;

	private BitmapFont fontArial;
	private BitmapFont fontRosewood;
	private BitmapFont font04b03;

	private Vector2 textArialPosition, textRosewoodPosition, text04b03Position;
	private Vector2 rotationPoint;
	private Color textArialColor, textRosewoodColor1, textRosewoodColor2, text04b03Color;
	private String textArial, textRosewood, text04b03;

	private int textSizePx;

	public CustomFontsGameState(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {

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

		fontArial = new BitmapFont();
		fontArial.loadFromFile("fonts/arial.fnt", getActivity());
		getTextureManager().addFontTextures(fontArial);

		fontRosewood = new BitmapFont();
		fontRosewood.loadFromFile("fonts/rosewood.fnt", getActivity());
		getTextureManager().addFontTextures(fontRosewood);

		font04b03 = new BitmapFont();
		font04b03.loadFromFile("fonts/04b03.fnt", getActivity());
		getTextureManager().addFontTextures(font04b03);

		textSizePx = 50;

		textArialPosition = new Vector2(100, 150);
		textArialColor = new Color(1, 1, 1);
		textArial = "Testing Arial";

		textRosewoodPosition = new Vector2(100, 250);
		textRosewoodColor1 = new Color(0.7f, 0.7f, 0.7f);
		textRosewoodColor2 = new Color(0, 1, 1);
		textRosewood = "Testing Rosewood";
		float lineWidth = fontRosewood.measureLineWidth(textRosewood, textSizePx);
		rotationPoint = new Vector2(textRosewoodPosition.getX() + lineWidth / 2,
				textRosewoodPosition.getY() + fontRosewood.measureLineHeight(textSizePx) / 2);

		text04b03Position = new Vector2(100, 350);
		text04b03Color = new Color(0, 0, 0);
		text04b03 = "Testing 04b03";
	}

	@Override
	public void onActivation() {
	}

	@Override
	public void onDeactivation() {
	}

	@Override
	public void onPause() {
	}

	@Override
	public void onResume() {
	}

	@Override
	public void onDispose() {
	}

}

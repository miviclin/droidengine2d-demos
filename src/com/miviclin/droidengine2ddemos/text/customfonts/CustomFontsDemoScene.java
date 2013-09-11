package com.miviclin.droidengine2ddemos.text.customfonts;

import android.opengl.GLES20;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.text.BitmapFont;
import com.miviclin.droidengine2d.scene.Scene;
import com.miviclin.droidengine2d.util.math.Vector2;

public class CustomFontsDemoScene extends Scene {
	
	private BitmapFont fontArial;
	private BitmapFont fontRosewood;
	private BitmapFont font04b03;
	
	private Vector2 textArialPosition, textRosewoodPosition, text04b03Position;
	private Vector2 rotationPoint;
	private Color textArialColor, textRosewoodColor1, textRosewoodColor2, text04b03Color;
	private String textArial, textRosewood, text04b03;
	
	private int textSizePx;
	
	public CustomFontsDemoScene(Game game) {
		super(game);
	}
	
	@Override
	public void update(float delta) {
		
	}
	
	@Override
	public void draw(Graphics g) {
		GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		g.drawText(textRosewood, fontRosewood, textRosewoodPosition, textSizePx, rotationPoint, 45, textRosewoodColor1);
		g.drawText(textRosewood, fontRosewood, textRosewoodPosition, textSizePx, textRosewoodColor2);
		g.drawText(textArial, fontArial, textArialPosition, textSizePx, textArialColor);
		g.drawText(text04b03, font04b03, text04b03Position, textSizePx, text04b03Color);
	}
	
	@Override
	public void onRegister() {
		fontArial = new BitmapFont();
		fontArial.loadFromXML("fonts/arial.fnt", getGame().getActivity());
		getGame().getTextureManager().addFontTextures(fontArial);
		
		fontRosewood = new BitmapFont();
		fontRosewood.loadFromXML("fonts/rosewood.fnt", getGame().getActivity());
		getGame().getTextureManager().addFontTextures(fontRosewood);
		
		font04b03 = new BitmapFont();
		font04b03.loadFromXML("fonts/04b03.fnt", getGame().getActivity());
		getGame().getTextureManager().addFontTextures(font04b03);
		
		textSizePx = 50;
		
		textArialPosition = new Vector2(100, 150);
		textArialColor = new Color(1, 1, 1);
		textArial = "Testing Arial";
		
		textRosewoodPosition = new Vector2(100, 250);
		textRosewoodColor1 = new Color(0.7f, 0.7f, 0.7f);
		textRosewoodColor2 = new Color(0, 1, 1);
		textRosewood = "Testing Rosewood";
		rotationPoint = new Vector2(textRosewoodPosition.getX() + fontRosewood.measureLineWidth(textRosewood, textSizePx) / 2,
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
	public void dispose() {
	}
	
}

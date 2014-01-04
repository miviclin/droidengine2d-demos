package com.miviclin.droidengine2ddemos.rectangle.rotation.anchorpoint;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.ColorMaterial;
import com.miviclin.droidengine2d.screen.Screen;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class AnchorPointRotationScreen extends Screen {

	private Color backgroundColor;
	private Rectangle<ColorMaterial> rectangle1;
	private Rectangle<ColorMaterial> rectangle2;

	public AnchorPointRotationScreen(float viewWidth, float viewHeight, Game game) {
		super(viewWidth, viewHeight, game);
	}

	@Override
	public void update(float delta) {
		rectangle1.getTransform().setRotation((rectangle1.getTransform().getRotation() + (10.0f / delta)) % 360.0f);
		rectangle2.getTransform().setRotation((rectangle2.getTransform().getRotation() - (10.0f / delta)) % 360.0f);
	}

	@Override
	public void draw(Graphics g) {
		g.setBackgroundColor(backgroundColor);
		g.drawRect(rectangle1.getMaterial(), rectangle1.getTransform());
		g.drawRect(rectangle2.getMaterial(), rectangle2.getTransform());
	}

	@Override
	public void onRegister() {
		backgroundColor = new Color(1, 1, 1);

		Vector2 pos1 = new Vector2(getWidth() / 2, getHeight() / 2);
		Vector2 scale1 = new Vector2(200, 200);
		Vector2 orig1 = new Vector2(scale1.getX() / 2, scale1.getY() / 2);
		rectangle1 = new Rectangle<ColorMaterial>(new Transform(pos1, scale1, orig1, 0),
				new ColorMaterial(new Color(0, 0, 0)));

		Vector2 pos2 = new Vector2(getWidth() / 2, getHeight() / 2);
		Vector2 scale2 = new Vector2(200, 200);
		Vector2 orig2 = new Vector2(0, 0);
		rectangle2 = new Rectangle<ColorMaterial>(new Transform(pos2, scale2, orig2, 0),
				new ColorMaterial(new Color(1, 0, 0)));
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
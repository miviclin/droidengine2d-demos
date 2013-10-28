package com.miviclin.droidengine2ddemos.rectangle.rotation.basic;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.ColorMaterial;
import com.miviclin.droidengine2d.scene.Scene;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class BasicRotationScene extends Scene {

	private Color backgroundColor;
	private Rectangle<ColorMaterial> rectangle;

	public BasicRotationScene(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {
		rectangle.getTransform().setRotation((rectangle.getTransform().getRotation() + (10.0f / delta)) % 360.0f);
	}

	@Override
	public void draw(Graphics g) {
		g.setBackgroundColor(backgroundColor);
		g.drawRect(rectangle.getMaterial(), rectangle.getTransform());
	}

	@Override
	public void onRegister() {
		backgroundColor = new Color(1, 1, 1);

		Vector2 rectPos = new Vector2(getGame().getGameViewWidth() / 2, getGame().getGameViewHeight() / 2);
		Vector2 rectScale = new Vector2(200, 200);
		Transform transform = new Transform(rectPos, rectScale);
		rectangle = new Rectangle<ColorMaterial>(transform, new ColorMaterial(new Color(0, 0, 0)));
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

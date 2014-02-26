package com.miviclin.droidengine2ddemos.rectangle.basic;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameState;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.ColorMaterial;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class RectanglesGameState extends GameState {

	private Color backgroundColor;
	private Rectangle<ColorMaterial> rectangle1;
	private Rectangle<ColorMaterial> rectangle2;
	private Rectangle<ColorMaterial> rectangle3;

	public RectanglesGameState(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void draw(Graphics g) {
		g.setBackgroundColor(backgroundColor);
		g.drawRect(rectangle1.getMaterial(), rectangle1.getTransform());
		g.drawRect(rectangle2.getMaterial(), rectangle2.getTransform());
		g.drawRect(rectangle3.getMaterial(), rectangle3.getTransform());
	}

	@Override
	public void onRegister() {
		backgroundColor = new Color(1, 1, 1);

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();

		Vector2 pos1 = new Vector2(viewWidth / 2, viewHeight / 2);
		rectangle1 = new Rectangle<ColorMaterial>(new Transform(pos1, new Vector2(200, 200)),
				new ColorMaterial(new Color(0, 0, 0)));

		Vector2 pos2 = new Vector2(200, viewHeight / 2);
		rectangle2 = new Rectangle<ColorMaterial>(new Transform(pos2, new Vector2(100, 350)),
				new ColorMaterial(new Color(0, 0, 0)));

		Vector2 pos3 = new Vector2(viewWidth - 200, viewHeight / 2);
		rectangle3 = new Rectangle<ColorMaterial>(new Transform(pos3, new Vector2(100, 50)),
				new ColorMaterial(new Color(0, 0, 0)));
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

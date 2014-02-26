package com.miviclin.droidengine2ddemos.input.accelerometer;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameState;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.input.sensor.Accelerometer;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.MovingRectangle;

public class AccelerometerDemoGameState extends GameState {

	private MovingRectangle<TextureMaterial> rectangle;

	public AccelerometerDemoGameState(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {
		Accelerometer accelerometer = getGameStateInputManager().getAccelerometer();
		float accelerometerX = accelerometer.getValuesListener().getX();
		float accelerometerY = accelerometer.getValuesListener().getY();
		rectangle.move(-accelerometerX * 0.1f, -accelerometerY * 0.1f, delta);

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();
		handleRectangleCollisionsWithViewBounds(viewWidth, viewHeight);
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(rectangle.getMaterial(), rectangle.getTransform());
	}

	@Override
	public void onRegister() {
		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/squares.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();
		Transform transform = new Transform(new Vector2(viewWidth / 2, viewHeight / 2), new Vector2(240, 240));
		rectangle = new MovingRectangle<TextureMaterial>(transform,
				new TextureMaterial(textureAtlas.getTextureRegion("greensquare_on_shadow.png")));

		Accelerometer accelerometer = getGameStateInputManager().getAccelerometer();
		accelerometer.getValuesListener().useCoordinateSystemOfDisplay();
		accelerometer.startListening();
	}

	@Override
	public void onActivation() {
	}

	@Override
	public void onDeactivation() {
	}

	@Override
	public void onPause() {
		Accelerometer accelerometer = getGameStateInputManager().getAccelerometer();
		accelerometer.stopListening();
	}

	@Override
	public void onResume() {
		Accelerometer accelerometer = getGameStateInputManager().getAccelerometer();
		accelerometer.startListening();
	}

	@Override
	public void onDispose() {
	}

	private void handleRectangleCollisionsWithViewBounds(float viewWidth, float viewHeight) {
		Vector2 position = rectangle.getTransform().getPosition();
		Vector2 scale = rectangle.getTransform().getScale();
		float halfWidth = scale.getX() / 2.0f;
		if ((position.getX() - halfWidth) < 0) {
			position.setX(halfWidth);
		} else if (viewWidth < (position.getX() + halfWidth)) {
			position.setX(viewWidth - halfWidth);
		}
		float halfHeight = scale.getY() / 2.0f;
		if ((position.getY() - halfHeight) < 0) {
			position.setY(halfHeight);
		} else if (viewHeight < (position.getY() + halfHeight)) {
			position.setY(viewHeight - halfHeight);
		}
	}

}

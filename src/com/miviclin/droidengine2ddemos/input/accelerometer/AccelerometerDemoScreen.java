package com.miviclin.droidengine2ddemos.input.accelerometer;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.input.sensor.Accelerometer;
import com.miviclin.droidengine2d.screen.Screen;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.MovingRectangle;

public class AccelerometerDemoScreen extends Screen {

	private MovingRectangle<TextureMaterial> rectangle;

	public AccelerometerDemoScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {
		Accelerometer accelerometer = getInputManager().getAccelerometer();
		float accelerometerX = accelerometer.getValuesListener().getX();
		float accelerometerY = accelerometer.getValuesListener().getY();
		rectangle.move(-accelerometerX * 0.1f, -accelerometerY * 0.1f, delta);
		handleRectangleCollisionsWithScreenBounds();
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(rectangle.getMaterial(), rectangle.getTransform());
	}

	@Override
	public void onRegister() {
		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/squares.xml", getGame().getActivity());
		getGame().getTextureManager().addTextureAtlas(textureAtlas);

		float viewWidth = getWidth();
		float viewHeight = getHeight();
		Transform transform = new Transform(new Vector2(viewWidth / 2, viewHeight / 2), new Vector2(240, 240));
		rectangle = new MovingRectangle<TextureMaterial>(transform,
				new TextureMaterial(textureAtlas.getTextureRegion("greensquare_on_shadow.png")));

		Accelerometer accelerometer = getInputManager().getAccelerometer();
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
		Accelerometer accelerometer = getInputManager().getAccelerometer();
		accelerometer.stopListening();
	}

	@Override
	public void onResume() {
		Accelerometer accelerometer = getInputManager().getAccelerometer();
		accelerometer.startListening();
	}

	@Override
	public void onDispose() {
	}

	private void handleRectangleCollisionsWithScreenBounds() {
		Vector2 position = rectangle.getTransform().getPosition();
		Vector2 scale = rectangle.getTransform().getScale();
		float halfWidth = scale.getX() / 2.0f;
		if ((position.getX() - halfWidth) < 0) {
			position.setX(halfWidth);
		} else if (getWidth() < (position.getX() + halfWidth)) {
			position.setX(getWidth() - halfWidth);
		}
		float halfHeight = scale.getY() / 2.0f;
		if ((position.getY() - halfHeight) < 0) {
			position.setY(halfHeight);
		} else if (getHeight() < (position.getY() + halfHeight)) {
			position.setY(getHeight() - halfHeight);
		}
	}

}

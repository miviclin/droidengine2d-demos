package com.miviclin.droidengine2ddemos.input.touch;

import android.view.MotionEvent;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.input.TouchInputProcessor;
import com.miviclin.droidengine2d.screen.Screen;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class TouchDemoScreen extends Screen {

	private Color backgroundColor;
	private volatile Rectangle<TextureMaterial> rectangle;

	public TouchDemoScreen(float viewWidth, float viewHeight, Game game) {
		super(viewWidth, viewHeight, game);
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void draw(Graphics g) {
		g.setBackgroundColor(backgroundColor);
		g.drawRect(rectangle.getMaterial(), rectangle.getTransform());
	}

	@Override
	public void onRegister() {
		backgroundColor = new Color(0.5f, 0.5f, 0.5f);

		final TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/circles.xml", getGame().getActivity());
		getGame().getTextureManager().addTextureAtlas(textureAtlas);

		Transform transform = new Transform(new Vector2(getWidth() / 2, getHeight() / 2), new Vector2(240, 240));
		rectangle = new Rectangle<TextureMaterial>(transform,
				new TextureMaterial(textureAtlas.getTextureRegion("circle-red.png")));

		getInputManager().getTouchInputController().setTouchInputProcessor(new TouchInputProcessor() {

			@Override
			public void processMotionEvent(MotionEvent motionEvent) {
				TextureRegion textureRegion;
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					textureRegion = textureAtlas.getTextureRegion("circle-red.png");
				} else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
					textureRegion = textureAtlas.getTextureRegion("circle-red.png");
				} else {
					textureRegion = textureAtlas.getTextureRegion("circle-blue.png");
				}
				rectangle.getMaterial().setTextureRegion(textureRegion);
				rectangle.getTransform().getPosition().set(motionEvent.getX(), getHeight() - motionEvent.getY());
			}
		});
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

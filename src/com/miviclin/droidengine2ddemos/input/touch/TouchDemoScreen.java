package com.miviclin.droidengine2ddemos.input.touch;

import android.util.Log;
import android.view.MotionEvent;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.input.TouchInputProcessor;
import com.miviclin.droidengine2d.screen.Screen;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2d.util.time.TimeCounter;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class TouchDemoScreen extends Screen {

	private static final int MAX_VISIBLE_TIME_MILLIS = 200;

	private Color backgroundColor;
	private volatile Rectangle<TextureMaterial> rectangle;
	private volatile TimeCounter timeCounter;

	public TouchDemoScreen(float viewWidth, float viewHeight, Game game) {
		super(viewWidth, viewHeight, game);
	}

	@Override
	public void update(float delta) {
		timeCounter.update();
		getInputManager().processInput();
	}

	@Override
	public void draw(Graphics g) {
		g.setBackgroundColor(backgroundColor);
		if (timeCounter.getMilliseconds() <= MAX_VISIBLE_TIME_MILLIS) {
			g.drawRect(rectangle.getMaterial(), rectangle.getTransform());
		}
	}

	@Override
	public void onRegister() {
		backgroundColor = new Color(0.5f, 0.5f, 0.5f);

		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/touch-icon-atlas.xml", getGame().getActivity());
		getGame().getTextureManager().addTextureAtlas(textureAtlas);

		Transform transform = new Transform(new Vector2(getWidth() / 2, getHeight() / 2), new Vector2(240, 240));
		rectangle = new Rectangle<TextureMaterial>(transform,
				new TextureMaterial(textureAtlas.getTextureRegion("touch-icon.png")));

		timeCounter = new TimeCounter();

		getInputManager().getTouchInputController().setTouchInputProcessor(new TouchInputProcessor() {

			@Override
			public void processMotionEvent(MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					rectangle.getTransform().getPosition().set(motionEvent.getX(), getHeight() - motionEvent.getY());
					timeCounter.reset();
					Log.d("touch", "touch " + motionEvent.getAction());
				}
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

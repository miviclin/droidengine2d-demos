package com.miviclin.droidengine2ddemos.animation.loopmodedisabled;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.animation.Animation;
import com.miviclin.droidengine2d.graphics.animation.AnimationFrame;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.gamestate.GameStateBase;
import com.miviclin.droidengine2ddemos.util.Button;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class AnimationLoopDisabledDemoGameState extends GameStateBase {

	private Rectangle<TextureMaterial> rectangle;
	private Animation animation;
	private Button btnPlay;

	public AnimationLoopDisabledDemoGameState(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {
		AnimationFrame currentAnimationFrame = animation.update(delta);
		rectangle.getMaterial().setTextureRegion(currentAnimationFrame.getTextureRegion());
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(rectangle.getMaterial(), rectangle.getTransform());
		g.drawRect(btnPlay.getMaterial(), btnPlay.getTransform());
	}

	@Override
	public void onRegister() {
		super.onRegister();
		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/kenney-p1-walk.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		animation = new Animation();
		animation.setLoopModeEnabled(false);

		String frameName;
		float animationFrameDelay = 30;
		int numFrames = textureAtlas.getTextureRegions().size();
		for (int i = 1; i <= numFrames; i++) {
			if (i < 10) {
				frameName = "p1_walk0" + i + ".png";
			} else {
				frameName = "p1_walk" + i + ".png";
			}
			TextureRegion textureRegion = textureAtlas.getTextureRegion(frameName);
			animation.addFrame(new AnimationFrame(animationFrameDelay, textureRegion));
		}

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();

		TextureRegion textureRegion = animation.getCurrentFrame().getTextureRegion();
		float ratio = textureRegion.getWidth() / textureRegion.getHeight();
		float rectangleWidth = viewWidth / 3.0f;
		float rectangleHeight = rectangleWidth / ratio;
		Vector2 rectScale = new Vector2(rectangleWidth, rectangleHeight);

		Vector2 rectPos = new Vector2(viewWidth / 2, viewHeight / 2);
		Transform transform = new Transform(rectPos, rectScale);
		rectangle = new Rectangle<TextureMaterial>(transform, new TextureMaterial(textureRegion));

		initializePlayButton();
	}

	private void initializePlayButton() {
		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/buttons-atlas.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		TextureRegion trPlayNormal = textureAtlas.getTextureRegion("btn-music-play-normal.png");
		TextureRegion trPlaySelected = textureAtlas.getTextureRegion("btn-music-play-selected.png");

		float viewWidth = getCamera().getViewportWidth();

		float margin = 20.0f;
		float buttonSize = viewWidth / 3.5f;
		Vector2 buttonScale = new Vector2(buttonSize, buttonSize);
		float buttonX = viewWidth / 2;
		float buttonY = margin + buttonSize;
		Vector2 buttonPosition = new Vector2(buttonX, buttonY);
		Transform transform = new Transform(buttonPosition, buttonScale);
		btnPlay = new Button(transform, trPlaySelected, trPlayNormal);
		btnPlay.addOnClickListener(new Button.OnClickListener() {

			@Override
			public void onButtonPressed(Button button) {
			}

			@Override
			public void onButtonReleased(Button button) {
				animation.reset();
			}
		});
		addButton(btnPlay);
	}

}

package com.miviclin.droidengine2ddemos.animation.loopmodeenabled;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.animation.Animation;
import com.miviclin.droidengine2d.graphics.animation.AnimationFrame;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class AnimationLoopEnabledDemoGameState extends GameStateAdapter {

	private Rectangle<TextureMaterial> rectangle;
	private Animation animation;

	public AnimationLoopEnabledDemoGameState(Game game) {
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
	}

	@Override
	public void onRegister() {
		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/kenney-p1-walk.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		animation = new Animation();

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
	}

}

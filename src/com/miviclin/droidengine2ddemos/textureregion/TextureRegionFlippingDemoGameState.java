package com.miviclin.droidengine2ddemos.textureregion;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class TextureRegionFlippingDemoGameState extends GameStateAdapter {

	private Rectangle<TextureMaterial> rectDefault;
	private Rectangle<TextureMaterial> rectFlippedHorizontally;
	private Rectangle<TextureMaterial> rectFlippedVertically;
	private Rectangle<TextureMaterial> rectFlippedBoth;

	public TextureRegionFlippingDemoGameState(Game game) {
		super(game);
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(rectDefault.getMaterial(), rectDefault.getTransform());
		g.drawRect(rectFlippedHorizontally.getMaterial(), rectFlippedHorizontally.getTransform());
		g.drawRect(rectFlippedVertically.getMaterial(), rectFlippedVertically.getTransform());
		g.drawRect(rectFlippedBoth.getMaterial(), rectFlippedBoth.getTransform());
	}

	@Override
	public void onRegister() {
		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/buttons-atlas.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();
		float separationX = viewWidth / 3.0f;
		float separationY = viewHeight / 3.0f;
		float rectSize = viewWidth / 3.5f;
		Vector2 rectScale = new Vector2(rectSize, rectSize);

		TextureRegion trDefault = textureAtlas.getTextureRegion("btn-arrow-rotated-normal.png");
		float rectDefaultX = separationX;
		float rectDefaultY = viewHeight - separationY;
		Vector2 rectDefaultPosition = new Vector2(rectDefaultX, rectDefaultY);
		rectDefault = createRectangle(rectDefaultPosition, rectScale, trDefault);

		TextureRegion trFlippedHorizontally = new TextureRegion(trDefault);
		trFlippedHorizontally.flipHorizontally();
		getTextureManager().addTextureRegion("btn-arrow-rotated-normal-flipped-x.png", trFlippedHorizontally);
		float rectFlippedHorizontallyX = viewWidth - separationX;
		float rectFlippedHorizontallyY = viewHeight - separationY;
		Vector2 rectFlippedHorizontallyPosition = new Vector2(rectFlippedHorizontallyX, rectFlippedHorizontallyY);
		rectFlippedHorizontally = createRectangle(rectFlippedHorizontallyPosition, rectScale, trFlippedHorizontally);

		TextureRegion trFlippedVertically = new TextureRegion(trDefault);
		trFlippedVertically.flipVertically();
		getTextureManager().addTextureRegion("btn-arrow-rotated-normal-flipped-y.png", trFlippedVertically);
		float rectFlippedVerticallyX = separationX;
		float rectFlippedVerticallyY = separationY;
		Vector2 rectFlippedVerticallyPosition = new Vector2(rectFlippedVerticallyX, rectFlippedVerticallyY);
		rectFlippedVertically = createRectangle(rectFlippedVerticallyPosition, rectScale, trFlippedVertically);

		TextureRegion trFlippedBoth = new TextureRegion(trDefault);
		trFlippedBoth.flipHorizontally();
		trFlippedBoth.flipVertically();
		getTextureManager().addTextureRegion("btn-arrow-rotated-normal-flipped-xy.png", trFlippedBoth);
		float rectFlippedBothX = viewWidth - separationX;
		float rectFlippedBothY = separationY;
		Vector2 rectFlippedBothPosition = new Vector2(rectFlippedBothX, rectFlippedBothY);
		rectFlippedBoth = createRectangle(rectFlippedBothPosition, rectScale, trFlippedBoth);
	}

	private Rectangle<TextureMaterial> createRectangle(Vector2 position, Vector2 scale, TextureRegion textureRegion) {
		Transform transform = new Transform(position, scale);
		TextureMaterial material = new TextureMaterial(textureRegion);
		Rectangle<TextureMaterial> rectangle = new Rectangle<TextureMaterial>(transform, material);
		return rectangle;
	}

}

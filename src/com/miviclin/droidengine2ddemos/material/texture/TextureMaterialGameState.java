package com.miviclin.droidengine2ddemos.material.texture;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class TextureMaterialGameState extends GameStateAdapter {

	private Rectangle<TextureMaterial> rectangle;

	public TextureMaterialGameState(Game game) {
		super(game);
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
		rectangle = new Rectangle<TextureMaterial>(transform,
				new TextureMaterial(textureAtlas.getTextureRegion("greensquare_on_shadow.png")));
	}

}

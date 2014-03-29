/*   Copyright 2013-2014 Miguel Vicente Linares
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.miviclin.droidengine2ddemos.material.texture;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.MathUtils;
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

		TextureRegion textureRegion = textureAtlas.getTextureRegion("greensquare_on_shadow.png");

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();

		float rectSize = MathUtils.clamp(Math.min(viewWidth, viewHeight) / 2, 100, textureRegion.getWidth() * 2);

		Vector2 rectPosition = new Vector2(viewWidth / 2, viewHeight / 2);
		Vector2 rectScale = new Vector2(rectSize, rectSize);
		Transform transform = new Transform(rectPosition, rectScale);
		rectangle = new Rectangle<TextureMaterial>(transform, new TextureMaterial(textureRegion));
	}

}

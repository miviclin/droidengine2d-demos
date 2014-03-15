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
package com.miviclin.droidengine2ddemos.input.touch;

import android.view.MotionEvent;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.input.MotionEventProcessor;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class TouchInputDemoGameState extends GameStateAdapter {

	private Color backgroundColor;
	private Rectangle<TextureMaterial> rectangle;

	public TouchInputDemoGameState(Game game) {
		super(game);
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
		textureAtlas.loadFromFile("textures/circles.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		final float viewWidth = getCamera().getViewportWidth();
		final float viewHeight = getCamera().getViewportHeight();
		Transform transform = new Transform(new Vector2(viewWidth / 2, viewHeight / 2), new Vector2(240, 240));
		rectangle = new Rectangle<TextureMaterial>(transform,
				new TextureMaterial(textureAtlas.getTextureRegion("circle-red.png")));

		getGameStateInputManager().getTouchProcessor().setMotionEventProcessor(new MotionEventProcessor() {

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
				rectangle.getTransform().getPosition().set(motionEvent.getX(), viewHeight - motionEvent.getY());
			}
		});
	}

}

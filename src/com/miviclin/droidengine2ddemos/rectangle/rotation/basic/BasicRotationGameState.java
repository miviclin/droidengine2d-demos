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
package com.miviclin.droidengine2ddemos.rectangle.rotation.basic;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.ColorMaterial;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class BasicRotationGameState extends GameStateAdapter {

	private Color backgroundColor;
	private Rectangle<ColorMaterial> rectangle;

	public BasicRotationGameState(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {
		rectangle.getTransform().setRotation((rectangle.getTransform().getRotation() + (10.0f / delta)) % 360.0f);
	}

	@Override
	public void draw(Graphics g) {
		g.setBackgroundColor(backgroundColor);
		g.drawRect(rectangle.getMaterial(), rectangle.getTransform());
	}

	@Override
	public void onRegister() {
		backgroundColor = new Color(1, 1, 1);

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();
		
		float rectSize = viewWidth / 2.5f;
		
		Vector2 rectPos = new Vector2(viewWidth / 2, viewHeight / 2);
		Vector2 rectScale = new Vector2(rectSize, rectSize);
		Transform transform = new Transform(rectPos, rectScale);
		rectangle = new Rectangle<ColorMaterial>(transform, new ColorMaterial(new Color(0, 0, 0)));
	}

}

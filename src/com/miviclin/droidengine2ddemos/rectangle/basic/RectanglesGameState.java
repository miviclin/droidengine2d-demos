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
package com.miviclin.droidengine2ddemos.rectangle.basic;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.ColorMaterial;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class RectanglesGameState extends GameStateAdapter {

	private Color backgroundColor;
	private Rectangle<ColorMaterial> rectangle1;
	private Rectangle<ColorMaterial> rectangle2;
	private Rectangle<ColorMaterial> rectangle3;

	public RectanglesGameState(Game game) {
		super(game);
	}

	@Override
	public void draw(Graphics g) {
		g.setBackgroundColor(backgroundColor);
		g.drawRect(rectangle1.getMaterial(), rectangle1.getTransform());
		g.drawRect(rectangle2.getMaterial(), rectangle2.getTransform());
		g.drawRect(rectangle3.getMaterial(), rectangle3.getTransform());
	}

	@Override
	public void onRegister() {
		backgroundColor = new Color(1, 1, 1);

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();

		float rectSize = viewWidth / 2.5f;
		float margin = rectSize / 4.0f;

		Vector2 scale1 = new Vector2(rectSize, rectSize);
		Vector2 pos1 = new Vector2(viewWidth / 2, viewHeight / 2);
		Color color1 = new Color(0, 1, 0);
		rectangle1 = new Rectangle<ColorMaterial>(new Transform(pos1, scale1), new ColorMaterial(color1));

		Vector2 scale2 = new Vector2(rectSize, rectSize);
		Vector2 pos2 = new Vector2(pos1.getX(), pos1.getY() + (scale1.getY() / 2) + (scale2.getY() / 2) + margin);
		Color color2 = new Color(1, 0, 0);
		rectangle2 = new Rectangle<ColorMaterial>(new Transform(pos2, scale2), new ColorMaterial(color2));

		Vector2 scale3 = new Vector2(rectSize, rectSize);
		Vector2 pos3 = new Vector2(pos1.getX(), pos1.getY() - (scale1.getY() / 2) - (scale3.getY() / 2) - margin);
		Color color3 = new Color(0, 0, 1);
		rectangle3 = new Rectangle<ColorMaterial>(new Transform(pos3, scale3), new ColorMaterial(color3));
	}

}

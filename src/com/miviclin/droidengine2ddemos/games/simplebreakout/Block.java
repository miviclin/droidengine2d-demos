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
package com.miviclin.droidengine2ddemos.games.simplebreakout;

import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureHsvMaterial;
import com.miviclin.droidengine2d.util.Transform;

public class Block extends GameObject<TextureHsvMaterial> {

	private int points;
	private long lastCollisionTime;

	public Block(Transform transform, TextureHsvMaterial defaultMaterial, TextureHsvMaterial onCollisionMaterial,
			int points) {

		super(transform, defaultMaterial, onCollisionMaterial);
		this.points = points;
		this.lastCollisionTime = 0;
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(getDefaultMaterial(), getTransform());
	}

	public int getPoints() {
		return points;
	}

}

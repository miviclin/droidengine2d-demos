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

import com.miviclin.droidengine2d.graphics.material.TextureColorMaterial;
import com.miviclin.droidengine2d.util.Transform;

public class Block extends RectangularGameObject<TextureColorMaterial> {

	private static final long DELAY_BEFORE_DESTRUCTION_MS = 100;

	private int points;
	private long hitTime;
	private boolean destroyed;

	public Block(Transform transform, TextureColorMaterial defaultMaterial, TextureColorMaterial onCollisionMaterial,
			int points) {

		super(transform, defaultMaterial, onCollisionMaterial);
		this.points = points;
		this.hitTime = 0;
		this.destroyed = false;
	}

	@Override
	public void update(float delta) {
		if (hitTime == 0) {
			return;
		}

		long timeElapsedSinceCollision = System.currentTimeMillis() - hitTime;
		if (timeElapsedSinceCollision >= DELAY_BEFORE_DESTRUCTION_MS) {
			setDestroyed(true);
		}
	}

	public int getPoints() {
		return points;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	protected void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public void hit() {
		hitTime = System.currentTimeMillis();
		setCurrentMaterial(getOnCollisionMaterial());
	}

}

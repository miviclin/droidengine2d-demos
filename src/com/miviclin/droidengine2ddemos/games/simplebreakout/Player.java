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
import com.miviclin.droidengine2d.util.math.Vector2;

public class Player extends RectangularGameObject<TextureColorMaterial> {

	private static final long DELAY_BEFORE_DESTRUCTION_MS = 150;
	private static final long DELAY_INMUNE_AFTER_HIT_MS = 300;

	private int score;
	private long hitTime;

	public Player(Transform transform, TextureColorMaterial defaultMaterial, TextureColorMaterial onCollisionMaterial) {
		super(transform, defaultMaterial, onCollisionMaterial);
		this.score = 0;
		this.hitTime = 0;
	}

	@Override
	public void update(float delta) {
		long timeElapsedSinceCollision = System.currentTimeMillis() - hitTime;
		if (timeElapsedSinceCollision >= DELAY_BEFORE_DESTRUCTION_MS) {
			setCurrentMaterial(getDefaultMaterial());
		} else {
			setCurrentMaterial(getOnCollisionMaterial());
		}
	}

	public void move(float ax, float ay, float delta) {
		Vector2 position = getTransform().getPosition();
		float x = position.getX() + ax * delta;
		float y = position.getY() + ay * delta;
		position.set(x, y);
	}

	public void handleCollisionWithViewBounds(float viewWidth, float viewHeight) {
		float halfWidth = getScale().getX() / 2.0f;
		if (getLeft() < 0) {
			getPosition().set(halfWidth + 1, getPosition().getY());
		} else if (getRight() > viewWidth) {
			getPosition().set(viewWidth - halfWidth - 1, getPosition().getY());
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addPointsToScore(int points) {
		this.score += points;
	}

	public void hit() {
		hitTime = System.currentTimeMillis();
	}
	
	public boolean isInmune() {
		return (System.currentTimeMillis() - hitTime) < DELAY_INMUNE_AFTER_HIT_MS;
	}

}

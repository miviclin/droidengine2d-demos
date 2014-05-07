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
import com.miviclin.droidengine2d.graphics.material.TextureColorMaterial;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;

public class Ball extends GameObject<TextureColorMaterial> {

	private static final long DELAY_AFTER_HIT_MS = 150;
	private static final long DELAY_INMUNE_AFTER_HIT_MS = 70;

	private Vector2 velocity;
	private long hitEntityTime;
	private long hitTime;

	public Ball(Vector2 position, float radius, float speed,
			TextureColorMaterial defaultMaterial, TextureColorMaterial onCollisionMaterial) {

		super(new Transform(position, new Vector2(radius * 2, radius * 2)), defaultMaterial, onCollisionMaterial);

		int random = (int) (Math.random() * 2);
		int initialDirectionX = (random == 0) ? -1 : 1;
		int initialDirectionY = 1;
		this.velocity = new Vector2(initialDirectionX, initialDirectionY);
		this.velocity.setLength(speed);

		this.hitEntityTime = 0;
		this.hitTime = 0;
	}

	@Override
	public void update(float delta) {
		long timeElapsedSinceEntityHit = System.currentTimeMillis() - hitTime;
		if (timeElapsedSinceEntityHit >= DELAY_AFTER_HIT_MS) {
			setCurrentMaterial(getDefaultMaterial());
		} else {
			setCurrentMaterial(getOnCollisionMaterial());
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(getCurrentMaterial(), getTransform());
	}

	public float calculateNextPositionX(float delta) {
		return getTransform().getPosition().getX() + (velocity.getX() * delta);
	}

	public float calculateNextPositionY(float delta) {
		return getTransform().getPosition().getY() + (velocity.getY() * delta);
	}

	public void reverseDirectionX() {
		velocity.multiply(-1, 1);
	}

	public void reverseDirectionY() {
		velocity.multiply(1, -1);
	}

	public Vector2 getPosition() {
		return getTransform().getPosition();
	}

	public float getRadius() {
		return getTransform().getScale().getX() / 2.0f;
	}

	public void hitEntity() {
		hitEntityTime = System.currentTimeMillis();
		hitTime = System.currentTimeMillis();
	}

	public void hitWall() {
		hitTime = System.currentTimeMillis();
	}

	public boolean isInmune() {
		return (System.currentTimeMillis() - hitEntityTime) < DELAY_INMUNE_AFTER_HIT_MS;
	}

}

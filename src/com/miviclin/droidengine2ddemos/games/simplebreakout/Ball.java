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
import com.miviclin.droidengine2d.util.math.Vector2;

public class Ball extends GameObject<TextureHsvMaterial> {

	private Vector2 velocity;

	public Ball(Vector2 position, float radius, float speed,
			TextureHsvMaterial defaultMaterial, TextureHsvMaterial onCollisionMaterial) {

		super(new Transform(position, new Vector2(radius * 2, radius * 2)), defaultMaterial, onCollisionMaterial);

		int random = (int) (Math.random() * 2);
		int initialDirectionX = (random == 0) ? -1 : 1;
		int initialDirectionY = 1;
		velocity = new Vector2(initialDirectionX, initialDirectionY);
		velocity.setLength(speed);
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(getDefaultMaterial(), getTransform());
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

}

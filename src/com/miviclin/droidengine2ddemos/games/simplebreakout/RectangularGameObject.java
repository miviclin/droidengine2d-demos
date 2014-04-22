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

import com.miviclin.droidengine2d.graphics.material.Material;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;

public abstract class RectangularGameObject<M extends Material> extends GameObject<M> {

	public RectangularGameObject(Transform transform, M defaultMaterial, M onCollisionMaterial) {
		super(transform, defaultMaterial, onCollisionMaterial);
	}

	public float getLeft() {
		Vector2 position = getTransform().getPosition();
		Vector2 origin = getTransform().getOrigin();
		return position.getX() - origin.getX();
	}

	public float getRight() {
		Vector2 position = getTransform().getPosition();
		Vector2 origin = getTransform().getOrigin();
		Vector2 scale = getTransform().getScale();
		return position.getX() - origin.getX() + scale.getX();
	}

	public float getBottom() {
		Vector2 position = getTransform().getPosition();
		Vector2 origin = getTransform().getOrigin();
		return position.getY() - origin.getY();
	}

	public float getTop() {
		Vector2 position = getTransform().getPosition();
		Vector2 origin = getTransform().getOrigin();
		Vector2 scale = getTransform().getScale();
		return position.getY() - origin.getY() + scale.getY();
	}

	public Vector2 getPosition() {
		return getTransform().getPosition();
	}

	public Vector2 getScale() {
		return getTransform().getScale();
	}

}

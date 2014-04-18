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
package com.miviclin.droidengine2ddemos.util;

import com.miviclin.droidengine2d.graphics.material.Material;
import com.miviclin.droidengine2d.util.Transform;

public class Rectangle<M extends Material> {

	private Transform transform;
	private M material;

	public Rectangle(Transform transform, M material) {
		super();
		this.transform = transform;
		this.material = material;
	}

	public Transform getTransform() {
		return transform;
	}

	public M getMaterial() {
		return material;
	}

	public boolean contains(float x, float y) {
		float width = transform.getScale().getX();
		float height = transform.getScale().getY();
		float halfWidth = width / 2;
		float halfHeight = height / 2;
		float left = transform.getPosition().getX() - halfWidth;
		float bottom = transform.getPosition().getY() - halfHeight;
		float right = left + width;
		float top = bottom + height;
		boolean containsX = (left <= x) && (x <= right);
		boolean containsY = (bottom <= y) && (y <= top);
		return (containsX && containsY);
	}

	public boolean intersects(Rectangle<?> rectangle) {
		float width = transform.getScale().getX();
		float height = transform.getScale().getY();
		float halfWidth = width / 2;
		float halfHeight = height / 2;
		float left = transform.getPosition().getX() - halfWidth;
		float bottom = transform.getPosition().getY() - halfHeight;
		float right = left + width;
		float top = bottom + height;

		float otherWidth = transform.getScale().getX();
		float otherHeight = transform.getScale().getY();
		float otherHalfWidth = otherWidth / 2;
		float otherHalfHeight = otherHeight / 2;
		float otherLeft = transform.getPosition().getX() - otherHalfWidth;
		float otherBottom = transform.getPosition().getY() - otherHalfHeight;
		float otherRight = otherLeft + otherWidth;
		float otherTop = otherBottom + otherHeight;

		boolean intersectsX = ((otherLeft <= right) && (otherLeft >= left)) ||
				((otherRight >= left) && (otherRight <= right));

		boolean intersectsY = ((otherBottom <= top) && (otherBottom >= bottom)) ||
				((otherTop >= bottom) && (otherTop <= top));

		return (intersectsX && intersectsY);
	}
}

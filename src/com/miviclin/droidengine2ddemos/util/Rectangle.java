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
}

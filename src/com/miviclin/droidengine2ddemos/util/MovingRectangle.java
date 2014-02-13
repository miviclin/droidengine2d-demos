package com.miviclin.droidengine2ddemos.util;

import com.miviclin.droidengine2d.graphics.material.Material;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;

public class MovingRectangle<M extends Material> extends Rectangle<M> {

	public MovingRectangle(Transform transform, M material) {
		super(transform, material);
	}

	public void move(float ax, float ay, float delta) {
		Vector2 position = getTransform().getPosition();
		float x = position.getX() + ax * delta;
		float y = position.getY() + ay * delta;
		position.set(x, y);
	}

}

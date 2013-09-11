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
}

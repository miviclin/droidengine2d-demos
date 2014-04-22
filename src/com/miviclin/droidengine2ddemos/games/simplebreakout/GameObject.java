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
import com.miviclin.droidengine2d.graphics.material.Material;
import com.miviclin.droidengine2d.util.Transform;

public abstract class GameObject<M extends Material> {

	private Transform transform;
	private M defaultMaterial;
	private M onCollisionMaterial;
	private M currentMaterial;

	public GameObject(Transform transform, M defaultMaterial, M onCollisionMaterial) {
		this.transform = transform;
		this.defaultMaterial = defaultMaterial;
		this.onCollisionMaterial = onCollisionMaterial;
		this.currentMaterial = defaultMaterial;
	}

	public abstract void update(float delta);

	public abstract void draw(Graphics g);

	protected Transform getTransform() {
		return transform;
	}

	protected M getCurrentMaterial() {
		return currentMaterial;
	}

	protected void setCurrentMaterial(M currentMaterial) {
		this.currentMaterial = currentMaterial;
	}

	protected M getDefaultMaterial() {
		return defaultMaterial;
	}

	protected M getOnCollisionMaterial() {
		return onCollisionMaterial;
	}

}

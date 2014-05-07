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

public class Logo {

	private Transform transform;
	private TextureColorMaterial baseMaterial;
	private TextureColorMaterial overlayedMaterial;
	private float alphaIncrement;

	public Logo(Transform transform, TextureColorMaterial baseMaterial,
			TextureColorMaterial overlayedMaterial) {

		this.transform = transform;
		this.baseMaterial = baseMaterial;
		this.overlayedMaterial = overlayedMaterial;
		this.alphaIncrement = 0.01f;
	}

	public void update(float delta) {
		float alpha = overlayedMaterial.getColor().getA() + alphaIncrement;
		if (alpha < 0) {
			alpha = 0.0f;
			alphaIncrement *= -1;
		} else if (alpha > 1) {
			alpha = 1.0f;
			alphaIncrement *= -1;
		}
		overlayedMaterial.getColor().setA(alpha);
	}

	public void draw(Graphics g) {
		g.drawRect(baseMaterial, transform);
		g.drawRect(overlayedMaterial, transform);
	}

	public Vector2 getPosition() {
		return transform.getPosition();
	}

	public Vector2 getScale() {
		return transform.getScale();
	}

}

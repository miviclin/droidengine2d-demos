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
package com.miviclin.droidengine2ddemos.material.blendingoptions;

import android.opengl.GLES20;

public enum BlendingEquation {
	GL_FUNC_ADD(GLES20.GL_FUNC_ADD),
	GL_FUNC_SUBTRACT(GLES20.GL_FUNC_SUBTRACT),
	GL_FUNC_REVERSE_SUBTRACT(GLES20.GL_FUNC_REVERSE_SUBTRACT);

	private int value;

	private BlendingEquation(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

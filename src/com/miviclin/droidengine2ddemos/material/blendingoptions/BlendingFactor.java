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

public enum BlendingFactor {
	GL_ZERO(GLES20.GL_ZERO),
	GL_ONE(GLES20.GL_ONE),
	GL_SRC_COLOR(GLES20.GL_SRC_COLOR),
	GL_ONE_MINUS_SRC_COLOR(GLES20.GL_ONE_MINUS_SRC_COLOR),
	GL_DST_COLOR(GLES20.GL_DST_COLOR),
	GL_ONE_MINUS_DST_COLOR(GLES20.GL_ONE_MINUS_DST_COLOR),
	GL_SRC_ALPHA(GLES20.GL_SRC_ALPHA),
	GL_ONE_MINUS_SRC_ALPHA(GLES20.GL_ONE_MINUS_SRC_ALPHA),
	GL_DST_ALPHA(GLES20.GL_DST_ALPHA),
	GL_ONE_MINUS_DST_ALPHA(GLES20.GL_ONE_MINUS_DST_ALPHA),
	GL_CONSTANT_COLOR(GLES20.GL_CONSTANT_COLOR),
	GL_ONE_MINUS_CONSTANT_COLOR(GLES20.GL_ONE_MINUS_CONSTANT_COLOR),
	GL_CONSTANT_ALPHA(GLES20.GL_CONSTANT_ALPHA),
	GL_ONE_MINUS_CONSTANT_ALPHA(GLES20.GL_ONE_MINUS_CONSTANT_ALPHA),
	GL_SRC_ALPHA_SATURATE(GLES20.GL_SRC_ALPHA_SATURATE);

	private int value;

	private BlendingFactor(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

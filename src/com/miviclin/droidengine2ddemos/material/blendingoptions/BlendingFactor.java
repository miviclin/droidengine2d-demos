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
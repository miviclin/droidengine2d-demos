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

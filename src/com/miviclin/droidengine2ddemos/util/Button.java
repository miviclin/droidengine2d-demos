package com.miviclin.droidengine2ddemos.util;

import java.util.ArrayList;

import android.view.MotionEvent;

import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;

public class Button extends Rectangle<TextureMaterial> {

	public enum State {
		PRESSED,
		RELEASED;
	}

	private ArrayList<OnClickListener> onClickListeners;
	private State state;
	private TextureRegion buttonPressedTexture;
	private TextureRegion buttonReleasedTexture;

	public Button(Transform transform, TextureRegion buttonPressedTexture, TextureRegion buttonReleasedTexture) {
		super(transform, new TextureMaterial(buttonReleasedTexture));
		this.onClickListeners = new ArrayList<Button.OnClickListener>();
		this.state = State.RELEASED;
		this.buttonPressedTexture = buttonPressedTexture;
		this.buttonReleasedTexture = buttonReleasedTexture;
	}

	public void addOnClickListener(OnClickListener listener) {
		onClickListeners.add(listener);
	}

	public void removeOnClickListener(OnClickListener listener) {
		onClickListeners.remove(listener);
	}

	public void processMotionEvent(MotionEvent motionEvent, float viewWidth, float viewHeight) {
		float x = motionEvent.getX();
		float y = viewHeight - motionEvent.getY();
		if (!contains(x, y)) {
			release(false);
			return;
		}
		if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
			press();
		} else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
			release(true);
		}
	}

	private void press() {
		if (state == State.RELEASED) {
			state = State.PRESSED;
			getMaterial().setTextureRegion(buttonPressedTexture);
			notifyButtonPressed();
		}
	}

	private void notifyButtonPressed() {
		for (int i = 0; i < onClickListeners.size(); i++) {
			onClickListeners.get(i).onButtonPressed(this);
		}
	}

	private void release(boolean notifyListeners) {
		if (state == State.PRESSED) {
			state = State.RELEASED;
			getMaterial().setTextureRegion(buttonReleasedTexture);
			if (notifyListeners) {
				notifyButtonReleased();
			}
		}
	}

	private void notifyButtonReleased() {
		for (int i = 0; i < onClickListeners.size(); i++) {
			onClickListeners.get(i).onButtonReleased(this);
		}
	}

	public State getState() {
		return state;
	}

	public interface OnClickListener {

		public void onButtonPressed(Button button);

		public void onButtonReleased(Button button);

	}

}

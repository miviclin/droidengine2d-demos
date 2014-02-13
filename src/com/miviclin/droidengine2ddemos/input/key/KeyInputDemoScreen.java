package com.miviclin.droidengine2ddemos.input.key;

import android.view.KeyEvent;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.input.KeyEventInfo;
import com.miviclin.droidengine2d.input.KeyEventProcessor;
import com.miviclin.droidengine2d.screen.Screen;

public class KeyInputDemoScreen extends Screen {

	private Color backgroundColor;

	public KeyInputDemoScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void draw(Graphics g) {
		g.setBackgroundColor(backgroundColor);
	}

	@Override
	public void onRegister() {
		final Color backgroundColorDefault = new Color(0.5f, 0.5f, 0.5f);
		final Color onKeyDownBackgroundColor = new Color(1.0f, 0.0f, 0.0f);

		backgroundColor = new Color(backgroundColorDefault);

		getInputManager().getKeyProcessor().setKeyEventProcessor(new KeyEventProcessor() {

			@Override
			public void processKeyEvent(KeyEventInfo event) {
				if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
					getGame().getActivity().finish();
					return;
				}
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					backgroundColor.set(onKeyDownBackgroundColor);
				} else if (event.getAction() == KeyEvent.ACTION_UP) {
					backgroundColor.set(backgroundColorDefault);
				}
			}
		});
	}

	@Override
	public void onActivation() {
	}

	@Override
	public void onDeactivation() {
	}

	@Override
	public void onPause() {
	}

	@Override
	public void onResume() {
	}

	@Override
	public void onDispose() {
	}

}

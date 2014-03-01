package com.miviclin.droidengine2ddemos.gamestate;

import java.util.ArrayList;

import android.view.MotionEvent;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.input.MotionEventProcessor;
import com.miviclin.droidengine2ddemos.util.Button;

public class GameStateBase extends GameStateAdapter {

	private ArrayList<Button> buttons;
	private Color backgroundColor;

	public GameStateBase(Game game) {
		super(game);
		this.buttons = new ArrayList<Button>();
		this.backgroundColor = new Color(0.0f, 0.0f, 0.0f);
	}

	@Override
	public void onRegister() {
		setupMotionEventProcessor();
	}

	protected void addButton(Button button) {
		buttons.add(button);
	}

	protected Color getBackgroundColor() {
		return backgroundColor;
	}

	protected void setupMotionEventProcessor() {
		getGameStateInputManager().getTouchProcessor().setMotionEventProcessor(new MotionEventProcessor() {

			@Override
			public void processMotionEvent(MotionEvent motionEvent) {
				handleButtonClick(motionEvent);
			}
		});
	}

	protected void handleButtonClick(MotionEvent motionEvent) {
		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();
		for (int i = 0; i < buttons.size(); i++) {
			Button button = buttons.get(i);
			button.processMotionEvent(motionEvent, viewWidth, viewHeight);
		}
	}

}
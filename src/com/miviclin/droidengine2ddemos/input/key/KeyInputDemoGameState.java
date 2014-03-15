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
package com.miviclin.droidengine2ddemos.input.key;

import android.view.KeyEvent;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.input.KeyEventInfo;
import com.miviclin.droidengine2d.input.KeyEventProcessor;

public class KeyInputDemoGameState extends GameStateAdapter {

	private Color backgroundColor;

	public KeyInputDemoGameState(Game game) {
		super(game);
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

		getGameStateInputManager().getKeyProcessor().setKeyEventProcessor(new KeyEventProcessor() {

			@Override
			public void processKeyEvent(KeyEventInfo event) {
				if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
					getActivity().finish();
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

}

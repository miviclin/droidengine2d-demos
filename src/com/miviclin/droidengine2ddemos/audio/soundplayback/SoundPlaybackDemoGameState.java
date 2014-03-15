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
package com.miviclin.droidengine2ddemos.audio.soundplayback;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.audio.SoundManager;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.gamestate.GameStateBase;
import com.miviclin.droidengine2ddemos.util.Button;

public class SoundPlaybackDemoGameState extends GameStateBase {

	private SoundManager soundManager;
	private Button btnExplosionSound;
	private Button btnJumpSound;
	private Button btnCoinSound;
	private Button btnPowerUpSound;

	public SoundPlaybackDemoGameState(Game game) {
		super(game);
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(btnExplosionSound.getMaterial(), btnExplosionSound.getTransform());
		g.drawRect(btnJumpSound.getMaterial(), btnJumpSound.getTransform());
		g.drawRect(btnCoinSound.getMaterial(), btnCoinSound.getTransform());
		g.drawRect(btnPowerUpSound.getMaterial(), btnPowerUpSound.getTransform());
	}

	@Override
	public void onRegister() {
		super.onRegister();

		int maxStreams = 10;
		int initialCapacity = 10;
		soundManager = new SoundManager(maxStreams, initialCapacity);
		soundManager.loadSound(getActivity(), "audio/drums-track.wav");

		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/buttons-atlas.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();
		float buttonX = viewWidth / 2;
		float separationY = viewHeight / 5.0f;

		TextureRegion trExplosionBtnNormal = textureAtlas.getTextureRegion("btn-large-explosion-normal.png");
		TextureRegion trExplosionBtnSelected = textureAtlas.getTextureRegion("btn-large-explosion-selected.png");

		float ratio = trExplosionBtnNormal.getWidth() / trExplosionBtnNormal.getHeight();
		float margin = 20.0f;
		float buttonWidth = viewWidth - margin;
		float buttonHeight = buttonWidth / ratio;
		Vector2 buttonScale = new Vector2(buttonWidth, buttonHeight);

		float explosionBtnY = viewHeight - separationY;
		Vector2 explosionBtnPosition = new Vector2(buttonX, explosionBtnY);
		String explosionSoundPath = "audio/explosion.wav";
		soundManager.loadSound(getActivity(), explosionSoundPath);
		btnExplosionSound = createButton(explosionBtnPosition, buttonScale, trExplosionBtnSelected,
				trExplosionBtnNormal, explosionSoundPath);

		TextureRegion trJumpBtnNormal = textureAtlas.getTextureRegion("btn-large-jump-normal.png");
		TextureRegion trJumpBtnSelected = textureAtlas.getTextureRegion("btn-large-jump-selected.png");
		float jumpBtnY = explosionBtnY - separationY;
		Vector2 jumpBtnPosition = new Vector2(buttonX, jumpBtnY);
		String jumpSoundPath = "audio/jump.wav";
		soundManager.loadSound(getActivity(), jumpSoundPath);
		btnJumpSound = createButton(jumpBtnPosition, buttonScale, trJumpBtnSelected, trJumpBtnNormal, jumpSoundPath);

		TextureRegion trCoinBtnNormal = textureAtlas.getTextureRegion("btn-large-coin-normal.png");
		TextureRegion trCoinBtnSelected = textureAtlas.getTextureRegion("btn-large-coin-selected.png");
		float coinBtnY = jumpBtnY - separationY;
		Vector2 coinBtnPosition = new Vector2(buttonX, coinBtnY);
		String coinSoundPath = "audio/pickup-coin.wav";
		soundManager.loadSound(getActivity(), coinSoundPath);
		btnCoinSound = createButton(coinBtnPosition, buttonScale, trCoinBtnSelected, trCoinBtnNormal, coinSoundPath);

		TextureRegion trPowerUpBtnNormal = textureAtlas.getTextureRegion("btn-large-power-up-normal.png");
		TextureRegion trPowerUpBtnSelected = textureAtlas.getTextureRegion("btn-large-power-up-selected.png");
		float powerUpBtnY = coinBtnY - separationY;
		Vector2 powerUpBtnPosition = new Vector2(buttonX, powerUpBtnY);
		String powerUpSoundPath = "audio/powerup.wav";
		soundManager.loadSound(getActivity(), powerUpSoundPath);
		btnPowerUpSound = createButton(powerUpBtnPosition, buttonScale, trPowerUpBtnSelected, trPowerUpBtnNormal,
				powerUpSoundPath);
	}

	@Override
	public void onDispose() {
		soundManager.release();
	}

	private Button createButton(Vector2 position, Vector2 scale, TextureRegion buttonPressedTextureRegion,
			TextureRegion buttonReleasedTextureRegion, final String soundPath) {

		Transform transform = new Transform(position, scale);
		Button button = new Button(transform, buttonPressedTextureRegion, buttonReleasedTextureRegion);
		button.addOnClickListener(new Button.OnClickListener() {

			@Override
			public void onButtonPressed(Button button) {
			}

			@Override
			public void onButtonReleased(Button button) {
				playSound(soundPath);
			}
		});
		addButton(button);
		return button;
	}

	private void playSound(String path) {
		soundManager.playSound(path);
	}

}

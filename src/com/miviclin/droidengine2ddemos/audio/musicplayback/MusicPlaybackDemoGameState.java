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
package com.miviclin.droidengine2ddemos.audio.musicplayback;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.audio.MusicPlayer;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.gamestate.GameStateBase;
import com.miviclin.droidengine2ddemos.util.Button;

public class MusicPlaybackDemoGameState extends GameStateBase {

	private enum MusicAction {
		PLAY,
		PAUSE,
		TURN_VOLUME_UP,
		TURN_VOLUME_DOWN;
	}

	private static final float VOLUME_INCREMENT = 0.1f;

	private MusicPlayer musicPlayer;

	private Button btnPlay;
	private Button btnPause;
	private Button btnVolumeUp;
	private Button btnVolumeDown;

	public MusicPlaybackDemoGameState(Game game) {
		super(game);
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(btnPlay.getMaterial(), btnPlay.getTransform());
		g.drawRect(btnPause.getMaterial(), btnPause.getTransform());
		g.drawRect(btnVolumeUp.getMaterial(), btnVolumeUp.getTransform());
		g.drawRect(btnVolumeDown.getMaterial(), btnVolumeDown.getTransform());
	}

	@Override
	public void onRegister() {
		super.onRegister();

		musicPlayer = new MusicPlayer();
		musicPlayer.loadMusicFromAssets(getActivity(), "audio/drums-track.wav");

		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/buttons-atlas.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();
		float separationX = viewWidth / 3.0f;
		float separationY = viewHeight / 3.0f;
		float rectSize = viewWidth / 3.5f;
		Vector2 buttonScale = new Vector2(rectSize, rectSize);

		TextureRegion trPlayNormal = textureAtlas.getTextureRegion("btn-music-play-normal.png");
		TextureRegion trPlaySelected = textureAtlas.getTextureRegion("btn-music-play-selected.png");
		float playButtonX = separationX;
		float playButtonY = viewHeight - separationY;
		Vector2 playButtonPosition = new Vector2(playButtonX, playButtonY);
		btnPlay = createButton(playButtonPosition, buttonScale, trPlaySelected, trPlayNormal, MusicAction.PLAY);

		TextureRegion trPauseNormal = textureAtlas.getTextureRegion("btn-music-pause-normal.png");
		TextureRegion trPauseSelected = textureAtlas.getTextureRegion("btn-music-pause-selected.png");
		float pauseButtonX = viewWidth - separationX;
		float pauseButtonY = viewHeight - separationY;
		Vector2 pauseButtonPosition = new Vector2(pauseButtonX, pauseButtonY);
		btnPause = createButton(pauseButtonPosition, buttonScale, trPauseSelected, trPauseNormal, MusicAction.PAUSE);

		TextureRegion trVolumeUpNormal = textureAtlas.getTextureRegion("btn-music-volume-up-normal.png");
		TextureRegion trVolumeUpSelected = textureAtlas.getTextureRegion("btn-music-volume-up-selected.png");
		float volumeUpButtonX = separationX;
		float volumeUpButtonY = separationY;
		Vector2 volumeUpButtonPosition = new Vector2(volumeUpButtonX, volumeUpButtonY);
		btnVolumeUp = createButton(volumeUpButtonPosition, buttonScale, trVolumeUpSelected, trVolumeUpNormal,
				MusicAction.TURN_VOLUME_UP);

		TextureRegion trVolumeDownNormal = textureAtlas.getTextureRegion("btn-music-volume-down-normal.png");
		TextureRegion trVolumeDownSelected = textureAtlas.getTextureRegion("btn-music-volume-down-selected.png");
		float volumeDownButtonX = viewWidth - separationX;
		float volumeDownButtonY = separationY;
		Vector2 volumeDownButtonPosition = new Vector2(volumeDownButtonX, volumeDownButtonY);
		btnVolumeDown = createButton(volumeDownButtonPosition, buttonScale, trVolumeDownSelected, trVolumeDownNormal,
				MusicAction.TURN_VOLUME_DOWN);
	}

	@Override
	public void onPause() {
		musicPlayer.pause();
	}

	@Override
	public void onResume() {
		musicPlayer.play();
	}

	@Override
	public void onDispose() {
		musicPlayer.release();
	}

	private Button createButton(Vector2 position, Vector2 scale, TextureRegion buttonPressedTextureRegion,
			TextureRegion buttonReleasedTextureRegion, final MusicAction musicAction) {

		Transform transform = new Transform(position, scale);
		Button button = new Button(transform, buttonPressedTextureRegion, buttonReleasedTextureRegion);
		button.addOnClickListener(new Button.OnClickListener() {

			@Override
			public void onButtonPressed(Button button) {
			}

			@Override
			public void onButtonReleased(Button button) {
				doMusicAction(musicAction);
			}
		});
		addButton(button);
		return button;
	}

	private void doMusicAction(MusicAction musicAction) {
		float volume;
		switch (musicAction) {
		case PLAY:
			musicPlayer.play();
			break;
		case PAUSE:
			musicPlayer.pause();
			break;
		case TURN_VOLUME_UP:
			volume = Math.min(musicPlayer.getVolume() + VOLUME_INCREMENT, MusicPlayer.MAX_VOLUME);
			musicPlayer.setVolume(volume);
			break;
		case TURN_VOLUME_DOWN:
			volume = Math.max(musicPlayer.getVolume() - VOLUME_INCREMENT, MusicPlayer.MIN_VOLUME);
			musicPlayer.setVolume(volume);
			break;
		default:
			break;
		}
	}

}

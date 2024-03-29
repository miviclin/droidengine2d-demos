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
package com.miviclin.droidengine2ddemos.games.simplebreakout;

import java.util.ArrayList;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.audio.MusicPlayer;
import com.miviclin.droidengine2d.audio.SoundManager;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureColorMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.input.sensor.Accelerometer;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;

public class LevelGameState extends GameStateAdapter {

	private static final String SOUND_HIT_BLOCK = "audio/hit-block.wav";
	private static final String SOUND_HIT_SIDE = "audio/hit-side.wav";
	private static final String BACKGROUND_MUSIC = "audio/drums-track.wav";

	private SoundManager soundManager;
	private MusicPlayer musicPlayer;
	private ArrayList<Block> blocks;
	private Player player;
	private Ball ball;

	public LevelGameState(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {
		removeDestroyedBlocks();
		updateBlocks(delta);
		updatePlayer(delta);
		updateBall(delta);
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).draw(g);
		}
		player.draw(g);
		ball.draw(g);
	}

	@Override
	public void onRegister() {
		int maxStreams = 10;
		int initialCapacity = 10;
		soundManager = new SoundManager(maxStreams, initialCapacity);
		soundManager.loadSound(getActivity(), SOUND_HIT_BLOCK);
		soundManager.loadSound(getActivity(), SOUND_HIT_SIDE);
		
		musicPlayer = new MusicPlayer();
		musicPlayer.loadMusicFromAssets(getActivity(), BACKGROUND_MUSIC);

		Accelerometer accelerometer = getGameStateInputManager().getAccelerometer();
		accelerometer.getValuesListener().useCoordinateSystemOfDisplay();
		accelerometer.startListening();
	}

	@Override
	public void onActivation() {
		blocks = createBlocks(5);
		player = createPlayer();
		ball = createBall(player);
		
		if (!musicPlayer.isPlaying()) {
			musicPlayer.play();
		}
	}
	
	@Override
	public void onDeactivation() {
		if (musicPlayer.isPlaying()) {
			musicPlayer.pause();
		}
	}

	@Override
	public void onPause() {
		Accelerometer accelerometer = getGameStateInputManager().getAccelerometer();
		accelerometer.stopListening();
		
		if (musicPlayer.isPlaying()) {
			musicPlayer.pause();
		}
	}

	@Override
	public void onResume() {
		Accelerometer accelerometer = getGameStateInputManager().getAccelerometer();
		accelerometer.startListening();
		
		if (!musicPlayer.isPlaying()) {
			musicPlayer.play();
		}
	}

	@Override
	public void onDispose() {
		soundManager.release();
		musicPlayer.release();
	}

	private ArrayList<Block> createBlocks(int numRows) {
		ArrayList<Block> blocks = new ArrayList<Block>();

		TextureRegion blockTextureRegionDefault = getTextureManager().getTextureRegion("btn-large-normal.png");
		TextureRegion blockTextureRegionCollided = getTextureManager().getTextureRegion("btn-large-selected.png");

		final float viewWidth = getCamera().getViewportWidth();
		final float viewHeight = getCamera().getViewportHeight();

		final float blockRatio = blockTextureRegionDefault.getWidth() / blockTextureRegionDefault.getHeight();
		final float blockWidth = viewWidth;
		final float blockHeight = (blockWidth / blockRatio) / 3.0f;

		float blockX = 0;

		for (int rowIndex = 1; rowIndex <= numRows; rowIndex++) {
			float blockY = viewHeight - (rowIndex * blockHeight);

			Block block = createBlock(blockX, blockY, blockWidth, blockHeight,
					blockTextureRegionDefault, blockTextureRegionCollided, 10);

			blocks.add(block);
		}

		return blocks;
	}

	private Block createBlock(float positionX, float positionY, float blockWidth, float blockHeight,
			TextureRegion defaultTextureregion, TextureRegion onCollisionTextureRegion, int points) {

		Vector2 origin = new Vector2(0, 0);
		Vector2 scale = new Vector2(blockWidth, blockHeight);
		Vector2 position = new Vector2(positionX, positionY);
		Transform transform = new Transform(position, scale, origin, 0.0f);

		Color defaultColor = new Color(1, 0.3f, 1);
		TextureColorMaterial defaultMaterial = new TextureColorMaterial(defaultTextureregion, defaultColor);

		Color onCollisionColor = new Color(defaultColor);
		TextureColorMaterial onCollisionMaterial = new TextureColorMaterial(onCollisionTextureRegion, onCollisionColor);

		return new Block(transform, defaultMaterial, onCollisionMaterial, points);
	}

	private Player createPlayer() {
		TextureRegion defaultTextureregion = getTextureManager().getTextureRegion("btn-large-normal.png");
		TextureRegion onCollisionTextureRegion = getTextureManager().getTextureRegion("btn-large-selected.png");

		final float viewWidth = getCamera().getViewportWidth();

		final float playerRatio = defaultTextureregion.getWidth() / defaultTextureregion.getHeight();
		final float playerWidth = viewWidth / 3.0f;
		final float playerHeight = playerWidth / playerRatio;

		final float bottomMargin = 10.0f;

		Vector2 scale = new Vector2(playerWidth, playerHeight);
		Vector2 playerPosition = new Vector2(viewWidth / 2.0f, playerHeight + bottomMargin);
		Transform playerTransform = new Transform(playerPosition, scale);

		Color defaultColor = new Color(0.3f, 1, 1);
		TextureColorMaterial defaultMaterial = new TextureColorMaterial(defaultTextureregion, defaultColor);

		Color onCollisionColor = new Color(defaultColor);
		TextureColorMaterial onCollisionMaterial = new TextureColorMaterial(onCollisionTextureRegion, onCollisionColor);

		return new Player(playerTransform, defaultMaterial, onCollisionMaterial);
	}

	private Ball createBall(Player player) {
		final float playerPositionX = player.getTransform().getPosition().getX();
		final float playerPositionY = player.getTransform().getPosition().getY();
		final float playerScaleX = player.getTransform().getScale().getX();
		final float playerScaleY = player.getTransform().getScale().getY();

		final float ballRadius = playerScaleX / 8.0f;
		final float ballSpeed = 0.5f;

		final float bottomMargin = 5.0f;

		float positionX = playerPositionX;
		float positionY = playerPositionY + (playerScaleY / 2.0f) + bottomMargin + ballRadius;
		Vector2 ballPosition = new Vector2(positionX, positionY);

		Color defaultColor = new Color(1, 1, 0.3f);
		TextureRegion defaultTextureregion = getTextureManager().getTextureRegion("btn-small-circle-normal.png");
		TextureColorMaterial defaultMaterial = new TextureColorMaterial(defaultTextureregion, defaultColor);

		Color onCollisionColor = new Color(defaultColor);
		TextureRegion onCollisionTextureRegion = getTextureManager().getTextureRegion("btn-small-circle-selected.png");
		TextureColorMaterial onCollisionMaterial = new TextureColorMaterial(onCollisionTextureRegion, onCollisionColor);

		return new Ball(ballPosition, ballRadius, ballSpeed, defaultMaterial, onCollisionMaterial);
	}

	private void updatePlayer(float delta) {
		player.update(delta);

		final float accelerationReduction = 0.2f;
		final float maxAccelerationAbs = 0.5f;

		Accelerometer accelerometer = getGameStateInputManager().getAccelerometer();
		float accelerometerX = accelerometer.getValuesListener().getX();
		int accelerometerXSign = (accelerometerX < 0) ? -1 : 1;
		float ax = accelerometerXSign * Math.min(Math.abs(accelerometerX * accelerationReduction), maxAccelerationAbs);
		player.move(-ax, 0, delta);

		final float viewWidth = getCamera().getViewportWidth();
		final float viewHeight = getCamera().getViewportHeight();
		player.handleCollisionWithViewBounds(viewWidth, viewHeight);
	}

	private void updateBall(float delta) {
		ball.update(delta);

		float newBallPositionX = ball.calculateNextPositionX(delta);
		float newBallPositionY = ball.calculateNextPositionY(delta);

		checkCollisionWithSidesOfBoard(newBallPositionX, newBallPositionY);
		checkCollisionWithBlockAtBottom(newBallPositionX, newBallPositionY);
		checkCollisionWithPlayer(newBallPositionX, newBallPositionY);
		checkCollisionWithTopOfBoard(newBallPositionX, newBallPositionY);
		checkCollisionWithBottomOfBoard(newBallPositionX, newBallPositionY);
	}

	private void checkCollisionWithSidesOfBoard(float newBallPositionX, float newBallPositionY) {
		final float viewWidth = getCamera().getViewportWidth();

		if (newBallPositionX + ball.getRadius() > viewWidth) {
			newBallPositionX = viewWidth - ball.getRadius();
			ball.reverseDirectionX();
			ball.hitWall();
			soundManager.playSound(SOUND_HIT_SIDE);
		} else if (newBallPositionX - ball.getRadius() < 0) {
			newBallPositionX = ball.getRadius();
			ball.reverseDirectionX();
			ball.hitWall();
			soundManager.playSound(SOUND_HIT_SIDE);
		}

		ball.getPosition().set(newBallPositionX, newBallPositionY);
	}

	private void checkCollisionWithBlockAtBottom(float newBallPositionX, float newBallPositionY) {
		if ((blocks.size() > 0) && !ball.isInmune()) {
			Block blockAtBottom = blocks.get(blocks.size() - 1);
			if (newBallPositionY + ball.getRadius() > blockAtBottom.getBottom()) {
				newBallPositionY = blockAtBottom.getBottom() - ball.getRadius();
				ball.reverseDirectionY();
				ball.hitEntity();
				blockAtBottom.hit();
				soundManager.playSound(SOUND_HIT_BLOCK);
			}
		}

		ball.getPosition().set(newBallPositionX, newBallPositionY);
	}

	private void checkCollisionWithPlayer(float newBallPositionX, float newBallPositionY) {
		float overlapY = player.getTop() - (newBallPositionY - ball.getRadius());

		if ((overlapY > 0.0f) && !ball.isInmune() && !player.isInmune()) {
			float overlapLeftX = (newBallPositionX + ball.getRadius()) - player.getLeft();
			float overlapRightX = player.getRight() - (newBallPositionX - ball.getRadius());

			if ((overlapLeftX > 0.0f) && (overlapRightX > 0.0f)) {
				float minOverlapX = Math.min(overlapLeftX, overlapRightX);
				boolean compensateX = Math.min(minOverlapX, overlapY) == minOverlapX;
				if (compensateX) {
					if (minOverlapX == overlapLeftX) {
						newBallPositionX = player.getLeft() - ball.getRadius();
						ball.reverseDirectionX();
					} else {
						newBallPositionX = player.getRight() + ball.getRadius();
						ball.reverseDirectionX();
					}
				} else {
					newBallPositionY = player.getTop() + ball.getRadius();
					ball.reverseDirectionY();
				}
				ball.hitEntity();
				player.hit();
				soundManager.playSound(SOUND_HIT_BLOCK);
			}
		}

		ball.getPosition().set(newBallPositionX, newBallPositionY);
	}

	private void checkCollisionWithTopOfBoard(float newBallPositionX, float newBallPositionY) {
		final float viewHeight = getCamera().getViewportHeight();
		if (newBallPositionY + ball.getRadius() > viewHeight) {
			newBallPositionY = viewHeight - ball.getRadius();
			ball.reverseDirectionY();
			ball.hitWall();
			soundManager.playSound(SOUND_HIT_SIDE);
		}

		ball.getPosition().set(newBallPositionX, newBallPositionY);
	}

	private void checkCollisionWithBottomOfBoard(float newBallPositionX, float newBallPositionY) {
		if (newBallPositionY + ball.getRadius() < 0) {
			getGameStateManager().pushActiveGameState(SimpleBreakoutGameStates.GAME_LOST);
		}

		ball.getPosition().set(newBallPositionX, newBallPositionY);
	}

	private void removeDestroyedBlocks() {
		for (int i = blocks.size() - 1; i >= 0; i--) {
			if (blocks.get(i).isDestroyed()) {
				blocks.remove(i);
			}
		}
		if (blocks.size() == 0) {
			getGameStateManager().pushActiveGameState(SimpleBreakoutGameStates.GAME_WON);
		}
	}

	private void updateBlocks(float delta) {
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).update(delta);
		}
	}
}

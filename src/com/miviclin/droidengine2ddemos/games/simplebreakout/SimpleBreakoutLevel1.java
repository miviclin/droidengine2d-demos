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
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureHsvMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;

public class SimpleBreakoutLevel1 extends GameStateAdapter {

	private ArrayList<Block> blocks;
	private Player player;
	private Ball ball;

	public SimpleBreakoutLevel1(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {
		updateBallPosition(delta);
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
		blocks = createBlocks(5);
		player = createPlayer();
		ball = createBall(player);
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
			TextureRegion blockTextureRegionDefault, TextureRegion blockTextureRegionCollided, int points) {

		Vector2 origin = new Vector2(0, 0);
		Vector2 scale = new Vector2(blockWidth, blockHeight);
		Vector2 position = new Vector2(positionX, positionY);
		Transform transform = new Transform(position, scale, origin, 0.0f);

		TextureHsvMaterial defaultMaterial = new TextureHsvMaterial(blockTextureRegionDefault);
		TextureHsvMaterial onCollisionMaterial = new TextureHsvMaterial(blockTextureRegionCollided);

		return new Block(transform, defaultMaterial, onCollisionMaterial, points);
	}

	private Player createPlayer() {
		TextureRegion playerTextureRegionDefault = getTextureManager().getTextureRegion("btn-large-normal.png");
		TextureRegion playerTextureRegionCollided = getTextureManager().getTextureRegion("btn-large-selected.png");

		final float viewWidth = getCamera().getViewportWidth();

		final float playerRatio = playerTextureRegionDefault.getWidth() / playerTextureRegionDefault.getHeight();
		final float playerWidth = viewWidth / 3.0f;
		final float playerHeight = playerWidth / playerRatio;

		final float bottomMargin = 10.0f;

		Vector2 playerScale = new Vector2(playerWidth, playerHeight);
		Vector2 playerPosition = new Vector2(viewWidth / 2.0f, playerHeight + bottomMargin);
		Transform playerTransform = new Transform(playerPosition, playerScale);

		TextureHsvMaterial playerDefaultMaterial = new TextureHsvMaterial(playerTextureRegionDefault);
		TextureHsvMaterial playerOnCollisionMaterial = new TextureHsvMaterial(playerTextureRegionCollided);

		return new Player(playerTransform, playerDefaultMaterial, playerOnCollisionMaterial);
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

		TextureRegion ballTextureRegionDefault = getTextureManager().getTextureRegion("btn-small-circle-normal.png");
		TextureHsvMaterial defaultMaterial = new TextureHsvMaterial(ballTextureRegionDefault);

		TextureRegion ballTextureRegionCollided = getTextureManager().getTextureRegion("btn-small-circle-selected.png");
		TextureHsvMaterial onCollisionMaterial = new TextureHsvMaterial(ballTextureRegionCollided);

		return new Ball(ballPosition, ballRadius, ballSpeed, defaultMaterial, onCollisionMaterial);
	}

	private void updateBallPosition(float delta) {
		final float viewWidth = getCamera().getViewportWidth();
		final float viewHeight = getCamera().getViewportHeight();

		Vector2 ballPosition = ball.getPosition();
		float newBallPositionX = ball.calculateNextPositionX(delta);
		float newBallPositionY = ball.calculateNextPositionY(delta);

		if (newBallPositionX + ball.getRadius() > viewWidth) {
			newBallPositionX = viewWidth - ball.getRadius();
			ball.reverseDirectionX();
		} else if (newBallPositionX - ball.getRadius() < 0) {
			newBallPositionX = ball.getRadius();
			ball.reverseDirectionX();
		}

		if (newBallPositionY + ball.getRadius() > viewHeight) {
			newBallPositionY = viewHeight - ball.getRadius();
			ball.reverseDirectionY();
		} else if (newBallPositionY - ball.getRadius() < 0) {
			newBallPositionY = ball.getRadius();
			ball.reverseDirectionY();
		}

		ballPosition.set(newBallPositionX, newBallPositionY);
	}

}

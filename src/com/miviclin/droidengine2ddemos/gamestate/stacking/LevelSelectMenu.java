package com.miviclin.droidengine2ddemos.gamestate.stacking;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Button;

public class LevelSelectMenu extends BaseGameState {

	private Button btnLevel1;
	private Button btnBack;

	public LevelSelectMenu(Game game) {
		super(game);
	}

	@Override
	public void draw(Graphics g) {
		g.setBackgroundColor(getBackgroundColor());
		g.drawRect(btnLevel1.getMaterial(), btnLevel1.getTransform());
		g.drawRect(btnBack.getMaterial(), btnBack.getTransform());
	}

	@Override
	public void onRegister() {
		super.onRegister();
		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/buttons-atlas.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		getBackgroundColor().setRGB(0.0f, 0.0f, 1.0f);

		setupBtnLevel1();
		setupBtnBack();
	}

	private void setupBtnLevel1() {
		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();

		TextureRegion buttonPressedTexture = getTextureManager().getTextureRegion("btn-small-1-selected.png");
		TextureRegion buttonReleasedTexture = getTextureManager().getTextureRegion("btn-small-1-normal.png");
		Vector2 buttonPosition = new Vector2(viewWidth / 2, viewHeight / 2);
		float buttonsize = viewWidth / 4;
		Vector2 buttonScale = new Vector2(buttonsize, buttonsize);
		Transform buttonTransform = new Transform(buttonPosition, buttonScale);

		btnLevel1 = new Button(buttonTransform, buttonPressedTexture, buttonReleasedTexture);
		btnLevel1.addOnClickListener(new Button.OnClickListener() {

			@Override
			public void onButtonPressed(Button button) {
			}

			@Override
			public void onButtonReleased(Button button) {
				getGameStateManager().switchActiveGameState(State.LEVEL_1.getId());
			}
		});

		addButton(btnLevel1);
	}

	private void setupBtnBack() {
		float viewWidth = getCamera().getViewportWidth();

		TextureRegion buttonPressedTexture = getTextureManager().getTextureRegion("btn-large-back-selected.png");
		TextureRegion buttonReleasedTexture = getTextureManager().getTextureRegion("btn-large-back-normal.png");
		float ratio = buttonReleasedTexture.getWidth() / buttonReleasedTexture.getHeight();
		float margin = 20.0f;
		float buttonWidth = viewWidth - margin;
		float buttonHeight = buttonWidth / ratio;
		Vector2 buttonScale = new Vector2(buttonWidth, buttonHeight);
		float buttonY = (buttonHeight / 2) + (margin / 2);
		Vector2 buttonPosition = new Vector2(viewWidth / 2, buttonY);
		Transform buttonTransform = new Transform(buttonPosition, buttonScale);

		btnBack = new Button(buttonTransform, buttonPressedTexture, buttonReleasedTexture);
		btnBack.addOnClickListener(new Button.OnClickListener() {

			@Override
			public void onButtonPressed(Button button) {
			}

			@Override
			public void onButtonReleased(Button button) {
				getGameStateManager().switchActiveGameState(State.MAIN_MENU.getId());
			}
		});

		addButton(btnBack);
	}

}

package com.miviclin.droidengine2ddemos.gamestate;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Button;

public class LevelSelectMenuDefault extends GameStateBase {

	private Button btnLevel1;
	private Button btnLevel2;
	private Button btnLevel3;
	private Button btnBack;

	public LevelSelectMenuDefault(Game game) {
		super(game);
	}

	@Override
	public void draw(Graphics g) {
		g.setBackgroundColor(getBackgroundColor());
		g.drawRect(btnLevel1.getMaterial(), btnLevel1.getTransform());
		g.drawRect(btnLevel2.getMaterial(), btnLevel2.getTransform());
		g.drawRect(btnLevel3.getMaterial(), btnLevel3.getTransform());
		g.drawRect(btnBack.getMaterial(), btnBack.getTransform());
	}

	@Override
	public void onRegister() {
		super.onRegister();
		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/buttons-atlas.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		getBackgroundColor().setRGB(0.0f, 0.0f, 1.0f);

		float margin = 20.0f;
		setupBtnBack(margin);

		float btnBackY = btnBack.getTransform().getPosition().getY();
		float btnBackWidth = btnBack.getTransform().getScale().getY();
		float minPositionY = btnBackY + btnBackWidth + margin;

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();

		float btnLevelSize = viewWidth / 4;
		Vector2 btnLevelDimensions = new Vector2(btnLevelSize, btnLevelSize);

		float availableHeight = viewHeight - minPositionY;
		float distanceBetweenButtons = availableHeight / 4;

		float btnLevelX = viewWidth / 2;

		float btnLevel3Y = minPositionY + distanceBetweenButtons;
		Vector2 btnLevelPosition3 = new Vector2(btnLevelX, btnLevel3Y);
		btnLevel3 = createDisabledButton(btnLevelPosition3, btnLevelDimensions);

		float btnLevel2Y = btnLevel3Y + distanceBetweenButtons;
		Vector2 btnLevelPosition2 = new Vector2(btnLevelX, btnLevel2Y);
		btnLevel2 = createDisabledButton(btnLevelPosition2, btnLevelDimensions);

		float btnLevel1Y = btnLevel2Y + distanceBetweenButtons;
		Vector2 btnLevelPosition1 = new Vector2(btnLevelX, btnLevel1Y);
		btnLevel1 = createDisabledButton(btnLevelPosition1, btnLevelDimensions);
	}

	private Button createDisabledButton(Vector2 buttonPosition, Vector2 buttonScale) {
		TextureRegion buttonPressedTexture = getTextureManager().getTextureRegion("btn-small-disabled.png");
		TextureRegion buttonReleasedTexture = getTextureManager().getTextureRegion("btn-small-disabled.png");
		Transform buttonTransform = new Transform(buttonPosition, buttonScale);
		Button button = new Button(buttonTransform, buttonPressedTexture, buttonReleasedTexture);
		addButton(button);
		return button;
	}

	private void setupBtnBack(float margin) {
		float viewWidth = getCamera().getViewportWidth();

		TextureRegion buttonPressedTexture = getTextureManager().getTextureRegion("btn-large-back-selected.png");
		TextureRegion buttonReleasedTexture = getTextureManager().getTextureRegion("btn-large-back-normal.png");
		float ratio = buttonReleasedTexture.getWidth() / buttonReleasedTexture.getHeight();
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
				getGameStateManager().switchActiveGameState(GameStates.MAIN_MENU);
			}
		});

		addButton(btnBack);
	}

	protected Button getBtnLevel1() {
		return btnLevel1;
	}

	protected Button getBtnLevel2() {
		return btnLevel2;
	}

	protected Button getBtnLevel3() {
		return btnLevel3;
	}

	protected Button getBtnBack() {
		return btnBack;
	}

}

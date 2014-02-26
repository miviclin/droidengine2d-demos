package com.miviclin.droidengine2ddemos.gamestate;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Button;

public class MainMenu extends GameStateBase {

	private Button btnLevelSelect;

	public MainMenu(Game game) {
		super(game);
	}

	@Override
	public void draw(Graphics g) {
		g.setBackgroundColor(getBackgroundColor());
		g.drawRect(btnLevelSelect.getMaterial(), btnLevelSelect.getTransform());
	}

	@Override
	public void onRegister() {
		super.onRegister();
		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/buttons-atlas.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		setupBtnLevelSelect();
	}

	private void setupBtnLevelSelect() {
		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();

		TextureRegion buttonPressedTexture = getTextureManager().getTextureRegion("btn-large-levels-selected.png");
		TextureRegion buttonReleasedTexture = getTextureManager().getTextureRegion("btn-large-levels-normal.png");
		Vector2 buttonPosition = new Vector2(viewWidth / 2, viewHeight / 2);
		float ratio = buttonReleasedTexture.getWidth() / buttonReleasedTexture.getHeight();
		float margin = 20.0f;
		float buttonWidth = viewWidth - margin;
		float buttonHeight = buttonWidth / ratio;
		Vector2 buttonScale = new Vector2(buttonWidth, buttonHeight);
		Transform buttonTransform = new Transform(buttonPosition, buttonScale);

		btnLevelSelect = new Button(buttonTransform, buttonPressedTexture, buttonReleasedTexture);
		btnLevelSelect.addOnClickListener(new Button.OnClickListener() {

			@Override
			public void onButtonPressed(Button button) {
			}

			@Override
			public void onButtonReleased(Button button) {
				getGameStateManager().switchActiveGameState(GameStates.LEVEL_SELECT_MENU);
			}
		});

		addButton(btnLevelSelect);
	}

}

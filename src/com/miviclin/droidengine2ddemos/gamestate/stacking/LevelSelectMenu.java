package com.miviclin.droidengine2ddemos.gamestate.stacking;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2ddemos.gamestate.GameStates;
import com.miviclin.droidengine2ddemos.gamestate.LevelSelectMenuDefault;
import com.miviclin.droidengine2ddemos.util.Button;

public class LevelSelectMenu extends LevelSelectMenuDefault {

	public LevelSelectMenu(Game game) {
		super(game);
	}

	@Override
	public void onRegister() {
		super.onRegister();
		
		TextureRegion buttonPressedTexture = getTextureManager().getTextureRegion("btn-small-1-selected.png");
		TextureRegion buttonReleasedTexture = getTextureManager().getTextureRegion("btn-small-1-normal.png");
		Button btnLevel1 = getBtnLevel1();
		btnLevel1.setButtonPressedTexture(buttonPressedTexture);
		btnLevel1.setButtonReleasedTexture(buttonReleasedTexture);
		
		btnLevel1.addOnClickListener(new Button.OnClickListener() {

			@Override
			public void onButtonPressed(Button button) {
			}

			@Override
			public void onButtonReleased(Button button) {
				getGameStateManager().pushActiveGameState(GameStates.BACK_BUTTON_DIALOG);
			}
		});
	}

}

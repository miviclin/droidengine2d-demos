package com.miviclin.droidengine2ddemos.gamestate.stacking;

import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.ColorMaterial;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.util.Button;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class BackButtonDialog {

	private Rectangle<ColorMaterial> container;
	private Rectangle<ColorMaterial> titleBar;
	private Button btnBack;

	public BackButtonDialog(Vector2 dialogPosition, Vector2 dialogDimensions, float titleBarHeight, Button btnBack) {
		Transform containerTransform = new Transform(dialogPosition, dialogDimensions);
		Color containerColor = new Color(1.0f, 0.0f, 1.0f);
		this.container = new Rectangle<ColorMaterial>(containerTransform, new ColorMaterial(containerColor));

		float titleBarY = dialogPosition.getY() + (dialogDimensions.getY() / 2) - (titleBarHeight / 2);
		Vector2 titleBarPosition = new Vector2(dialogPosition.getX(), titleBarY);
		Vector2 titleBarDimensions = new Vector2(dialogDimensions.getX(), titleBarHeight);
		Transform titleBarTransform = new Transform(titleBarPosition, titleBarDimensions);
		Color titleBarColor = new Color(0.0f, 1.0f, 0.0f);
		this.titleBar = new Rectangle<ColorMaterial>(titleBarTransform, new ColorMaterial(titleBarColor));

		this.btnBack = btnBack;

		float textureRegionWidth = btnBack.getMaterial().getTextureRegion().getWidth();
		float textureRegionHeight = btnBack.getMaterial().getTextureRegion().getHeight();
		float ratio = textureRegionWidth / textureRegionHeight;
		float buttonMargin = dialogDimensions.getX() * 0.20f;
		float buttonWidth = dialogDimensions.getX() - buttonMargin;
		float buttonHeight = buttonWidth / ratio;
		Vector2 buttonScale = new Vector2(buttonWidth, buttonHeight);
		this.btnBack.getTransform().getScale().set(buttonScale);
		this.btnBack.getTransform().getOrigin().set(buttonScale.getX() / 2, buttonScale.getY() / 2);
		
		float buttonX = dialogPosition.getX();
		float bottomOfContainer = containerTransform.getPosition().getY() - containerTransform.getOrigin().getY();
		float buttonY = bottomOfContainer + (buttonScale.getY() / 2) + (buttonMargin / 2);
		Vector2 buttonPosition = new Vector2(buttonX, buttonY);
		this.btnBack.getTransform().getPosition().set(buttonPosition);
	}

	public void draw(Graphics g) {
		g.drawRect(container.getMaterial(), container.getTransform());
		g.drawRect(titleBar.getMaterial(), titleBar.getTransform());
		g.drawRect(btnBack.getMaterial(), btnBack.getTransform());
	}

	public Rectangle<ColorMaterial> getContainer() {
		return container;
	}

	public Rectangle<ColorMaterial> getTitleBar() {
		return titleBar;
	}

	public Button getBtnBack() {
		return btnBack;
	}

}

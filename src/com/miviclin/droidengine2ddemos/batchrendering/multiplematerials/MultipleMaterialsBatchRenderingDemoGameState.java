package com.miviclin.droidengine2ddemos.batchrendering.multiplematerials;

import java.util.ArrayList;

import android.widget.TextView;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.GLDebugger;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.ColorMaterial;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.R;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class MultipleMaterialsBatchRenderingDemoGameState extends GameStateAdapter {

	private static final int NUM_RECTANGLES = 512;

	private ArrayList<Rectangle<ColorMaterial>> rectanglesWithColor;
	private ArrayList<Rectangle<TextureMaterial>> rectanglesWithTexture;
	private MultipleMaterialsBatchRenderingCase caseToShow;
	private int numRectanglesWithColor;
	private int numRectanglesWithTexture;

	public MultipleMaterialsBatchRenderingDemoGameState(Game game, MultipleMaterialsBatchRenderingCase caseToShow) {
		super(game);
		this.caseToShow = caseToShow;
		switch (caseToShow) {
		case CASE_1:
			numRectanglesWithColor = NUM_RECTANGLES / 2;
			break;
		case CASE_2:
			numRectanglesWithColor = (NUM_RECTANGLES / 2) - 1;
			break;
		case CASE_3:
			numRectanglesWithColor = NUM_RECTANGLES / 2;
			break;
		default:
			break;
		}
		numRectanglesWithTexture = NUM_RECTANGLES - numRectanglesWithColor;
	}

	@Override
	public void update(float delta) {
		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				int numDrawCalls = GLDebugger.getInstance().getNumDrawCallsInPreviousFrame();
				TextView tvNumDrawCalls = (TextView) getActivity().findViewById(R.id.textview_num_draw_calls);
				tvNumDrawCalls.setText("Total number of draw calls per frame: " + numDrawCalls);
			}
		});

	}

	@Override
	public void draw(Graphics g) {
		if (caseToShow == MultipleMaterialsBatchRenderingCase.CASE_3) {
			int rectWithColorIndex = 0;
			int rectWithTextureIndex = 0;
			int rectanglesWithColorSize = rectanglesWithColor.size();
			int rectanglesWithTextureSize = rectanglesWithTexture.size();

			while (rectWithColorIndex < rectanglesWithColorSize || rectWithTextureIndex < rectanglesWithTextureSize) {
				if (rectWithColorIndex < rectanglesWithColorSize) {
					Rectangle<ColorMaterial> rectangle = rectanglesWithColor.get(rectWithColorIndex);
					g.drawRect(rectangle.getMaterial(), rectangle.getTransform());
				}
				rectWithColorIndex++;
				if (rectWithTextureIndex < rectanglesWithTextureSize) {
					Rectangle<TextureMaterial> rectangle = rectanglesWithTexture.get(rectWithTextureIndex);
					g.drawRect(rectangle.getMaterial(), rectangle.getTransform());
				}
				rectWithTextureIndex++;
			}
		} else {
			for (int i = 0; i < numRectanglesWithColor; i++) {
				Rectangle<ColorMaterial> rectangle = rectanglesWithColor.get(i);
				g.drawRect(rectangle.getMaterial(), rectangle.getTransform());
			}
			for (int i = 0; i < numRectanglesWithTexture; i++) {
				Rectangle<TextureMaterial> rectangle = rectanglesWithTexture.get(i);
				g.drawRect(rectangle.getMaterial(), rectangle.getTransform());
			}
		}
	}

	@Override
	public void onRegister() {
		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/squares.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		rectanglesWithColor = new ArrayList<Rectangle<ColorMaterial>>(numRectanglesWithColor);
		rectanglesWithTexture = new ArrayList<Rectangle<TextureMaterial>>(numRectanglesWithTexture);

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();

		TextureRegion textureRegion = textureAtlas.getTextureRegion("greensquare_on_shadow.png");
		float rectWidth = viewWidth / 10;
		for (int i = 0; i < numRectanglesWithColor; i++) {
			float rectX = (float) (Math.random() * viewWidth);
			float rectY = (float) (Math.random() * viewHeight);
			Vector2 rectPosition = new Vector2(rectX, rectY);
			Vector2 rectScale = new Vector2(rectWidth, rectWidth);
			Transform transform = new Transform(rectPosition, rectScale);
			float r = (float) Math.random();
			float g = (float) Math.random();
			float b = (float) Math.random();
			rectanglesWithColor.add(new Rectangle<ColorMaterial>(transform, new ColorMaterial(new Color(r, g, b))));
		}

		for (int i = 0; i < numRectanglesWithTexture; i++) {
			float rectX = (float) (Math.random() * viewWidth);
			float rectY = (float) (Math.random() * viewHeight);
			Vector2 rectPosition = new Vector2(rectX, rectY);
			Vector2 rectScale = new Vector2(rectWidth, rectWidth);
			Transform transform = new Transform(rectPosition, rectScale);
			rectanglesWithTexture.add(new Rectangle<TextureMaterial>(transform, new TextureMaterial(textureRegion)));
		}

		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				TextView tvMaterial1 = (TextView) getActivity().findViewById(R.id.textview_material_1);
				tvMaterial1.setText("Rendering " + numRectanglesWithColor + " rectangles with "
						+ TextureMaterial.class.getSimpleName());

				TextView tvMaterial2 = (TextView) getActivity().findViewById(R.id.textview_material_2);
				tvMaterial2.setText("Rendering " + numRectanglesWithTexture + " rectangles with "
						+ ColorMaterial.class.getSimpleName());
			}
		});
	}
}

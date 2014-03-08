package com.miviclin.droidengine2ddemos.batchrendering.singlematerial;

import java.util.ArrayList;

import android.widget.TextView;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.GLDebugger;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.R;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class SingleMaterialBatchRenderingDemoGameState extends GameStateAdapter {

	private static final int NUM_RECTANGLES = 512;

	private ArrayList<Rectangle<TextureMaterial>> rectangles;

	public SingleMaterialBatchRenderingDemoGameState(Game game) {
		super(game);
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
		for (int i = 0; i < NUM_RECTANGLES; i++) {
			Rectangle<TextureMaterial> rectangle = rectangles.get(i);
			g.drawRect(rectangle.getMaterial(), rectangle.getTransform());
		}
	}

	@Override
	public void onRegister() {
		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/squares.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		rectangles = new ArrayList<Rectangle<TextureMaterial>>(NUM_RECTANGLES);

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();

		TextureRegion textureRegion = textureAtlas.getTextureRegion("greensquare_on_shadow.png");
		float rectWidth = viewWidth / 10;
		for (int i = 0; i < NUM_RECTANGLES; i++) {
			float rectX = (float) (Math.random() * viewWidth);
			float rectY = (float) (Math.random() * viewHeight);
			Vector2 rectPosition = new Vector2(rectX, rectY);
			Vector2 rectScale = new Vector2(rectWidth, rectWidth);
			Transform transform = new Transform(rectPosition, rectScale);
			TextureMaterial material = new TextureMaterial(textureRegion);
			rectangles.add(new Rectangle<TextureMaterial>(transform, material));
		}

		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				TextView tvMaterial = (TextView) getActivity().findViewById(R.id.textview_material);
				tvMaterial.setText("Rendering " + NUM_RECTANGLES + " rectangles with "
						+ TextureMaterial.class.getSimpleName());
			}
		});
	}

}

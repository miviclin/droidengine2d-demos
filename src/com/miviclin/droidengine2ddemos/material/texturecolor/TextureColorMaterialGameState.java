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
package com.miviclin.droidengine2ddemos.material.texturecolor;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureColorMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.R;
import com.miviclin.droidengine2ddemos.util.MathUtils;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class TextureColorMaterialGameState extends GameStateAdapter {

	private Rectangle<TextureColorMaterial> rectangle;

	public TextureColorMaterialGameState(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {
		SeekBar sbRed = (SeekBar) getActivity().findViewById(R.id.seekbar_red);
		SeekBar sbGreen = (SeekBar) getActivity().findViewById(R.id.seekbar_green);
		SeekBar sbBlue = (SeekBar) getActivity().findViewById(R.id.seekbar_blue);
		SeekBar sbAlpha = (SeekBar) getActivity().findViewById(R.id.seekbar_alpha);
		float r = sbRed.getProgress() / 255.0f;
		float g = sbGreen.getProgress() / 255.0f;
		float b = sbBlue.getProgress() / 255.0f;
		float a = sbAlpha.getProgress() / 255.0f;
		rectangle.getMaterial().getColor().setRGBA(r, g, b, a);
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(rectangle.getMaterial(), rectangle.getTransform());
	}

	@Override
	public void onRegister() {
		TextureAtlas textureAtlas = new TexturePackerAtlas();
		textureAtlas.loadFromFile("textures/squares.xml", getActivity());
		getTextureManager().addTextureAtlas(textureAtlas);

		TextureRegion textureRegion = textureAtlas.getTextureRegion("greensquare_on_shadow.png");

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();

		float rectSize = MathUtils.clamp(Math.min(viewWidth, viewHeight) / 2, 100, textureRegion.getWidth() * 2);

		Vector2 rectPosition = new Vector2(viewWidth / 2, viewHeight / 2);
		Vector2 rectScale = new Vector2(rectSize, rectSize);
		Transform transform = new Transform(rectPosition, rectScale);
		Color rectColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
		rectangle = new Rectangle<TextureColorMaterial>(transform, new TextureColorMaterial(textureRegion, rectColor));

		final TextView tvRed = (TextView) getActivity().findViewById(R.id.textview_red);

		SeekBar sbRed = (SeekBar) getActivity().findViewById(R.id.seekbar_red);
		sbRed.setMax(255);
		sbRed.setProgress(255);
		sbRed.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				tvRed.setText("R: " + ((float) progress));
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});

		final TextView tvGreen = (TextView) getActivity().findViewById(R.id.textview_green);

		SeekBar sbGreen = (SeekBar) getActivity().findViewById(R.id.seekbar_green);
		sbGreen.setMax(255);
		sbGreen.setProgress(255);
		sbGreen.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				tvGreen.setText("G: " + ((float) progress));
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});

		final TextView tvBlue = (TextView) getActivity().findViewById(R.id.textview_blue);

		SeekBar sbBlue = (SeekBar) getActivity().findViewById(R.id.seekbar_blue);
		sbBlue.setMax(255);
		sbBlue.setProgress(255);
		sbBlue.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				tvBlue.setText("B: " + ((float) progress));
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});

		final TextView tvAlpha = (TextView) getActivity().findViewById(R.id.textview_alpha);

		SeekBar sbAlpha = (SeekBar) getActivity().findViewById(R.id.seekbar_alpha);
		sbAlpha.setMax(255);
		sbAlpha.setProgress(255);
		sbAlpha.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				tvAlpha.setText("A: " + ((float) progress));
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
	}

}

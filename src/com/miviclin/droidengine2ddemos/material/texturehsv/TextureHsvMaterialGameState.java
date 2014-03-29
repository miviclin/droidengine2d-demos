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
package com.miviclin.droidengine2ddemos.material.texturehsv;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureHsvMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.R;
import com.miviclin.droidengine2ddemos.util.MathUtils;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class TextureHsvMaterialGameState extends GameStateAdapter {

	private Rectangle<TextureHsvMaterial> rectangle;

	public TextureHsvMaterialGameState(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {
		SeekBar sbHue = (SeekBar) getActivity().findViewById(R.id.seekbar_hue);
		SeekBar sbSaturation = (SeekBar) getActivity().findViewById(R.id.seekbar_saturation);
		SeekBar sbBrightness = (SeekBar) getActivity().findViewById(R.id.seekbar_brightness);
		rectangle.getMaterial().setHOffset(sbHue.getProgress());
		rectangle.getMaterial().setSMulti(sbSaturation.getProgress() / 100.0f);
		rectangle.getMaterial().setVMulti(sbBrightness.getProgress() / 100.0f);
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
		rectangle = new Rectangle<TextureHsvMaterial>(transform, new TextureHsvMaterial(textureRegion));

		final TextView tvHue = (TextView) getActivity().findViewById(R.id.textview_hue);

		SeekBar sbHue = (SeekBar) getActivity().findViewById(R.id.seekbar_hue);
		sbHue.setMax(360);
		sbHue.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				tvHue.setText("H: " + ((float) progress));
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});

		final TextView tvSaturation = (TextView) getActivity().findViewById(R.id.textview_saturation);

		SeekBar sbSaturation = (SeekBar) getActivity().findViewById(R.id.seekbar_saturation);
		sbSaturation.setProgress(100);
		sbSaturation.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				tvSaturation.setText("S: " + ((float) progress));
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});

		final TextView tvBrightness = (TextView) getActivity().findViewById(R.id.textview_brightness);

		SeekBar sbBrightness = (SeekBar) getActivity().findViewById(R.id.seekbar_brightness);
		sbBrightness.setProgress(100);
		sbBrightness.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				tvBrightness.setText("V: " + ((float) progress));
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

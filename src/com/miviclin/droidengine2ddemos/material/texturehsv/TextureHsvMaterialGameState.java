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
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.R;
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

		float viewWidth = getCamera().getViewportWidth();
		Transform transform = new Transform(new Vector2(viewWidth / 2, 125), new Vector2(240, 240));
		rectangle = new Rectangle<TextureHsvMaterial>(transform,
				new TextureHsvMaterial(textureAtlas.getTextureRegion("greensquare_on_shadow.png")));

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

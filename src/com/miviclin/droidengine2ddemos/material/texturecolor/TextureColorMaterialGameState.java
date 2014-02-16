package com.miviclin.droidengine2ddemos.material.texturecolor;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameState;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureColorMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.R;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class TextureColorMaterialGameState extends GameState {

	private Rectangle<TextureColorMaterial> rectangle;

	public TextureColorMaterialGameState(Game game) {
		super(game);
	}

	@Override
	public void update(float delta) {
		SeekBar sbRed = (SeekBar) getGame().getActivity().findViewById(R.id.seekbar_red);
		SeekBar sbGreen = (SeekBar) getGame().getActivity().findViewById(R.id.seekbar_green);
		SeekBar sbBlue = (SeekBar) getGame().getActivity().findViewById(R.id.seekbar_blue);
		SeekBar sbAlpha = (SeekBar) getGame().getActivity().findViewById(R.id.seekbar_alpha);
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
		textureAtlas.loadFromFile("textures/squares.xml", getGame().getActivity());
		getGame().getTextureManager().addTextureAtlas(textureAtlas);

		Transform transform = new Transform(new Vector2(getGame().getViewWidth() / 2, 125), new Vector2(240, 240));
		rectangle = new Rectangle<TextureColorMaterial>(transform,
				new TextureColorMaterial(textureAtlas.getTextureRegion("greensquare_on_shadow.png"),
						new Color(1.0f, 1.0f, 1.0f, 1.0f)));

		final TextView tvRed = (TextView) getGame().getActivity().findViewById(R.id.textview_red);

		SeekBar sbRed = (SeekBar) getGame().getActivity().findViewById(R.id.seekbar_red);
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

		final TextView tvGreen = (TextView) getGame().getActivity().findViewById(R.id.textview_green);

		SeekBar sbGreen = (SeekBar) getGame().getActivity().findViewById(R.id.seekbar_green);
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

		final TextView tvBlue = (TextView) getGame().getActivity().findViewById(R.id.textview_blue);

		SeekBar sbBlue = (SeekBar) getGame().getActivity().findViewById(R.id.seekbar_blue);
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

		final TextView tvAlpha = (TextView) getGame().getActivity().findViewById(R.id.textview_alpha);

		SeekBar sbAlpha = (SeekBar) getGame().getActivity().findViewById(R.id.seekbar_alpha);
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

	@Override
	public void onActivation() {
	}

	@Override
	public void onDeactivation() {
	}

	@Override
	public void onPause() {
	}

	@Override
	public void onResume() {
	}

	@Override
	public void onDispose() {
	}

}

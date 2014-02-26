package com.miviclin.droidengine2ddemos.material.color;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Color;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.ColorMaterial;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.R;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class ColorMaterialGameState extends GameStateAdapter {

	private Rectangle<ColorMaterial> rectangle;

	public ColorMaterialGameState(Game game) {
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
		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();
		Transform transform = new Transform(new Vector2(viewWidth / 2, viewHeight / 2), new Vector2(240, 240));
		rectangle = new Rectangle<ColorMaterial>(transform, new ColorMaterial(new Color(0.0f, 1.0f, 0.0f)));

		final TextView tvRed = (TextView) getActivity().findViewById(R.id.textview_red);

		SeekBar sbRed = (SeekBar) getActivity().findViewById(R.id.seekbar_red);
		sbRed.setMax(255);
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

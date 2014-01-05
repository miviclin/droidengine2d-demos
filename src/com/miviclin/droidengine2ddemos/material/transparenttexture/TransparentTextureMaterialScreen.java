package com.miviclin.droidengine2ddemos.material.transparenttexture;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TransparentTextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.screen.Screen;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.R;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class TransparentTextureMaterialScreen extends Screen {

	private Rectangle<TransparentTextureMaterial> rectangle;

	public TransparentTextureMaterialScreen(float viewWidth, float viewHeight, Game game) {
		super(viewWidth, viewHeight, game);
	}

	@Override
	public void update(float delta) {
		SeekBar sbOpacity = (SeekBar) getGame().getActivity().findViewById(R.id.seekbar_opacity);
		rectangle.getMaterial().setOpacity(sbOpacity.getProgress() / 100.0f);
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

		Transform transform = new Transform(new Vector2(getWidth() / 2, 125), new Vector2(240, 240));
		rectangle = new Rectangle<TransparentTextureMaterial>(transform,
				new TransparentTextureMaterial(textureAtlas.getTextureRegion("greensquare_on_shadow.png")));

		final TextView tvOpacity = (TextView) getGame().getActivity().findViewById(R.id.textview_opacity);

		SeekBar sbBrightness = (SeekBar) getGame().getActivity().findViewById(R.id.seekbar_opacity);
		sbBrightness.setProgress(100);
		sbBrightness.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				tvOpacity.setText("Opacity: " + ((float) progress));
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

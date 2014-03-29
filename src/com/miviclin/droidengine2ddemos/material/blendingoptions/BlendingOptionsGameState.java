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
package com.miviclin.droidengine2ddemos.material.blendingoptions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.gamestate.GameStateAdapter;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.BlendingOptions;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.R;
import com.miviclin.droidengine2ddemos.util.MathUtils;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class BlendingOptionsGameState extends GameStateAdapter {

	private static final BlendingFactor DEFAULT_SOURCE_FACTOR = BlendingFactor.GL_SRC_ALPHA;
	private static final BlendingFactor DEFAULT_DEST_FACTOR = BlendingFactor.GL_ONE_MINUS_SRC_ALPHA;
	private static final BlendingEquation DEFAULT_EQUATION = BlendingEquation.GL_FUNC_ADD;

	private final Map<String, Integer> blendingFactors;
	private final Map<String, Integer> blendingEquations;
	private final Rectangle<TextureMaterial> square;
	private ArrayList<Rectangle<TextureMaterial>> backgroundTiles;
	private AlertDialog alertDialog;
	private AtomicBoolean handlingException;

	public BlendingOptionsGameState(Game game) {
		super(game);
		this.blendingFactors = new LinkedHashMap<String, Integer>();
		this.blendingEquations = new LinkedHashMap<String, Integer>();

		Transform squareTransform = new Transform(new Vector2(0, 0), new Vector2(1, 1));
		this.square = new Rectangle<TextureMaterial>(squareTransform, new TextureMaterial(null));

		this.backgroundTiles = new ArrayList<Rectangle<TextureMaterial>>();
		this.handlingException = new AtomicBoolean();
	}

	@Override
	public void draw(Graphics g) {
		if (handlingException.get()) {
			return;
		}
		for (int i = 0; i < backgroundTiles.size(); i++) {
			g.drawRect(backgroundTiles.get(i).getMaterial(), backgroundTiles.get(i).getTransform());
		}
		try {
			g.drawRect(square.getMaterial(), square.getTransform());
			g.flush();
		} catch (Exception e) {
			handlingException.set(true);
			handleBlendingException();
		}
	}

	@Override
	public void onRegister() {
		TextureAtlas squaresAtlas = new TexturePackerAtlas();
		squaresAtlas.loadFromFile("textures/squares.xml", getActivity());
		getTextureManager().addTextureAtlas(squaresAtlas);
		
		TextureRegion textureRegion = squaresAtlas.getTextureRegion("greensquare_on_shadow.png");

		float viewWidth = getCamera().getViewportWidth();
		float viewHeight = getCamera().getViewportHeight();
		
		float rectSize = MathUtils.clamp(Math.min(viewWidth, viewHeight) / 2, 100, textureRegion.getWidth() * 2);
		
		Vector2 rectPosition = new Vector2(viewWidth / 2, viewHeight / 2);
		Vector2 rectOrigin =  new Vector2(rectSize / 2, rectSize / 2);
		Vector2 rectScale =  new Vector2(rectSize, rectSize);
		
		square.getTransform().getPosition().set(rectPosition);
		square.getTransform().getOrigin().set(rectOrigin);
		square.getTransform().getScale().set(rectScale);
		square.getMaterial().setTextureRegion(textureRegion);

		TextureAtlas backgroundAtlas = new TexturePackerAtlas();
		backgroundAtlas.loadFromFile("textures/background.xml", getActivity());
		getTextureManager().addTextureAtlas(backgroundAtlas);

		int tileSize = 40;
		int numTilesX = (int) (viewWidth / tileSize + 1);
		int numTilesY = (int) (viewHeight / tileSize + 1);
		TextureRegion backgroundTile = backgroundAtlas.getTextureRegion("background_tile.png");
		for (int i = 0; i < numTilesX; i++) {
			for (int j = 0; j < numTilesY; j++) {
				backgroundTiles.add(new Rectangle<TextureMaterial>(
						new Transform(new Vector2(i * tileSize, j * tileSize), new Vector2(tileSize, tileSize)),
						new TextureMaterial(backgroundTile)));
			}
		}

		initializeBlendingOptionsMaps();
		initializeAlertDialog();
		initializeSpinners();
	}

	@Override
	public void onPause() {
		if (alertDialog != null) {
			dismissAlertDialog();
		}
	}

	@Override
	public void onResume() {
		if (alertDialog == null) {
			initializeAlertDialog();
		}
	}

	private void initializeBlendingOptionsMaps() {
		addBlendingFactor(BlendingFactor.GL_ZERO);
		addBlendingFactor(BlendingFactor.GL_ONE);
		addBlendingFactor(BlendingFactor.GL_SRC_COLOR);
		addBlendingFactor(BlendingFactor.GL_ONE_MINUS_SRC_COLOR);
		addBlendingFactor(BlendingFactor.GL_DST_COLOR);
		addBlendingFactor(BlendingFactor.GL_ONE_MINUS_DST_COLOR);
		addBlendingFactor(BlendingFactor.GL_SRC_ALPHA);
		addBlendingFactor(BlendingFactor.GL_ONE_MINUS_SRC_ALPHA);
		addBlendingFactor(BlendingFactor.GL_DST_ALPHA);
		addBlendingFactor(BlendingFactor.GL_ONE_MINUS_DST_ALPHA);
		addBlendingFactor(BlendingFactor.GL_CONSTANT_COLOR);
		addBlendingFactor(BlendingFactor.GL_ONE_MINUS_CONSTANT_COLOR);
		addBlendingFactor(BlendingFactor.GL_CONSTANT_ALPHA);
		addBlendingFactor(BlendingFactor.GL_ONE_MINUS_CONSTANT_ALPHA);
		addBlendingFactor(BlendingFactor.GL_SRC_ALPHA_SATURATE);

		addBlendingEquation(BlendingEquation.GL_FUNC_ADD);
		addBlendingEquation(BlendingEquation.GL_FUNC_SUBTRACT);
		addBlendingEquation(BlendingEquation.GL_FUNC_REVERSE_SUBTRACT);
	}

	private void addBlendingFactor(BlendingFactor blendingFactor) {
		blendingFactors.put(blendingFactor.toString(), blendingFactor.getValue());
	}

	private void addBlendingEquation(BlendingEquation blendingEquation) {
		blendingEquations.put(blendingEquation.toString(), blendingEquation.getValue());
	}

	private void initializeSpinners() {
		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				final BlendingOptions squareBlendingOptions = square.getMaterial().getBlendingOptions();

				CharSequence[] blendingFactorsNames = new String[blendingFactors.keySet().size()];
				ArrayAdapter<CharSequence> factorsAdapter = new ArrayAdapter<CharSequence>(getActivity(),
						android.R.layout.simple_spinner_item, blendingFactors.keySet().toArray(blendingFactorsNames));

				factorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				// Source factors
				Spinner spinnerSource = (Spinner) getActivity().findViewById(R.id.spinner_source);
				spinnerSource.setAdapter(factorsAdapter);
				spinnerSource.setSelection(getFactorIndex(DEFAULT_SOURCE_FACTOR));
				squareBlendingOptions.setSourceFactor(DEFAULT_SOURCE_FACTOR.getValue());

				spinnerSource.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						Object selectedItem = parent.getItemAtPosition(position);
						int sourceFactor = blendingFactors.get(selectedItem.toString()).intValue();
						squareBlendingOptions.setSourceFactor(sourceFactor);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

				// Destination factors
				Spinner spinnerDest = (Spinner) getActivity().findViewById(R.id.spinner_destination);
				spinnerDest.setAdapter(factorsAdapter);
				spinnerDest.setSelection(getFactorIndex(DEFAULT_DEST_FACTOR));
				squareBlendingOptions.setDestinationFactor(DEFAULT_DEST_FACTOR.getValue());

				spinnerDest.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						Object selectedItem = parent.getItemAtPosition(position);
						int destFactor = blendingFactors.get(selectedItem.toString()).intValue();
						squareBlendingOptions.setDestinationFactor(destFactor);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

				// Blending equations
				Spinner spinnerEquation = (Spinner) getActivity().findViewById(R.id.spinner_equation);

				// Allow changing the blending equation only if the version of the device is ice cream sandwich or
				// newer.
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
					CharSequence[] blendingEquationsNames = new String[blendingEquations.keySet().size()];
					ArrayAdapter<CharSequence> equationsAdapter = new ArrayAdapter<CharSequence>(getActivity(),
							android.R.layout.simple_spinner_item,
							blendingEquations.keySet().toArray(blendingEquationsNames));

					equationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

					spinnerEquation.setAdapter(equationsAdapter);
					spinnerEquation.setSelection(getEquationIndex(DEFAULT_EQUATION));
					squareBlendingOptions.setBlendEquationMode(DEFAULT_EQUATION.getValue());

					spinnerEquation.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
							Object selectedItem = parent.getItemAtPosition(position);
							int equation = blendingEquations.get(selectedItem.toString()).intValue();
							squareBlendingOptions.setBlendEquationMode(equation);
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {
						}
					});
				} else {
					spinnerEquation.setEnabled(false);
				}
			}
		});
	}

	private void initializeAlertDialog() {
		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				alertDialog = createAlertDialog();
			}
		});
	}

	private AlertDialog createAlertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(R.string.blending_options_alert_msg)
				.setTitle(R.string.blending_options_alert_title)
				.setCancelable(false)
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});

		return builder.create();
	}

	private void showAlertDialog() {
		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (alertDialog == null) {
					alertDialog = createAlertDialog();
				}
				alertDialog.show();
			}
		});
	}

	private void dismissAlertDialog() {
		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				alertDialog.dismiss();
				alertDialog = null;
			}
		});
	}

	private void handleBlendingException() {
		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				BlendingOptions squareBlendingOptions = square.getMaterial().getBlendingOptions();

				Spinner spinnerSource = (Spinner) getActivity().findViewById(R.id.spinner_source);
				spinnerSource.setSelection(getFactorIndex(DEFAULT_SOURCE_FACTOR));
				squareBlendingOptions.setSourceFactor(DEFAULT_SOURCE_FACTOR.getValue());

				Spinner spinnerDest = (Spinner) getActivity().findViewById(R.id.spinner_destination);
				spinnerDest.setSelection(getFactorIndex(DEFAULT_DEST_FACTOR));
				squareBlendingOptions.setDestinationFactor(DEFAULT_DEST_FACTOR.getValue());

				Spinner spinnerEquation = (Spinner) getActivity().findViewById(R.id.spinner_equation);
				spinnerEquation.setSelection(getEquationIndex(DEFAULT_EQUATION));
				squareBlendingOptions.setBlendEquationMode(DEFAULT_EQUATION.getValue());

				handlingException.set(false);

				showAlertDialog();
			}
		});
	}

	private int getFactorIndex(BlendingFactor blendingFactor) {
		int factorIndex = 0;
		int i = 0;
		for (String blendingFactorKey : blendingFactors.keySet()) {
			if (blendingFactorKey.equals(blendingFactor.toString())) {
				factorIndex = i;
			}
			i++;
		}
		return factorIndex;
	}

	private int getEquationIndex(BlendingEquation blendingEquation) {
		int equationIndex = 0;
		int i = 0;
		for (String blendingEquationKey : blendingEquations.keySet()) {
			if (blendingEquationKey.equals(blendingEquation.toString())) {
				equationIndex = i;
			}
			i++;
		}
		return equationIndex;
	}

}

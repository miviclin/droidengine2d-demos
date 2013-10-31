package com.miviclin.droidengine2ddemos.material.blendingoptions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.opengl.GLES20;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.Graphics;
import com.miviclin.droidengine2d.graphics.material.TextureMaterial;
import com.miviclin.droidengine2d.graphics.texture.TextureAtlas;
import com.miviclin.droidengine2d.graphics.texture.TexturePackerAtlas;
import com.miviclin.droidengine2d.graphics.texture.TextureRegion;
import com.miviclin.droidengine2d.scene.Scene;
import com.miviclin.droidengine2d.util.Transform;
import com.miviclin.droidengine2d.util.math.Vector2;
import com.miviclin.droidengine2ddemos.R;
import com.miviclin.droidengine2ddemos.util.Rectangle;

public class BlendingOptionsScene extends Scene {

	private final Map<String, Integer> blendingFactors;
	private final Map<String, Integer> blendingEquations;
	private final Rectangle<TextureMaterial> square;
	private ArrayList<Rectangle<TextureMaterial>> backgroundTiles;
	private AlertDialog alertDialog;

	public BlendingOptionsScene(Game game) {
		super(game);
		this.blendingFactors = new LinkedHashMap<String, Integer>();
		this.blendingEquations = new LinkedHashMap<String, Integer>();

		Transform squareTransform = new Transform(new Vector2(0, 0), new Vector2(1, 1));
		this.square = new Rectangle<TextureMaterial>(squareTransform, new TextureMaterial(null));

		this.backgroundTiles = new ArrayList<Rectangle<TextureMaterial>>();
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < backgroundTiles.size(); i++) {
			g.drawRect(backgroundTiles.get(i).getMaterial(), backgroundTiles.get(i).getTransform());
		}
		try {
			g.drawRect(square.getMaterial(), square.getTransform());
		} catch (Exception e) {
			handleBlendingException();
		}
	}

	@Override
	public void onRegister() {
		TextureAtlas squaresAtlas = new TexturePackerAtlas();
		squaresAtlas.loadFromXML("textures/squares.xml", getGame().getActivity());
		getGame().getTextureManager().addTextureAtlas(squaresAtlas);

		square.getTransform().getPosition().set(getGame().getGameViewWidth() / 2, getGame().getGameViewHeight() / 2);
		square.getTransform().getOrigin().set(120, 120);
		square.getTransform().getScale().set(240, 240);
		square.getMaterial().setTextureRegion(squaresAtlas.getTextureRegion("green_square_on.png"));

		TextureAtlas backgroundAtlas = new TexturePackerAtlas();
		backgroundAtlas.loadFromXML("textures/background.xml", getGame().getActivity());
		getGame().getTextureManager().addTextureAtlas(backgroundAtlas);

		int tileSize = 40;
		int numTilesX = getGame().getGameViewWidth() / tileSize + 1;
		int numTilesY = getGame().getGameViewHeight() / tileSize + 1;
		TextureRegion backgroundTile = backgroundAtlas.getTextureRegion("background_tile.png");
		for (int i = 0; i < numTilesX; i++) {
			for (int j = 0; j < numTilesY; j++) {
				backgroundTiles.add(new Rectangle<TextureMaterial>(
						new Transform(new Vector2(i * tileSize, j * tileSize), new Vector2(tileSize, tileSize)),
						new TextureMaterial(backgroundTile)));
			}
		}

		blendingFactors.put("GL_ZERO", GLES20.GL_ZERO);
		blendingFactors.put("GL_ONE", GLES20.GL_ONE);
		blendingFactors.put("GL_SRC_COLOR", GLES20.GL_SRC_COLOR);
		blendingFactors.put("GL_ONE_MINUS_SRC_COLOR", GLES20.GL_ONE_MINUS_SRC_COLOR);
		blendingFactors.put("GL_DST_COLOR", GLES20.GL_DST_COLOR);
		blendingFactors.put("GL_ONE_MINUS_DST_COLOR", GLES20.GL_ONE_MINUS_DST_COLOR);
		blendingFactors.put("GL_SRC_ALPHA", GLES20.GL_SRC_ALPHA);
		blendingFactors.put("GL_ONE_MINUS_SRC_ALPHA", GLES20.GL_ONE_MINUS_SRC_ALPHA);
		blendingFactors.put("GL_DST_ALPHA", GLES20.GL_DST_ALPHA);
		blendingFactors.put("GL_ONE_MINUS_DST_ALPHA", GLES20.GL_ONE_MINUS_DST_ALPHA);
		blendingFactors.put("GL_CONSTANT_COLOR", GLES20.GL_CONSTANT_COLOR);
		blendingFactors.put("GL_ONE_MINUS_CONSTANT_COLOR", GLES20.GL_ONE_MINUS_CONSTANT_COLOR);
		blendingFactors.put("GL_CONSTANT_ALPHA", GLES20.GL_CONSTANT_ALPHA);
		blendingFactors.put("GL_ONE_MINUS_CONSTANT_ALPHA", GLES20.GL_ONE_MINUS_CONSTANT_ALPHA);
		blendingFactors.put("GL_SRC_ALPHA_SATURATE", GLES20.GL_SRC_ALPHA_SATURATE);

		blendingEquations.put("GL_FUNC_ADD", GLES20.GL_FUNC_ADD);
		blendingEquations.put("GL_FUNC_SUBTRACT", GLES20.GL_FUNC_SUBTRACT);
		blendingEquations.put("GL_FUNC_REVERSE_SUBTRACT", GLES20.GL_FUNC_REVERSE_SUBTRACT);

		getGame().getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				initializeAlertDialog();

				CharSequence[] blendingFactorsNames = new String[blendingFactors.keySet().size()];
				ArrayAdapter<CharSequence> factorsAdapter = new ArrayAdapter<CharSequence>(getGame().getActivity(),
						android.R.layout.simple_spinner_item, blendingFactors.keySet().toArray(blendingFactorsNames));

				factorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				Spinner spinnerSource = (Spinner) getGame().getActivity().findViewById(R.id.spinner_source);
				spinnerSource.setAdapter(factorsAdapter);

				spinnerSource.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						Object selectedItem = parent.getItemAtPosition(position);
						int sourceFactor = blendingFactors.get(selectedItem.toString()).intValue();
						square.getMaterial().getBlendingOptions().setSourceFactor(sourceFactor);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

				Spinner spinnerDest = (Spinner) getGame().getActivity().findViewById(R.id.spinner_destination);
				spinnerDest.setAdapter(factorsAdapter);

				spinnerDest.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						Object selectedItem = parent.getItemAtPosition(position);
						int destFactor = blendingFactors.get(selectedItem.toString()).intValue();
						square.getMaterial().getBlendingOptions().setDestinationFactor(destFactor);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

				CharSequence[] blendingEquationsNames = new String[blendingEquations.keySet().size()];
				ArrayAdapter<CharSequence> equationsAdapter = new ArrayAdapter<CharSequence>(getGame().getActivity(),
						android.R.layout.simple_spinner_item,
						blendingEquations.keySet().toArray(blendingEquationsNames));

				equationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				Spinner spinnerEquation = (Spinner) getGame().getActivity().findViewById(R.id.spinner_equation);
				spinnerEquation.setAdapter(equationsAdapter);

				spinnerEquation.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						Object selectedItem = parent.getItemAtPosition(position);
						int equation = blendingEquations.get(selectedItem.toString()).intValue();
						square.getMaterial().getBlendingOptions().setBlendEquationMode(equation);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
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
		if (alertDialog != null) {
			getGame().getActivity().runOnUiThread(new Runnable() {

				@Override
				public void run() {
					dismissAlertDialog();
				}
			});
		}
	}

	@Override
	public void onResume() {
		if (alertDialog == null) {
			getGame().getActivity().runOnUiThread(new Runnable() {

				@Override
				public void run() {
					initializeAlertDialog();
				}
			});
		}
	}

	@Override
	public void dispose() {
	}

	private void initializeAlertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getGame().getActivity());
		builder.setMessage(R.string.blending_options_alert_msg)
				.setTitle(R.string.blending_options_alert_title)
				.setCancelable(false)
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});

		alertDialog = builder.create();
	}

	private void showAlertDialog() {
		if (alertDialog != null) {
			alertDialog.show();
		}
	}

	private void dismissAlertDialog() {
		if (alertDialog != null) {
			alertDialog.dismiss();
			alertDialog = null;
		}
	}

	private void handleBlendingException() {
		getGame().getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {
				int factor1Index = 0;
				int factor2Index = 0;
				int i = 0;
				for (String blendingFactorKey : blendingFactors.keySet()) {
					if (blendingFactorKey.equals("GL_SRC_ALPHA")) {
						factor1Index = i;
					} else if (blendingFactorKey.equals("GL_ONE_MINUS_SRC_ALPHA")) {
						factor2Index = i;
					}
					i++;
				}
				int equationIndex = 0;
				i = 0;
				for (String blendingEquationKey : blendingEquations.keySet()) {
					if (blendingEquationKey.equals("GL_FUNC_ADD")) {
						equationIndex = i;
					}
					i++;
				}

				Spinner spinnerSource = (Spinner) getGame().getActivity().findViewById(R.id.spinner_source);
				spinnerSource.setSelection(factor1Index);
				int sourceFactor = blendingFactors.get(spinnerSource.getSelectedItem().toString()).intValue();
				square.getMaterial().getBlendingOptions().setSourceFactor(sourceFactor);

				Spinner spinnerDest = (Spinner) getGame().getActivity().findViewById(R.id.spinner_destination);
				spinnerDest.setSelection(factor2Index);
				int destFactor = blendingFactors.get(spinnerDest.getSelectedItem().toString()).intValue();
				square.getMaterial().getBlendingOptions().setDestinationFactor(destFactor);

				Spinner spinnerEquation = (Spinner) getGame().getActivity().findViewById(R.id.spinner_equation);
				spinnerEquation.setSelection(equationIndex);
				int equation = blendingEquations.get(spinnerEquation.getSelectedItem().toString()).intValue();
				square.getMaterial().getBlendingOptions().setBlendEquationMode(equation);
				
				showAlertDialog();
			}
		});
	}

}

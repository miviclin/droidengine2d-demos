package com.miviclin.droidengine2ddemos.material.blendingoptions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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
	
	private final Map<String, Integer> blendingFactors = new LinkedHashMap<String, Integer>();
	private final Map<String, Integer> blendingEquations = new LinkedHashMap<String, Integer>();
	
	private final Rectangle<TextureMaterial> square = new Rectangle<TextureMaterial>(new Transform(new Vector2(0, 0), new Vector2(1, 1)),
			new TextureMaterial(null));
	
	private ArrayList<Rectangle<TextureMaterial>> backgroundTiles = new ArrayList<Rectangle<TextureMaterial>>();
	
	public BlendingOptionsScene(Game game) {
		super(game);
	}
	
	@Override
	public void update(float delta) {
	}
	
	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < backgroundTiles.size(); i++) {
			g.drawRect(backgroundTiles.get(i).getMaterial(), backgroundTiles.get(i).getTransform());
		}
		g.drawRect(square.getMaterial(), square.getTransform());
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
				CharSequence[] blendingFactorsNames = new String[blendingFactors.keySet().size()];
				ArrayAdapter<CharSequence> blendingFactorsAdapter = new ArrayAdapter<CharSequence>(getGame().getActivity(),
						android.R.layout.simple_spinner_item, blendingFactors.keySet().toArray(blendingFactorsNames));
				
				blendingFactorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				
				Spinner spinnerSource = (Spinner) getGame().getActivity().findViewById(R.id.spinner_source);
				spinnerSource.setAdapter(blendingFactorsAdapter);
				
				spinnerSource.setOnItemSelectedListener(new OnItemSelectedListener() {
					
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						int sourceFactor = blendingFactors.get(parent.getItemAtPosition(position).toString()).intValue();
						square.getMaterial().getBlendingOptions().setSourceFactor(sourceFactor);
					}
					
					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
				
				Spinner spinnerDest = (Spinner) getGame().getActivity().findViewById(R.id.spinner_destination);
				spinnerDest.setAdapter(blendingFactorsAdapter);
				
				spinnerDest.setOnItemSelectedListener(new OnItemSelectedListener() {
					
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						int destFactor = blendingFactors.get(parent.getItemAtPosition(position).toString()).intValue();
						square.getMaterial().getBlendingOptions().setDestinationFactor(destFactor);
					}
					
					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
				
				CharSequence[] blendingEquationsNames = new String[blendingEquations.keySet().size()];
				ArrayAdapter<CharSequence> blendingEquationsAdapter = new ArrayAdapter<CharSequence>(getGame().getActivity(),
						android.R.layout.simple_spinner_item, blendingEquations.keySet().toArray(blendingEquationsNames));
				
				blendingEquationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				
				Spinner spinnerEquation = (Spinner) getGame().getActivity().findViewById(R.id.spinner_equation);
				spinnerEquation.setAdapter(blendingEquationsAdapter);
				
				spinnerEquation.setOnItemSelectedListener(new OnItemSelectedListener() {
					
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						int equation = blendingEquations.get(parent.getItemAtPosition(position).toString()).intValue();
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
	}
	
	@Override
	public void onResume() {
	}
	
	@Override
	public void dispose() {
	}
	
}

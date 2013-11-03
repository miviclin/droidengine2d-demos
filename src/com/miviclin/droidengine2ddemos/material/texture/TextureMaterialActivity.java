package com.miviclin.droidengine2ddemos.material.texture;

import android.content.res.Configuration;

import com.miviclin.droidengine2d.Engine;
import com.miviclin.droidengine2d.EngineActivity;
import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;
import com.miviclin.droidengine2ddemos.R;

public class TextureMaterialActivity extends EngineActivity {

	@Override
	public Engine createEngine(GLView glView) {
		Game game = new TextureMaterialGame(glView, this);
		Engine engine = new Engine.EngineBuilder(game)
				.setMaxFPS(60)
				.build();

		return engine;
	}

	@Override
	public int getContentViewID() {
		return R.layout.activity_game_default;
	}

	@Override
	public int getGLViewID() {
		return R.id.glview;
	}

	@Override
	public int getOrientation() {
		return Configuration.ORIENTATION_PORTRAIT;
	}

}

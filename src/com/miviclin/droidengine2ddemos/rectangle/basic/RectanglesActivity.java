package com.miviclin.droidengine2ddemos.rectangle.basic;

import android.content.res.Configuration;

import com.miviclin.droidengine2d.Engine;
import com.miviclin.droidengine2d.EngineActivity;
import com.miviclin.droidengine2d.Game;
import com.miviclin.droidengine2d.graphics.GLView;
import com.miviclin.droidengine2ddemos.R;

public class RectanglesActivity extends EngineActivity {

	@Override
	public Engine createEngine(GLView glView) {
		Game game = new RectanglesGame(glView, this);
		Engine engine = new Engine.EngineBuilder(game)
				.setMaxFPS(60)
				.build();

		return engine;
	}

	@Override
	public int getContentViewId() {
		return R.layout.activity_game_default;
	}

	@Override
	public int getGLViewId() {
		return R.id.glview;
	}

	@Override
	public int getOrientation() {
		return Configuration.ORIENTATION_LANDSCAPE;
	}

}

package com.miviclin.droidengine2ddemos.batchrendering.multiplematerials;

import android.content.res.Configuration;

import com.miviclin.droidengine2d.Engine;
import com.miviclin.droidengine2d.EngineActivity;
import com.miviclin.droidengine2d.graphics.GLView;
import com.miviclin.droidengine2ddemos.R;

public class MultipleMaterialsBatchRenderingCase3DemoActivity extends EngineActivity {

	@Override
	public Engine createEngine(GLView glView) {
		MultipleMaterialsBatchRenderingDemoGame game = new MultipleMaterialsBatchRenderingDemoGame(glView, this,
				MultipleMaterialsBatchRenderingCase.CASE_3);

		Engine engine = new Engine.EngineBuilder(game)
				.setMaxFPS(60)
				.build();

		return engine;
	}

	@Override
	public int getContentViewId() {
		return R.layout.activity_game_batch_rendering_multiple_materials;
	}

	@Override
	public int getGLViewId() {
		return R.id.glview;
	}

	@Override
	public int getOrientation() {
		return Configuration.ORIENTATION_PORTRAIT;
	}

}

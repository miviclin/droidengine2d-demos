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
package com.miviclin.droidengine2ddemos;

import android.content.Context;

import com.miviclin.droidengine2d.EngineActivity;
import com.miviclin.droidengine2ddemos.animation.loopmodedisabled.AnimationLoopDisabledDemoActivity;
import com.miviclin.droidengine2ddemos.animation.loopmodeenabled.AnimationLoopEnabledDemoActivity;
import com.miviclin.droidengine2ddemos.audio.musicplayback.MusicPlaybackDemoActivity;
import com.miviclin.droidengine2ddemos.audio.soundplayback.SoundPlaybackDemoActivity;
import com.miviclin.droidengine2ddemos.batchrendering.multiplematerials.MultipleMaterialsBatchRenderingCase1DemoActivity;
import com.miviclin.droidengine2ddemos.batchrendering.multiplematerials.MultipleMaterialsBatchRenderingCase2DemoActivity;
import com.miviclin.droidengine2ddemos.batchrendering.multiplematerials.MultipleMaterialsBatchRenderingCase3DemoActivity;
import com.miviclin.droidengine2ddemos.batchrendering.singlematerial.SingleMaterialBatchRenderingDemoActivity;
import com.miviclin.droidengine2ddemos.gamestate.stacking.GameStateStackingDemoActivity;
import com.miviclin.droidengine2ddemos.gamestate.switching.GameStateSwitchingDemoActivity;
import com.miviclin.droidengine2ddemos.input.accelerometer.AccelerometerDemoActivity;
import com.miviclin.droidengine2ddemos.input.key.KeyInputDemoActivity;
import com.miviclin.droidengine2ddemos.input.touch.TouchInputDemoActivity;
import com.miviclin.droidengine2ddemos.material.blendingoptions.BlendingOptionsActivity;
import com.miviclin.droidengine2ddemos.material.color.ColorMaterialActivity;
import com.miviclin.droidengine2ddemos.material.texture.TextureMaterialActivity;
import com.miviclin.droidengine2ddemos.material.texturecolor.TextureColorMaterialActivity;
import com.miviclin.droidengine2ddemos.material.texturehsv.TextureHsvMaterialActivity;
import com.miviclin.droidengine2ddemos.material.transparenttexture.TransparentTextureMaterialActivity;
import com.miviclin.droidengine2ddemos.rectangle.basic.RectanglesActivity;
import com.miviclin.droidengine2ddemos.rectangle.rotation.anchorpoint.AnchorPointRotationActivity;
import com.miviclin.droidengine2ddemos.rectangle.rotation.basic.BasicRotationActivity;
import com.miviclin.droidengine2ddemos.text.customfonts.CustomFontsActivity;
import com.miviclin.droidengine2ddemos.textureregion.TextureRegionFlippingDemoActivity;

public enum Demo {
	RECTANGLES_BASIC(R.string.group_rectangles_basic, RectanglesActivity.class),
	RECTANGLES_ROTATION_BASIC(R.string.group_rectangles_rotation_basic, BasicRotationActivity.class),
	RECTANGLES_ROTATION_ANCHOR_POINT(R.string.group_rectangles_rotation_anchor_point, AnchorPointRotationActivity.class),
	TEXTURE_REGION_FLIPPING(R.string.group_texture_regions_flip_texture_region, TextureRegionFlippingDemoActivity.class),
	TEXT_CUSTOM_FONTS(R.string.group_text_custom_fonts, CustomFontsActivity.class),
	MATERIAL_BLENDING_OPTIONS(R.string.group_materials_blending_options, BlendingOptionsActivity.class),
	MATERIAL_COLOR(R.string.group_materials_color, ColorMaterialActivity.class),
	MATERIAL_TEXTURE(R.string.group_materials_texture, TextureMaterialActivity.class),
	MATERIAL_TEXTURE_COLOR(R.string.group_materials_texture_color, TextureColorMaterialActivity.class),
	MATERIAL_TEXTURE_HSV(R.string.group_materials_texture_hsv, TextureHsvMaterialActivity.class),
	MATERIAL_TRANSPARENT_TEXTURE(R.string.group_materials_transparent_texture, TransparentTextureMaterialActivity.class),
	INPUT_TOUCH(R.string.group_input_touch, TouchInputDemoActivity.class),
	INPUT_KEY(R.string.group_input_key, KeyInputDemoActivity.class),
	INPUT_ACCELEROMETER(R.string.group_input_accelerometer, AccelerometerDemoActivity.class),
	GAME_STATE_SWITCHING(R.string.group_gamestate_management_switching, GameStateSwitchingDemoActivity.class),
	GAME_STATE_STACKING(R.string.group_gamestate_management_stacking, GameStateStackingDemoActivity.class),
	AUDIO_MUSIC_PLAYBACK(R.string.group_audio_music_playback, MusicPlaybackDemoActivity.class),
	AUDIO_SOUND_PLAYBACK(R.string.group_audio_sound_playback, SoundPlaybackDemoActivity.class),
	ANIMATION_LOOP_MODE_ENABLED(R.string.group_animation_loop_mode_enabled, AnimationLoopEnabledDemoActivity.class),
	ANIMATION_LOOP_MODE_DISABLED(R.string.group_animation_loop_mode_disabled, AnimationLoopDisabledDemoActivity.class),
	BATCH_RENDERING_SINGLE_MATERIAL(R.string.group_batch_rendering_single_material,
			SingleMaterialBatchRenderingDemoActivity.class),
	BATCH_RENDERING_MULTIPLE_MATERIALS_CASE_1(R.string.group_batch_rendering_multiple_materials_case_1,
			MultipleMaterialsBatchRenderingCase1DemoActivity.class),
	BATCH_RENDERING_MULTIPLE_MATERIALS_CASE_2(R.string.group_batch_rendering_multiple_materials_case_2,
			MultipleMaterialsBatchRenderingCase2DemoActivity.class),
	BATCH_RENDERING_MULTIPLE_MATERIALS_CASE_3(R.string.group_batch_rendering_multiple_materials_case_3,
			MultipleMaterialsBatchRenderingCase3DemoActivity.class);

	private int resId;
	private Class<? extends EngineActivity> demoActivityClass;

	private Demo(int resId) {
		this.resId = resId;
	}

	private Demo(int resId, Class<? extends EngineActivity> demoActivityClass) {
		this.resId = resId;
		this.demoActivityClass = demoActivityClass;
	}

	public int getResId() {
		return resId;
	}

	public Class<? extends EngineActivity> getDemoActivityClass() {
		return demoActivityClass;
	}

	public String getString(Context context) {
		return context.getString(resId);
	}
}

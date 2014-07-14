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

public enum DemosGroup {
	RECTANGLES(R.string.group_rectangles),
	MATERIALS(R.string.group_materials),
	TEXTURE_REGIONS(R.string.group_texture_regions),
	TEXT(R.string.group_text),
	ANIMATION(R.string.group_animation),
	AUDIO(R.string.group_audio),
	GAME_STATES(R.string.group_gamestate_management),
	INPUT(R.string.group_input),
	BATCH_RENDERING(R.string.group_batch_rendering),
	GAMES(R.string.group_games);

	private int resId;

	private DemosGroup(int resId) {
		this.resId = resId;
	}

	public int getResId() {
		return resId;
	}

	public String getString(Context context) {
		return context.getString(resId);
	}
}

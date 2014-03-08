package com.miviclin.droidengine2ddemos;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ExpandableListData listData = initializeExpandableListData();
		initializeExpandableListView(listData);
	}

	private ExpandableListData initializeExpandableListData() {
		ExpandableListData listData = new ExpandableListData();

		List<Demo> rectanglesDemos = new ArrayList<Demo>();
		rectanglesDemos.add(Demo.RECTANGLES_BASIC);
		rectanglesDemos.add(Demo.RECTANGLES_ROTATION_BASIC);
		rectanglesDemos.add(Demo.RECTANGLES_ROTATION_ANCHOR_POINT);
		listData.addGroup(DemosGroup.RECTANGLES, rectanglesDemos);

		List<Demo> textureRegionDemos = new ArrayList<Demo>();
		textureRegionDemos.add(Demo.TEXTURE_REGION_FLIPPING);
		listData.addGroup(DemosGroup.TEXTURE_REGIONS, textureRegionDemos);

		List<Demo> animationDemos = new ArrayList<Demo>();
		animationDemos.add(Demo.ANIMATION_LOOP_MODE_ENABLED);
		animationDemos.add(Demo.ANIMATION_LOOP_MODE_DISABLED);
		listData.addGroup(DemosGroup.ANIMATION, animationDemos);

		List<Demo> textDemos = new ArrayList<Demo>();
		textDemos.add(Demo.TEXT_CUSTOM_FONTS);
		listData.addGroup(DemosGroup.TEXT, textDemos);

		List<Demo> materialDemos = new ArrayList<Demo>();
		materialDemos.add(Demo.MATERIAL_BLENDING_OPTIONS);
		materialDemos.add(Demo.MATERIAL_COLOR);
		materialDemos.add(Demo.MATERIAL_TEXTURE);
		materialDemos.add(Demo.MATERIAL_TEXTURE_COLOR);
		materialDemos.add(Demo.MATERIAL_TEXTURE_HSV);
		materialDemos.add(Demo.MATERIAL_TRANSPARENT_TEXTURE);
		listData.addGroup(DemosGroup.MATERIALS, materialDemos);

		List<Demo> batchRenderingDemos = new ArrayList<Demo>();
		batchRenderingDemos.add(Demo.BATCH_RENDERING_SINGLE_MATERIAL);
		batchRenderingDemos.add(Demo.BATCH_RENDERING_MULTIPLE_MATERIALS_CASE_1);
		batchRenderingDemos.add(Demo.BATCH_RENDERING_MULTIPLE_MATERIALS_CASE_2);
		batchRenderingDemos.add(Demo.BATCH_RENDERING_MULTIPLE_MATERIALS_CASE_3);
		listData.addGroup(DemosGroup.BATCH_RENDERING, batchRenderingDemos);

		List<Demo> audioDemos = new ArrayList<Demo>();
		audioDemos.add(Demo.AUDIO_MUSIC_PLAYBACK);
		audioDemos.add(Demo.AUDIO_SOUND_PLAYBACK);
		listData.addGroup(DemosGroup.AUDIO, audioDemos);

		List<Demo> inputDemos = new ArrayList<Demo>();
		inputDemos.add(Demo.INPUT_TOUCH);
		inputDemos.add(Demo.INPUT_KEY);
		inputDemos.add(Demo.INPUT_ACCELEROMETER);
		listData.addGroup(DemosGroup.INPUT, inputDemos);

		List<Demo> gameStateManagementDemos = new ArrayList<Demo>();
		gameStateManagementDemos.add(Demo.GAME_STATE_SWITCHING);
		gameStateManagementDemos.add(Demo.GAME_STATE_STACKING);
		listData.addGroup(DemosGroup.GAME_STATES, gameStateManagementDemos);

		return listData;
	}

	private void initializeExpandableListView(final ExpandableListData listData) {
		ExpandableListView expListView = (ExpandableListView) findViewById(R.id.expandablelist_main);
		expListView.setAdapter(new ExpandableListAdapter(this, listData));
		expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPos, int childPos, long id) {
				Demo clickedDemo = listData.getChildren(groupPos).get(childPos);
				launchDemoActivity(clickedDemo);
				return true;
			}
		});
	}

	private void launchDemoActivity(Demo demo) {
		Intent intent = new Intent(MainActivity.this, demo.getDemoActivityClass());
		startActivity(intent);
	}

	private static class ExpandableListAdapter extends BaseExpandableListAdapter {

		private Context context;
		private ExpandableListData listData;

		public ExpandableListAdapter(Context context, ExpandableListData listData) {
			this.context = context;
			this.listData = listData;
		}

		@Override
		public int getGroupCount() {
			return listData.getGroupCount();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return listData.getChildren(groupPosition).size();
		}

		@Override
		public String getGroup(int groupPosition) {
			return listData.getGroup(groupPosition).getString(context);
		}

		@Override
		public String getChild(int groupPosition, int childPosititon) {
			return listData.getChildren(groupPosition).get(childPosititon).getString(context);
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.expandablelist_group, null);
			}

			TextView tvGroup = (TextView) convertView.findViewById(R.id.textview_group_header);
			tvGroup.setText(getGroup(groupPosition));

			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView,
				ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.expandablelist_child, null);
			}

			TextView tvChild = (TextView) convertView.findViewById(R.id.textview_child_text);
			tvChild.setText(getChild(groupPosition, childPosition));
			return convertView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}

	private static class ExpandableListData {

		private List<DemosGroup> groups;
		private EnumMap<DemosGroup, List<Demo>> children;

		public ExpandableListData() {
			this.groups = new ArrayList<DemosGroup>();
			this.children = new EnumMap<DemosGroup, List<Demo>>(DemosGroup.class);
		}

		public void addGroup(DemosGroup group, List<Demo> groupChildren) {
			groups.add(group);
			children.put(group, groupChildren);
		}

		public DemosGroup getGroup(int groupPosition) {
			return groups.get(groupPosition);
		}

		public int getGroupCount() {
			return groups.size();
		}

		public List<Demo> getChildren(int groupPosition) {
			return children.get(groups.get(groupPosition));
		}

	}

}

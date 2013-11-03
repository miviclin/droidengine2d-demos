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

import com.miviclin.droidengine2ddemos.material.blendingoptions.BlendingOptionsActivity;
import com.miviclin.droidengine2ddemos.material.texture.TextureMaterialActivity;
import com.miviclin.droidengine2ddemos.material.texturehsv.TextureHsvMaterialActivity;
import com.miviclin.droidengine2ddemos.rectangle.basic.RectanglesActivity;
import com.miviclin.droidengine2ddemos.rectangle.rotation.anchorpoint.AnchorPointRotationActivity;
import com.miviclin.droidengine2ddemos.rectangle.rotation.basic.BasicRotationActivity;
import com.miviclin.droidengine2ddemos.text.customfonts.CustomFontsActivity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ExpandableListData listData = loadExpandableListData();

		ExpandableListView expListView = (ExpandableListView) findViewById(R.id.expandablelist_main);
		expListView.setAdapter(new ExpandableListAdapter(this, listData));
		expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

			@Override
			public boolean
					onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

				Intent intent = null;
				Demo demo = listData.getChildren(groupPosition).get(childPosition);
				switch (demo) {
				case RECTANGLES_BASIC:
					intent = new Intent(MainActivity.this, RectanglesActivity.class);
					break;
				case RECTANGLES_ROTATION_BASIC:
					intent = new Intent(MainActivity.this, BasicRotationActivity.class);
					break;
				case RECTANGLES_ROTATION_ANCHOR_POINT:
					intent = new Intent(MainActivity.this, AnchorPointRotationActivity.class);
					break;
				case TEXT_CUSTOM_FONTS:
					intent = new Intent(MainActivity.this, CustomFontsActivity.class);
					break;
				case MATERIAL_BLENDING_OPTIONS:
					intent = new Intent(MainActivity.this, BlendingOptionsActivity.class);
					break;
				case MATERIAL_TEXTURE:
					intent = new Intent(MainActivity.this, TextureMaterialActivity.class);
					break;
				case MATERIAL_TEXTURE_HSV:
					intent = new Intent(MainActivity.this, TextureHsvMaterialActivity.class);
					break;
				default:
					break;
				}
				startActivity(intent);
				return true;
			}
		});
	}

	private ExpandableListData loadExpandableListData() {
		ExpandableListData listData = new ExpandableListData();

		List<Demo> rectanglesDemos = new ArrayList<Demo>();
		rectanglesDemos.add(Demo.RECTANGLES_BASIC);
		rectanglesDemos.add(Demo.RECTANGLES_ROTATION_BASIC);
		rectanglesDemos.add(Demo.RECTANGLES_ROTATION_ANCHOR_POINT);
		listData.addGroup(DemosGroup.RECTANGLES, rectanglesDemos);

		List<Demo> textDemos = new ArrayList<Demo>();
		textDemos.add(Demo.TEXT_CUSTOM_FONTS);
		listData.addGroup(DemosGroup.TEXT, textDemos);

		List<Demo> materialDemos = new ArrayList<Demo>();
		materialDemos.add(Demo.MATERIAL_BLENDING_OPTIONS);
		materialDemos.add(Demo.MATERIAL_TEXTURE);
		materialDemos.add(Demo.MATERIAL_TEXTURE_HSV);
		listData.addGroup(DemosGroup.MATERIALS, materialDemos);

		return listData;
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

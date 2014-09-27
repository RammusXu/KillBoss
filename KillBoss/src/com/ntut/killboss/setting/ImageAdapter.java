package com.ntut.killboss.setting;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private int correctEquip = 0;

	private int clickTemp = -1;

	// references to our images
	private Integer[] mResIDArray;
	private Boolean[] mEnableArray;

	public ImageAdapter(Context c, Integer[] resIDArray, Boolean[] enableArray) {
		mContext = c;
		mResIDArray = resIDArray;
		mEnableArray = enableArray;
	}

	@Override
	public int getCount() {
		return mResIDArray.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	// 标识选择的Item
	public void setSeclection(int position) {
		clickTemp = position;
	}

	public void setCorrectEquip(int position) {
		correctEquip = position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;

		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(160, 160));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}

		if (correctEquip == position) {
			imageView.setBackgroundColor(Color.RED);
		} else if (clickTemp == position) {
			imageView.setBackgroundColor(Color.GREEN);
		} else {
			imageView.setBackgroundColor(Color.TRANSPARENT);
		}

		imageView.setImageResource(mResIDArray[position]);
		
		if(!mEnableArray[position]){
			imageView.setAlpha(0.5f);
			imageView.setSelected(false);
		}
		return imageView;
	}
}

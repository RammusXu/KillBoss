package com.ntut.killboss.setting;

import com.ntut.killboss.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;

	// references to our images
	private Integer[] mResIDArray;

	public ImageAdapter(Context c, Integer[] resIDArray) {
		mContext = c;
		mResIDArray = resIDArray;
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

		imageView.setImageResource(mResIDArray[position]);
		imageView.setBackgroundResource(R.drawable.button_selector);
		return imageView;
	}


}

package com.ntut.killboss.menu;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.ntut.killboss.R;
import com.ntut.killboss.setting.ImageAdapter;

public class AchievementFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.achievement_fragment, container,
				false);

		setAdapter();
		findView(v);

		return v;
	}

	private SimpleAdapter simpleAdapter;
	private SimpleAdapter simpleAdapter2;
	private SimpleAdapter simpleAdapter3;
	private ArrayList<HashMap<String, Object>> list1;
	private ArrayList<HashMap<String, Object>> list2;
	private ArrayList<HashMap<String, Object>> list3;

	private void setAdapter() {
		list1 = new ArrayList<HashMap<String, Object>>();
		list2 = new ArrayList<HashMap<String, Object>>();
		list3 = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("iv", R.drawable.boss111);
		hm.put("tv1", "boss111");
		hm.put("tv2", "kill the boss111");
		list1.add(hm);
		list1.add(hm);
		list1.add(hm);
		list2.add(hm);
		list2.add(hm);
		list3.add(hm);

		simpleAdapter = new SimpleAdapter(getActivity(), list1,
				R.layout.achievement_item, new String[] { "iv", "tv1", "tv2" },
				new int[] { R.id.achievement_item_imageView1,
						R.id.achievement_item_textView1,
						R.id.achievement_item_textView2 });

		simpleAdapter2 = new SimpleAdapter(getActivity(), list2,
				R.layout.achievement_item, new String[] { "iv", "tv1", "tv2" },
				new int[] { R.id.achievement_item_imageView1,
						R.id.achievement_item_textView1,
						R.id.achievement_item_textView2 });

		simpleAdapter3 = new SimpleAdapter(getActivity(), list3,
				R.layout.achievement_item, new String[] { "iv", "tv1", "tv2" },
				new int[] { R.id.achievement_item_imageView1,
						R.id.achievement_item_textView1,
						R.id.achievement_item_textView2 });

	}

	ListView listView;

	private void findView(View v) {
		listView = (ListView) v
				.findViewById(R.id.achievement_fragment_listView1);

		listView.setAdapter(simpleAdapter);

		listView.setOnItemClickListener(new GridView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT)
						.show();
			}
		});

		ImageButton ibWeapon = (ImageButton) v
				.findViewById(R.id.achievement_fragment_ImageButton1);
		ibWeapon.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				listView.setAdapter(simpleAdapter);
			}
		});

		ImageButton ibArmor = (ImageButton) v
				.findViewById(R.id.achievement_fragment_ImageButton2);
		ibArmor.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				listView.setAdapter(simpleAdapter2);
			}
		});

		ImageButton ibArmor2 = (ImageButton) v
				.findViewById(R.id.achievement_fragment_ImageButton3);
		ibArmor2.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				listView.setAdapter(simpleAdapter3);
			}
		});
	}
}

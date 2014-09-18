package com.ntut.killboss.menu;

import android.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ntut.killboss.MySaveData;
import com.ntut.killboss.R;
import com.ntut.killboss.setting.ImageAdapter;

public class EquipmentFragment extends Fragment {
	private static final String TAG = "EquipmentFragment";
	private static final String WEAPON_ID = "WEAPON_ID";
	private static final String ARMOR_ID = "ARMOR_ID";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.equipment_fragment, container, false);

		setAdapter();
		findView(v);

		return v;
	}

	Integer[] weaponIDs = { R.drawable.boss111, R.drawable.boss222,
			R.drawable.boss222, R.drawable.boss222, R.drawable.boss222,
			R.drawable.boss222, R.drawable.boss222, R.drawable.boss222,
			R.drawable.boss222, R.drawable.boss222, R.drawable.boss222,
			R.drawable.boss222, };

	Integer[] armorIDs = { R.drawable.boss111, R.drawable.boss111,
			R.drawable.boss111, R.drawable.boss111, R.drawable.boss222,
			R.drawable.boss222, R.drawable.boss222, R.drawable.boss222,
			R.drawable.boss222, R.drawable.boss222, R.drawable.boss222,
			R.drawable.boss222, };

	private int adapterFlag = 0;
	private ImageAdapter weaponAdapter;
	private int weaponID = 0;

	private ImageAdapter armorAdapter;
	private int armorID = 0;

	private void setAdapter() {
		weaponAdapter = new ImageAdapter(getActivity(), weaponIDs);
		armorAdapter = new ImageAdapter(getActivity(), armorIDs);
	}

	private GridView gridView;

	private void findView(View v) {
		gridView = (GridView) v.findViewById(R.id.equipment_fragment_gridView1);

		gridView.setAdapter(weaponAdapter);
		gridViewPerformClick(weaponID);

		gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				v.setSelected(true);

				gridView.requestFocusFromTouch();
				gridView.setSelection(position);
				if (adapterFlag == 0) {
					weaponID = position;
				} else {
					armorID = position;
				}

				Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT)
						.show();
			}
		});

		ImageButton ibWeapon = (ImageButton) v
				.findViewById(R.id.equipment_fragment_ImageButton1);
		ibWeapon.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				adapterFlag = 0;
				gridView.setAdapter(weaponAdapter);
				gridViewPerformClick(weaponID);
			}
		});

		ImageButton ibArmor = (ImageButton) v
				.findViewById(R.id.equipment_fragment_ImageButton2);
		ibArmor.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				adapterFlag = 1;
				gridView.setAdapter(armorAdapter);
				gridViewPerformClick(armorID);
			}
		});

		ImageButton ibChange = (ImageButton) v
				.findViewById(R.id.equipment_fragment_ImageButton3);
		ibChange.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO change EquipmentSetting

			}
		});
	}

	private void gridViewPerformClick(int position) {
		gridView.performItemClick(
				gridView.getAdapter().getView(position, null, null), position,
				gridView.getItemIdAtPosition(position));
	}

	@Override
	public void onResume() {
		MySaveData data = new MySaveData(getActivity());
		weaponID = data.loadDataInt(WEAPON_ID);
		armorID = data.loadDataInt(ARMOR_ID);

		Integer[] obj = (Integer[]) data.readObject(TAG);
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {

				Log.d("DEBUG", "" + obj[i]);
			}
		}

		super.onResume();
	}

	@Override
	public void onPause() {

		MySaveData data = new MySaveData(getActivity());

		data.saveDataInt(WEAPON_ID, weaponID);
		data.saveDataInt(ARMOR_ID, armorID);
		data.saveObject(TAG, weaponIDs);
		super.onPause();
	}

}

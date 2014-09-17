package com.ntut.killboss.menu;

import com.ntut.killboss.R;
import com.ntut.killboss.core.StartGameActivity;
import com.ntut.killboss.setting.ImageAdapter;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

public class EquipmentFragment extends Fragment {

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

	private ImageAdapter weaponAdapter;
	private ImageAdapter armorAdapter;

	private void setAdapter() {
		weaponAdapter = new ImageAdapter(getActivity(), weaponIDs);
		armorAdapter = new ImageAdapter(getActivity(), armorIDs);
	}

	private GridView gridView;

	private void findView(View v) {
		gridView = (GridView) v.findViewById(R.id.equipment_fragment_gridView1);

		gridView.setAdapter(weaponAdapter);

		gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				v.setSelected(true);
				Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT)
						.show();
			}
		});

		ImageButton ibWeapon = (ImageButton) v
				.findViewById(R.id.equipment_fragment_ImageButton1);
		ibWeapon.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				gridView.setAdapter(weaponAdapter);
			}
		});

		ImageButton ibArmor = (ImageButton) v
				.findViewById(R.id.equipment_fragment_ImageButton2);
		ibArmor.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				gridView.setAdapter(armorAdapter);
			}
		});

		// ImageButton ibStart = (ImageButton) v
		// .findViewById(R.id.equipment_fragment_buttonOK);
		// ibStart.setOnClickListener(new ImageButton.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Intent intent = new Intent(getActivity(),
		// StartGameActivity.class);
		// startActivity(intent);
		//
		// }
		// });
	}
}

package com.ntut.killboss.menu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ntut.killboss.Constant;
import com.ntut.killboss.R;
import com.ntut.killboss.setting.EquipmentSetting;
import com.ntut.killboss.setting.ImageAdapter;

public class EquipmentFragment extends Fragment {
	// View
	private View _view;
	private GridView gridView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.equipment_fragment, container, false);

		initAdapter();
		findView();

		return _view;
	}

	private int adapterFlag = 0;
	private ImageAdapter weaponAdapter;
	private int weaponID = 0;

	private ImageAdapter armorAdapter;
	private int armorID = 0;

	private void initAdapter() {
		weaponAdapter = new ImageAdapter(getActivity(), Constant.weaponIDs,
				Constant.temp1);
		weaponID = EquipmentSetting._weapon.get_weaponID();
		armorAdapter = new ImageAdapter(getActivity(), Constant.armorIDs,
				Constant.temp2);
		armorID = EquipmentSetting._armor.get_armorID();
	}

	private void findView() {
		gridView = (GridView) _view
				.findViewById(R.id.equipment_fragment_gridView1);

		weaponAdapter.setCorrectEquip(weaponID);
		gridView.setAdapter(weaponAdapter);

		gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				ImageAdapter adapter = (ImageAdapter) gridView.getAdapter();

				if (adapterFlag == 0) {
					if (MenuActivity.weaponEnable[position]) {
						weaponID = position;
						adapter.setSeclection(position);
						adapter.notifyDataSetChanged();
					}
				} else {
					if (MenuActivity.armorEnable[position]) {
						armorID = position;
						adapter.setSeclection(position);
						adapter.notifyDataSetChanged();
					}
				}

				Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT)
						.show();
			}
		});

		changeToWeaponFragment();
		changeToArmorFragment();
		setChangeButton();
	}

	private void changeToWeaponFragment() {
		ImageButton ibWeapon = (ImageButton) _view
				.findViewById(R.id.equipment_fragment_ImageButton1);
		ibWeapon.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				adapterFlag = 0;
				weaponAdapter.setCorrectEquip(weaponID);
				gridView.setAdapter(weaponAdapter);
			}
		});
	}

	private void changeToArmorFragment() {
		ImageButton ibArmor = (ImageButton) _view
				.findViewById(R.id.equipment_fragment_ImageButton2);
		ibArmor.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				adapterFlag = 1;
				armorAdapter.setCorrectEquip(armorID);
				gridView.setAdapter(armorAdapter);
			}
		});
	}

	private void setChangeButton() {
		ImageButton ibChange = (ImageButton) _view
				.findViewById(R.id.equipment_fragment_ImageButton3);
		ibChange.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Change EquipmentSetting
				if (adapterFlag == 0) {
					weaponAdapter.setCorrectEquip(weaponID);
					weaponAdapter.notifyDataSetChanged();
					EquipmentSetting._weapon.changeWeapon(weaponID);
				} else {
					armorAdapter.setCorrectEquip(armorID);
					armorAdapter.notifyDataSetChanged();
					EquipmentSetting._armor.changeArmor(armorID);
				}

			}
		});
	}
}

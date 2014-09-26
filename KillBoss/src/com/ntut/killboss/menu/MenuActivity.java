package com.ntut.killboss.menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.ntut.killboss.Constant;
import com.ntut.killboss.R;
import com.ntut.killboss.setting.EquipmentSetting;

public class MenuActivity extends Activity {
	public static EquipmentSetting _equipmentSetting;

	// Fragments
	private int _fragmentFlag = 1;
	private StageFragment SF;
	private EquipmentFragment WF;
	private AchievementFragment AF;

	private Button ib1, ib2, ib3;
	
	public static Boolean[] bossEnable, weaponEnable, armorEnable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_activity);
		bossEnable = Constant.temp3;
		weaponEnable = Constant.temp1;
		armorEnable = Constant.temp2;
		
		init();

		// Perform Click
		changeFramgnet(SF);
	}

	private void init() {

		// CLASS
		_equipmentSetting = new EquipmentSetting();

		SF = new StageFragment();
		WF = new EquipmentFragment();
		AF = new AchievementFragment();

		ib1 = (Button) findViewById(R.id.menu_activity_ImageButton1);
		ib1.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (_fragmentFlag != 1) {
					_fragmentFlag = 1;
					changeFramgnet(SF);
				}
			}
		});

		ib2 = (Button) findViewById(R.id.menu_activity_ImageButton2);
		ib2.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (_fragmentFlag != 2) {
					_fragmentFlag = 2;
					changeFramgnet(WF);
				}
			}
		});

		ib3 = (Button) findViewById(R.id.menu_activity_ImageButton3);
		ib3.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (_fragmentFlag != 3) {
					_fragmentFlag = 3;
					changeFramgnet(AF);
				}
			}
		});

	}

	private void changeFramgnet(Fragment f) {

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();

		// Replace whatever is in the fragment_container view with
		// this fragment,and add the transaction to the back stack
		transaction.replace(R.id.menu_activity_fragment1, f);
		// transaction.addToBackStack(null);

		// Commit the transaction
		transaction.commit();
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Leave Game ?");
		dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				MenuActivity.super.onBackPressed();
			}
		});
		dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		dialog.show();
	}

	@Override
	public void onResume() {

		// Load Data
		_equipmentSetting.loadSetting(getApplicationContext());

		super.onResume();
	}

	@Override
	public void onPause() {
		// Save Data
		_equipmentSetting.saveSetting(getApplicationContext());

		super.onPause();
	}
}

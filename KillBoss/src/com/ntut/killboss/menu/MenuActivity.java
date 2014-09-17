package com.ntut.killboss.menu;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.ntut.killboss.R;
import com.ntut.killboss.setting.EquipmentSetting;

public class MenuActivity extends Activity {

	StageFragment SF;
	EquipmentFragment WF;
	AchievementFragment AF;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_activity);


		SF = new StageFragment();
		WF = new EquipmentFragment();
		AF = new AchievementFragment();

		ImageButton ib1 = (ImageButton) findViewById(R.id.menu_activity_ImageButton1);
		ib1.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				changeFramgnet(SF);
			}
		});

		ImageButton ib2 = (ImageButton) findViewById(R.id.menu_activity_ImageButton2);
		ib2.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				changeFramgnet(WF);
			}
		});

		ImageButton ib3 = (ImageButton) findViewById(R.id.menu_activity_ImageButton3);
		ib3.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				changeFramgnet(AF);
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

}

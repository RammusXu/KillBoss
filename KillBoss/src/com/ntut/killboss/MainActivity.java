package com.ntut.killboss;

import com.ntut.killboss.core.StartGameActivity;
import com.ntut.killboss.menu.MenuActivity;
import com.ntut.killboss.setting.EquipmentSetting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	public static EquipmentSetting _equipmentSetting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);
		

		// CLASS
		_equipmentSetting = new EquipmentSetting();

		ImageButton ibStart = (ImageButton) findViewById(R.id.main_activity_start);
		ibStart.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						StartGameActivity.class);
				startActivity(intent);

			}
		});

		ImageButton ibSetting = (ImageButton) findViewById(R.id.main_activity_setting);
		ibSetting.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		
		ImageButton ibMenu = (ImageButton) findViewById(R.id.main_activity_menu);
		ibMenu.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this, MenuActivity.class);
				startActivity(intent);
			}
		});
	}
}

package com.ntut.killboss;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class MainActivityActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);
		
		ImageButton ibStart = (ImageButton) findViewById(R.id.main_activity_start);
		ibStart.setOnClickListener(new ImageButton.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivityActivity.this, HelloGameActivity.class);
				startActivity(intent);
				
			}});
		
		ImageButton ibSetting = (ImageButton) findViewById(R.id.main_activity_setting);
		ibSetting.setOnClickListener(new ImageButton.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}});
	}
}
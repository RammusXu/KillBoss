package com.ntut.killboss;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class HelloGameActivity extends Activity {
	private static final int MOVE_HERO_SPEED = 5;
	HelloGameView _gameview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.game_view);

		_gameview = (HelloGameView) findViewById(R.id.game_view_skill_gameView);
		ImageButton ibRight = (ImageButton) findViewById(R.id.game_view_right);
		ibRight.setOnTouchListener(new ImageButton.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				_gameview.moveHero(MOVE_HERO_SPEED);
				return false;
			}
		});

		ImageButton ibLeft = (ImageButton) findViewById(R.id.game_view_left);
		ibLeft.setOnTouchListener(new ImageButton.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				_gameview.moveHero(-MOVE_HERO_SPEED);
				return false;
			}
		});

	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder ab = new AlertDialog.Builder(this);
		ab.setTitle("Back To Menu?");
		ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				HelloGameActivity.super.onBackPressed();
			}
		});
		ab.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		ab.show();
	}
}

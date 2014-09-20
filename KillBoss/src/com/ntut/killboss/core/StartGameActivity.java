package com.ntut.killboss.core;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.ntut.killboss.R;
import com.ntut.killboss.setting.EquipmentSetting;

public class StartGameActivity extends Activity {
	private static final int MOVE_HERO_SPEED = 5;
	private GameView _gameview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.game_view);

		// VIEW
		_gameview = (GameView) findViewById(R.id.game_view_skill_gameView);
		ImageButton ibRight = (ImageButton) findViewById(R.id.game_view_right);
		ibRight.setOnTouchListener(new ImageButton.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				_gameview.moveHero(MOVE_HERO_SPEED);
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
					Log.d("TouchTest", "Touch down");
				} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					Log.d("TouchTest", "Touch up");
					// TODO write something here
					_gameview.resetImage();
				}
				return false;
			}
		});

		ImageButton ibLeft = (ImageButton) findViewById(R.id.game_view_left);
		ibLeft.setOnTouchListener(new ImageButton.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				_gameview.moveHero(-MOVE_HERO_SPEED);
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
					Log.d("TouchTest", "Touch down");
				} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					Log.d("TouchTest", "Touch up");
					_gameview.resetImage();
				}
				return false;
			}
		});

		ImageButton ibSkillA = (ImageButton) findViewById(R.id.game_view_skill_a);
		ibSkillA.setImageResource(EquipmentSetting._weapon.get_bitmapSkillA());
		ibSkillA.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				_gameview.shotSkillA(1);
			}

		});

		ImageButton ibSkillB = (ImageButton) findViewById(R.id.game_view_skill_b);
		ibSkillB.setImageResource(EquipmentSetting._weapon.get_bitmapSkillB());
		ibSkillB.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				_gameview.shotSkillB(1);
			}

		});
		Button ibJump = (Button) findViewById(R.id.game_view_jump);
		ibJump.setOnClickListener(new Button.OnClickListener() {
			int i;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i++;
				if (i == 1) {
					_gameview.heroJump();
					i = 0;
				} else if (i == 2) {
					i = 0;
					_gameview.heroDoubleJump();
				}
			}
		});
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Back To Menu?");
		dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				StartGameActivity.super.onBackPressed();
			}
		});
		dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		dialog.show();
	}
}

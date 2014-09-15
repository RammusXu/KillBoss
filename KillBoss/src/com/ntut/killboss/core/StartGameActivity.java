package com.ntut.killboss.core;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.ntut.killboss.EquipmentSetting;
import com.ntut.killboss.R;
import com.ntut.killboss.sprite.Sprite;
import com.ntut.killboss.sprite.SpriteHero;

public class StartGameActivity extends Activity {
	private static final int MOVE_HERO_SPEED = 5;
	private GameView _gameview;
	private EquipmentSetting _equipmentSetting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.game_view);

		// CLASS
		_equipmentSetting = new EquipmentSetting();

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
		ibSkillA.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				_gameview.shotSkillA(1);
			}

		});

		ImageButton ibSkillB = (ImageButton) findViewById(R.id.game_view_skill_b);
		ibSkillB.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				_gameview.shotSkillB(1);
			}

		});
		Button ibJump = (Button) findViewById(R.id.game_view_jump);
		ibJump.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				_gameview.jump();
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
				StartGameActivity.super.onBackPressed();
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

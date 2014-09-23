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

import com.ntut.killboss.GameOverDialog;
import com.ntut.killboss.R;
import com.ntut.killboss.setting.EquipmentSetting;

public class StartGameActivity extends Activity {
	private static int MOVE_HERO_SPEED;// 下面33行處初始化，避免NULL POINT
	private static int SLIDE_HERO_SPEED;
	private GameView _gameview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.game_view);

		// VIEW
		_gameview = (GameView) findViewById(R.id.game_view_skill_gameView);

		MOVE_HERO_SPEED = GameView._screenSize.x / 50;
		SLIDE_HERO_SPEED = GameView._screenSize.x / 3;

		ImageButton ibRight = (ImageButton) findViewById(R.id.game_view_right);
		ibRight.setOnLongClickListener(new ImageButton.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				_gameview.slideHero(MOVE_HERO_SPEED);
				return false;
			}
		});
		ibRight.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				_gameview.moveHero(MOVE_HERO_SPEED);
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
		ibRight.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				_gameview.moveHero(MOVE_HERO_SPEED);
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

		// Button ibOver = (Button) findViewById(R.id.game_view_over);
		// ibOver.setOnClickListener(new Button.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// finish();
		// }
		// });
		// if(_gameview._hero.checkSpriteDie()) {
		// ibOver.setVisibility(View.VISIBLE);
		// Log.d("DEBUG","die");
		// } else {
		// ibOver.setVisibility(View.GONE);
		// Log.d("DEBUG","live");
		// }

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

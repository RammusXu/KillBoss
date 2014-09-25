package com.ntut.killboss.core;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.ntut.killboss.R;
import com.ntut.killboss.core.GameView.OnEndOfGameInterface;
import com.ntut.killboss.setting.EquipmentSetting;

public class StartGameActivity extends Activity implements OnEndOfGameInterface{
	private static int MOVE_HERO_SPEED;// 下面33行處初始化，避免NULL POINT
	private static int SLIDE_HERO_SPEED;
	private GameView _gameview;

	private MediaPlayer _mediaPlayer;
	public static boolean _musicFlag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.game_view);

		// VIEW
		_gameview = (GameView) findViewById(R.id.game_view_skill_gameView);
		_gameview.setOnEndOfGame(this);  //传入this，设定自己为回调目标 

		_mediaPlayer = MediaPlayer.create(getApplicationContext(),
				R.raw.background);
		_mediaPlayer.setLooping(true);
		_mediaPlayer.start();

		MOVE_HERO_SPEED = GameView._screenSize.x / 40;
		SLIDE_HERO_SPEED = GameView._screenSize.x / 3;

		ImageButton ibRight = (ImageButton) findViewById(R.id.game_view_right);
		ibRight.setOnTouchListener(new ImageButton.OnTouchListener() {

			long now;
			long now2;
			boolean touchFlag = false;

			long down1;
			long down2;
			boolean downFlag = false;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
					downFlag = !downFlag;
					if (downFlag) {
						down1 = Calendar.getInstance().getTimeInMillis();
					} else {
						down2 = Calendar.getInstance().getTimeInMillis();
					}
					// Button Double Clicked
					if (Math.abs(down2 - down1) < 200) {
						_gameview.flashHeroRight(SLIDE_HERO_SPEED);
					}

					now = Calendar.getInstance().getTimeInMillis();
					touchFlag = true;
				} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					now2 = Calendar.getInstance().getTimeInMillis();
					touchFlag = false;

					// Button Clicked
					if (now2 - now < 100) {
						_gameview.changeHeroDirection(true);
					}
				}

				// Button Pressed
				if (touchFlag) {
					_gameview.moveHeroRight(MOVE_HERO_SPEED);
				}
				return false;
			}
		});

		ImageButton ibLeft = (ImageButton) findViewById(R.id.game_view_left);
		ibLeft.setOnTouchListener(new ImageButton.OnTouchListener() {

			long now;
			long now2;
			boolean touchFlag = false;

			long down1;
			long down2;
			boolean downFlag = false;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
					downFlag = !downFlag;
					if (downFlag) {
						down1 = Calendar.getInstance().getTimeInMillis();
					} else {
						down2 = Calendar.getInstance().getTimeInMillis();
					}
					// Button Double Clicked
					if (Math.abs(down2 - down1) < 200) {
						_gameview.flashHeroLeft(SLIDE_HERO_SPEED);
					}

					now = Calendar.getInstance().getTimeInMillis();
					touchFlag = true;
				} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					now2 = Calendar.getInstance().getTimeInMillis();
					touchFlag = false;

					// Button Clicked
					if (now2 - now < 100) {
						_gameview.changeHeroDirection(false);
					}
				}

				// Button Pressed
				if (touchFlag) {
					_gameview.moveHeroLeft(MOVE_HERO_SPEED);
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
		

		ImageButton ibSetting = (ImageButton) findViewById(R.id.game_view_setting);
		ibSetting.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(_musicFlag) {
					_mediaPlayer.pause();
				} else {
					_mediaPlayer.start();
				}
				_musicFlag = !_musicFlag;
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
	protected void onPause() {
		_mediaPlayer.pause();
		super.onPause();
	}


	@Override
	protected void onDestroy() {
		_mediaPlayer.release();
		super.onDestroy();
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


	@Override
	public void onEndOfGame() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Game Over?");
		dialog.setPositiveButton("Victory", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				StartGameActivity.this.finish();
			}
		});
		dialog.show();
		
	}

}

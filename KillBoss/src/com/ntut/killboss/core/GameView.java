package com.ntut.killboss.core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ntut.killboss.Constant;
import com.ntut.killboss.FunctionUtilities;
import com.ntut.killboss.R;
import com.ntut.killboss.SoundEffect;
import com.ntut.killboss.menu.StageFragment;
import com.ntut.killboss.object.ObjectSkill;
import com.ntut.killboss.sprite.AnimationReduceHP;
import com.ntut.killboss.sprite.Sprite3x4;
import com.ntut.killboss.sprite.SpriteBoss;
import com.ntut.killboss.sprite.SpriteHero;

public class GameView extends SurfaceView {
	private static final String TAG = "GameView";
	private SurfaceHolder _holder;
	private GameThread _gameThread;

	// Sprite
	private ArrayList<Sprite3x4> sprites;
	public static List<AnimationReduceHP> _temps = new ArrayList<AnimationReduceHP>();
	private List<ObjectSkill> _objectSkills = new ArrayList<ObjectSkill>();
	private Bitmap bmpBlood;
	private SpriteHero _hero;
	private SpriteBoss _boss;

	// Global Variables
	public static Point _screenSize;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		_gameThread = new GameThread(this);

		// Init _screenSize
		_screenSize = FunctionUtilities.getDisplaySize(context);

		_boss = new SpriteBoss(GameView.this, FunctionUtilities.createBitmap(
				getResources(), Constant.bossIDs[StageFragment.bossInt]));

		_hero = new SpriteHero(GameView.this, FunctionUtilities.createBitmap(
				getResources(), R.drawable.pichero1));

		bmpBlood = FunctionUtilities.createBitmap(getResources(),
				R.drawable.blood);

		resetAllSprites();

		_holder = getHolder();
		_holder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				try {
					_gameThread.setRunning(true);
					_gameThread.start();
				} catch (Exception e) {
					Log.e(TAG, e.toString());
				}
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean bIfRetry = true;
				_gameThread.setRunning(false);
				while (bIfRetry) {
					try {
						_gameThread.join();

					} catch (Exception e) {
						Log.e(TAG, e.toString());
					}
					bIfRetry = false;
				}
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {

			}
		});

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// synchronized (getHolder()) {
		// for (int i = 0; i < sprites.size(); i++) {
		// Sprite3x4 s = sprites.get(i);
		// if (s.isTouched(event.getX(), event.getY())) {
		// temps.add(new HelloGameTempSprite(temps, this,
		// event.getX(), event.getY(), bmpBlood));
		// sprites.remove(i);
		//
		// if (sprites.size() == 1) {
		// new Handler().postDelayed(new Runnable() {
		// @Override
		// public void run() {
		// resetAllSprites();
		// }
		// }, 1000);
		// }
		//
		// break;
		// }
		// }
		// }

		return super.onTouchEvent(event);
	}

	private void resetAllSprites() {
		sprites = new ArrayList<Sprite3x4>();
		/*
		 * sprites.add(new Sprite3x4(GameView.this, FunctionUtilities
		 * .createBitmap(getResources(), R.drawable.sprite))); sprites.add(new
		 * Sprite3x4(GameView.this, FunctionUtilities
		 * .createBitmap(getResources(), R.drawable.sprite2))); sprites.add(new
		 * Sprite3x4(GameView.this, FunctionUtilities
		 * .createBitmap(getResources(), R.drawable.sprite3))); sprites.add(new
		 * Sprite3x4(GameView.this, FunctionUtilities
		 * .createBitmap(getResources(), R.drawable.sprite4)));
		 */
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);

		for (Sprite3x4 s : sprites) {
			s.onDraw(canvas);
		}

		_boss.onDraw(canvas);
		_hero.onDraw(canvas);

		for (int i = _temps.size() - 1; i >= 0; i--) {
			_temps.get(i).onDraw(canvas);
		}

		// TODO
		for (int i = _objectSkills.size() - 1; i >= 0; i--) {
			_objectSkills.get(i).onDraw(canvas);
			_objectSkills.get(i).hitBoss(_boss, GameView.this);
		}

		super.onDraw(canvas);
	}

	public void moveHero(int moveHeroSpeed) {
		_hero.move(moveHeroSpeed);
	}

	public void shotSkillA(int skillID) {
		// Bitmap bitmap = FunctionUtilities.createBitmap(getResources(),
		// R.drawable.blood);
		final ObjectSkill temp = new ObjectSkill(getContext(), _objectSkills,
				R.drawable.skilla, _hero.get_x() + _hero.get_width()/2, _hero.get_y() + _hero.get_height()/4);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				_hero.skillAgetReady();
			}
		}, 100);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				_hero.skillAshot();
				_objectSkills.add(temp);
				_hero.reduceHP(1);
				hitHero(-1);
			}
		}, 280);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				_hero.resetImage();
			}
		}, 500);
	}

	public void shotSkillB(int skillID) {
		new SoundEffect(getContext(), R.raw.jump);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub 
				_hero.skillBgetReady1();
			}
		}, 150);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				_hero.skillBgetReady2();
			}
		}, 350);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				_hero.skillBshot();
				_hero.increaseHP(2);
				hitHero(+2);
			}
		}, 530);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				_hero.resetImage();
			}
		}, 750);
	}

	private void hitHero(int reduceHP) {

		AnimationReduceHP temp = new AnimationReduceHP(GameView.this, _temps,
				_hero.get_x() + 50, _hero.get_y(), reduceHP);
		_temps.add(temp);
	}

	public void resetImage() {
		_hero.resetImage();
	}

	public void heroJump() {
		// TODO Auto-generated method stub
		//	這個160 應該用_hero.get_height才對
		if (_hero.get_y() + _hero.get_height() < GameView._screenSize.y - Constant.SPACE_TO_BOTTOM) {
			//could not jump untill it touches the ground
		} else if (_hero.get_y() + 160 >= GameView._screenSize.y
				- Constant.SPACE_TO_BOTTOM) {
			_hero.jump(0);
		}
	}

	public void heroDoubleJump() {
		// TODO Auto-generated method stub
		if (_hero.get_y() + _hero.get_height() < GameView._screenSize.y - Constant.SPACE_TO_BOTTOM) {
			//could not jump untill it touches the ground
		} else if (_hero.get_y() + _hero.get_height() >= GameView._screenSize.y
				- Constant.SPACE_TO_BOTTOM) {
			_hero.jump(0);
			_hero.jump(0);
		}
	}

}

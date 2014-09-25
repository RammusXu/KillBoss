package com.ntut.killboss.core;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ntut.killboss.FunctionUtilities;
import com.ntut.killboss.R;
import com.ntut.killboss.SoundEffect;
import com.ntut.killboss.menu.StageFragment;
import com.ntut.killboss.object.ObjectSkill;
import com.ntut.killboss.object.ObjectSkill3;
import com.ntut.killboss.sprite.AnimationReduceHP;
import com.ntut.killboss.sprite.Sprite3x4;
import com.ntut.killboss.sprite.SpriteBackground;
import com.ntut.killboss.sprite.SpriteBoss;
import com.ntut.killboss.sprite.SpriteGameResult;
import com.ntut.killboss.sprite.SpriteHero;

public class GameView extends SurfaceView {
	public interface OnEndOfGameInterface {  
	    public void onEndOfGame();        
	}
	protected OnEndOfGameInterface mOnEndOfGame ; //callback interface   
    public void setOnEndOfGame(OnEndOfGameInterface xOnEndOfGame){  
        mOnEndOfGame = xOnEndOfGame;  
    }  
	
	private static final String TAG = "GameView";
	private SurfaceHolder _holder;
	private GameThread _gameThread;
	private Context _context;

	// Sprite
	private ArrayList<Sprite3x4> sprites;
	public static List<AnimationReduceHP> _animationReduceHP = new ArrayList<AnimationReduceHP>();
	public static List<ObjectSkill> _objectSkillsHero = new ArrayList<ObjectSkill>();
	public static List<ObjectSkill> _objectSkillsBoss = new ArrayList<ObjectSkill>();
	public SpriteHero _hero;
	public SpriteBoss _boss;
	public SpriteBackground _background;
	public SpriteGameResult _gameResult;

	// Global Variables
	public static Point _screenSize;
	public static int _bottomSpace;
	
	

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		_gameThread = new GameThread(this);
		_context = context;

		// Init _screenSize
		_screenSize = FunctionUtilities.getDisplaySize(context);
		_bottomSpace = _screenSize.y / 6;

		// Init sprites
		_background = new SpriteBackground(GameView.this,
				FunctionUtilities.createBitmap(getResources(),
						R.drawable.background1));
		_gameResult = new SpriteGameResult(context, GameView.this);
		
		_boss = new SpriteBoss(context, GameView.this, StageFragment.bossInt);
		_hero = new SpriteHero(context, GameView.this,
				FunctionUtilities.createBitmap(getResources(),
						R.drawable.pichero1), FunctionUtilities.createBitmap(
						getResources(), R.drawable.pichero2));

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

		synchronized (getHolder()) {
			if(_gameResult.isTouched(event.getX(), event.getY())){
				((Activity)_context).finish();
			}
		}

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

		_background.onDraw(canvas);
//		if(_boss.checkSpriteDie()) {
//		} else {
//			((Activity)getContext()).finish();
//		}
		
		_boss.onDraw(canvas);
		_hero.onDraw(canvas);

		for (int i = _animationReduceHP.size() - 1; i >= 0; i--) {
			_animationReduceHP.get(i).onDraw(canvas);
		}

		for (int i = _objectSkillsHero.size() - 1; i >= 0; i--) {
			_objectSkillsHero.get(i).onDraw(canvas);
			_objectSkillsHero.get(i).hitSprite(_boss, GameView.this);
		}

		for (int i = _objectSkillsBoss.size() - 1; i >= 0; i--) {
			_objectSkillsBoss.get(i).onDraw(canvas);
			_objectSkillsBoss.get(i).hitSprite(_hero, GameView.this);
		}
		
		if(_boss.checkSpriteDie()){
			_gameResult.onDraw(canvas, true);
		}
		if(_hero.checkSpriteDie()) {
			_gameResult.onDraw(canvas, false);
		}

		super.onDraw(canvas);
	}

	public void moveHeroRight(int moveHeroSpeed) {
		_hero.moveRight(moveHeroSpeed);
	}

	public void moveHeroLeft(int moveHeroSpeed) {
		_hero.moveLeft(moveHeroSpeed);
	}

	public void flashHeroRight(int moveHeroSpeed) {
		_hero.flashRight(moveHeroSpeed);
	}

	public void flashHeroLeft(int moveHeroSpeed) {
		_hero.flashLeft(moveHeroSpeed);
	}

	public void changeHeroDirection(boolean direction) {
		_hero.set_direction(direction);
	}

	public void shotSkillA(int skillID) {
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

				ObjectSkill temp1 = new ObjectSkill3(getContext(),
						GameView._objectSkillsHero, _hero.get_x(), _hero
								.get_y(), _hero.get_direction());
				ObjectSkill temp2 = new ObjectSkill3(getContext(),
						GameView._objectSkillsHero, _hero.get_x(), _hero
								.get_y() + _hero.get_height(), _hero
								.get_direction());
				GameView._objectSkillsHero.add(temp1);
				GameView._objectSkillsHero.add(temp2);
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

	public void resetImage() {
		_hero.resetImage();
	}

	public void heroJump() {
		// TODO Auto-generated method stub
		if (_hero.get_y() + _hero.get_height() < GameView._screenSize.y
				- GameView._screenSize.y / 6) {
			// SPACE_TO_BOTTOM直接在GameView中設定
			// could not jump untill it touches the ground
		} else if (_hero.get_y() + _hero.get_height() >= GameView._screenSize.y
				- GameView._screenSize.y / 6) {
			if (_hero.get_direction()) {
				_hero.jumpRight();
			} else {
				_hero.jumpLeft();
			}
		}
	}

	public void heroDoubleJump() {
		// TODO Auto-generated method stub
		if (_hero.get_y() + _hero.get_height() < GameView._screenSize.y
				- GameView._screenSize.y / 6) {
			// could not jump untill it touches the ground
		} else if (_hero.get_y() + _hero.get_height() >= GameView._screenSize.y
				- GameView._screenSize.y / 6) {
			if (_hero.get_direction()) {
				_hero.jumpRight();
				_hero.jumpRight();
			} else {
				_hero.jumpLeft();
				_hero.jumpLeft();
			}
		}
	}
	

}

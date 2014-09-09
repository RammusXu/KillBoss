package com.ntut.killboss.sprite;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.ntut.killboss.R;
import com.ntut.killboss.SoundEffect;
import com.ntut.killboss.core.HelloGameView;

public class AnimationReduceHP {
	private List<AnimationReduceHP> temps;
	private float x;
	private float y;
	private Bitmap bmp;

	private int life = 15;
	private int reduceHP = 1;
	private Paint paint = new Paint();

	public AnimationReduceHP(HelloGameView gameView, List<AnimationReduceHP> temps, float x, float y, Bitmap bmp, int reduceHP) {
		this.temps = temps;
		this.x = Math.min(Math.max(x - bmp.getWidth() / 2, 0),
				HelloGameView._screenSize.x - bmp.getWidth());
		this.y = Math.min(Math.max(y - bmp.getHeight() / 2, 0),
				HelloGameView._screenSize.y - bmp.getHeight());
//		this.bmp = bmp;
		this.reduceHP = reduceHP;

		new SoundEffect(gameView.getContext(), R.raw.skill_a);
	}

	private void update() {
		if (--life < 1) {
			temps.remove(this);
		} else {
			if (y > 0) {
				y -= 10;
			}
		}
	}

	public void onDraw(Canvas canvas) {
		update();
		// canvas.drawBitmap(bmp, x, y, null);

		paint.setTextSize(48);
		paint.setColor(Color.RED);
		canvas.drawText("" + reduceHP, x, y, paint);
		// canvas.drawText("" + reduceHP, _x + (_width / 2), _y + (_height / 2),
		// paint);
	}

}

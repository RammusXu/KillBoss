package com.ntut.killboss.core;

import android.graphics.Canvas;
import android.util.Log;

public class GameThread extends Thread {
	private static final String TAG = "HelloGameThread";
	private boolean bIfRunning = false;
	private GameView _view;
	private final long FPS = 30;

	public GameThread(GameView view) {
		super();
		this._view = view;
	}

	public void setRunning(boolean run) {
		this.bIfRunning = run;
	}

	@Override
	public void run() {
		long ticksPS = 1000 / FPS;
		long startTime;
		long sleepTime; // how much time SurfaceHolder getHolder and lock
						// Canvas.

		while (bIfRunning) {
			Canvas c = null;
			startTime = System.currentTimeMillis();

			try {
				c = _view.getHolder().lockCanvas();
				synchronized (_view.getHolder()) {
					_view.onDraw(c);
				}

			} catch (Exception e) {
				Log.e(TAG, e.toString());
			} finally {
				if (c != null) {
					_view.getHolder().unlockCanvasAndPost(c);
				}
			}

			sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
			try {
				if (sleepTime > 0) {
					sleep(sleepTime);
				} else {
					sleep(10);
				}
			} catch (Exception e) {
				Log.e(TAG, e.toString());
			}
		}
		super.run();
	}
}

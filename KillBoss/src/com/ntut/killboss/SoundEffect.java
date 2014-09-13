package com.ntut.killboss;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundEffect extends MediaPlayer {
	/**
	 * Create a Sound
	 * @param context _gameview.getContext()
	 * @param rawID R.raw.xxxx
	 */
	public SoundEffect(Context context, int rawID) {
		MediaPlayer mp = MediaPlayer.create(context, rawID);
		mp.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}

		});
		mp.start();
	}
}

package com.ntut.killboss;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundEffect extends MediaPlayer {
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

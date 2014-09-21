package com.ntut.killboss;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class GameOverDialog extends AlertDialog {

	public GameOverDialog(final Context context) {
		super(context);
		setMessage("Game Over");
		setButton(BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				((Activity) context).finish();
			}
		});
	}

}

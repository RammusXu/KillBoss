package com.ntut.killboss.setting;

import android.content.Context;
import android.content.SharedPreferences;

public class MySaveData {

	private static final String KILL_BOSS = "KILL_BOSS";

	public static void saveData(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(KILL_BOSS,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor se = sp.edit();
		se.putString(key, value);
		se.commit();
	}

	public static String loadData(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(KILL_BOSS,
				Context.MODE_PRIVATE);
		return sp.getString(key, "");
	}

}

package com.ntut.killboss;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;

public class MySaveData {
	private static final String KILL_BOSS = "KILL_BOSS";
	Context mContext;

	public MySaveData(Context context) {
		mContext = context;
	}

	public void saveData(String key, String value) {
		SharedPreferences sp = mContext.getSharedPreferences(KILL_BOSS,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor se = sp.edit();
		se.putString(key, value);
		se.commit();
	}

	public String loadData(String key) {
		SharedPreferences sp = mContext.getSharedPreferences(KILL_BOSS,
				Context.MODE_PRIVATE);
		return sp.getString(key, "");
	}
	

	public void saveDataInt(String key, int value) {
		SharedPreferences sp = mContext.getSharedPreferences(KILL_BOSS,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor se = sp.edit();
		se.putInt(key, value);
		se.commit();
	}

	/**
	 * @return if null return 0
	 */
	public int loadDataInt(String key) {
		SharedPreferences sp = mContext.getSharedPreferences(KILL_BOSS,
				Context.MODE_PRIVATE);
		return sp.getInt(key, 0);
	}

	public void saveObject(String filename, Object object) {

		try {
			FileOutputStream fos = mContext.openFileOutput(filename,
					Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object readObject(String filename) {
		Object returnlist = null;
		try {
			FileInputStream inputStream = mContext.openFileInput(filename);
			inputStream.read();
			inputStream.close();

			FileInputStream fis = mContext.openFileInput(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			returnlist = ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnlist;
	}
	
	// Integer[] obj = (Integer[]) data.readObject(TAG);
	// if (obj != null) {
	// for (int i = 0; i < obj.length; i++) {
	//
	// Log.d("DEBUG", "" + obj[i]);
	// }
	// }
}

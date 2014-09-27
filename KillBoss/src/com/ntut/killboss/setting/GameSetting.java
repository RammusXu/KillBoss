package com.ntut.killboss.setting;

import com.ntut.killboss.Constant;
import com.ntut.killboss.MySaveData;

import android.content.Context;
import android.util.Log;

public class GameSetting {
	private static final String TAG = "GameSetting";

	// Equipment
	private static final String SAVE_WEAPON = "SAVE_WEAPON";
	private static final String SAVE_ARMOR = "SAVE_ARMOR";
	public static Weapon _weapon;
	public static Armor _armor;

	// Enable
	private static final String BOSS_ENABLE = "BOSS_ENABLE";
	private static final String WEAPON_ENABLE = "WEAPON_ENABLE";
	private static final String ARMOR_ENABLE = "ARMOR_ENABLE";
	public static Boolean[] _bossEnable, _weaponEnable, _armorEnable;

	public GameSetting() {
		_weapon = new Weapon();
		_armor = new Armor();

		_bossEnable = Constant.tempBOss;
		_weaponEnable = Constant.temp1;
		_armorEnable = Constant.temp2;
	}

	public void changeWeapon(int weaponID) {
		_weapon.changeWeapon(weaponID);
	}

	public void changeArmor(int armorID) {
		_armor.changeArmor(armorID);
	}

	/**
	 * Put it in OnPause()
	 */
	public static void saveSetting(Context context) {
		Log.d(TAG, "saveSetting");
		
		MySaveData data = new MySaveData(context);
		data.saveDataInt(SAVE_WEAPON, _weapon.get_weaponID());
		data.saveDataInt(SAVE_ARMOR, _armor.get_armorID());

		data.saveObject(BOSS_ENABLE, _bossEnable);

	}

	/**
	 * Put it in OnResume()
	 */
	public void loadSetting(Context context) {
		Log.d(TAG, "loadSetting");
		
		MySaveData data = new MySaveData(context);
		int weaponID = data.loadDataInt(SAVE_WEAPON);
		if (weaponID > 0) {
			_weapon.changeWeapon(weaponID);
		}

		int armorID = data.loadDataInt(SAVE_ARMOR);
		if (weaponID > 0) {
			_armor.changeArmor(armorID);
		}

		_bossEnable = (Boolean[]) data.loadObject(BOSS_ENABLE);
		if (_bossEnable == null) {

			Log.d(TAG, "_bossEnable == null");
			_bossEnable = new Boolean[Constant.bossIDs.length];
			_bossEnable[0] = true;
			for (int i = 1; i < _bossEnable.length; i++) {
				Log.d(TAG, "_bossEnable." + i + "= false");
				_bossEnable[i] = false;
			}
		}
	}

}

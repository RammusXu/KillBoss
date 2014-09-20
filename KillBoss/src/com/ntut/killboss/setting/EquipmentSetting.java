package com.ntut.killboss.setting;

import com.ntut.killboss.MySaveData;

import android.content.Context;

public class EquipmentSetting {
	private static final String SAVE_WEAPON = "SAVE_WEAPON";
	private static final String SAVE_ARMOR = "SAVE_ARMOR";
	public static Weapon _weapon;
	public static Armor _armor;

	public EquipmentSetting() {
		_weapon = new Weapon();
		_armor = new Armor();
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
	public void saveSetting(Context context) {
		MySaveData data = new MySaveData(context);
		data.saveDataInt(SAVE_WEAPON, _weapon.get_weaponID());
		data.saveDataInt(SAVE_ARMOR, _armor.get_armorID());

	}

	/**
	 * Put it in OnResume()
	 */
	public void loadSetting(Context context) {
		MySaveData data = new MySaveData(context);
		int weaponID = data.loadDataInt(SAVE_WEAPON);
		if (weaponID > 0) {
			_weapon.changeWeapon(weaponID);
		}

		int armorID = data.loadDataInt(SAVE_ARMOR);
		if (weaponID > 0) {
			_armor.changeArmor(armorID);
		}
	}

}

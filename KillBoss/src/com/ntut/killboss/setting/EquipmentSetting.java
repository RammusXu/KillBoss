package com.ntut.killboss.setting;

public class EquipmentSetting {
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

}

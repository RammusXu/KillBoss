package com.ntut.killboss.setting;

import com.ntut.killboss.R;

public class Weapon {
	private Property _property;
	private int _weaponID = 1;

	private int _power;
	private int _bitmapSkillA;
	private int _bitmapSkillB;

	public enum Property {
		None, Ice, Fire, Thunder
	}

	public Weapon() {
		changeWeapon(0);
	}

	public void changeWeapon(int weaponID) {
		_weaponID = weaponID;
		switch (weaponID) {
		case 1:
			_property = Property.None;
			_power = 1;
			_bitmapSkillA = R.drawable.skill_button_2;
			_bitmapSkillB = R.drawable.skill_button_3;
			break;

		case 2:
			_property = Property.Ice;
			_power = 3;
			_bitmapSkillA = R.drawable.skill_button_3;
			_bitmapSkillB = R.drawable.skill_button_4;
			break;

		case 3:
			_property = Property.Fire;
			_power = 5;
			_bitmapSkillA = R.drawable.skill_button_4;
			_bitmapSkillB = R.drawable.skill_button_1;
			break;

		default:
			_property = Property.None;
			_power = 1;
			_bitmapSkillA = R.drawable.skill_button_1;
			_bitmapSkillB = R.drawable.skill_button_2;
			break;
		}
	}

	public int get_weaponID() {
		return _weaponID;
	}

	public Property get_property() {
		return _property;
	}

	public int get_power() {
		return _power;
	}

	public int get_bitmapSkillA() {
		return _bitmapSkillA;
	}

	public int get_bitmapSkillB() {
		return _bitmapSkillB;
	}

}

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
			_bitmapSkillA = R.drawable.skill_b;
			_bitmapSkillB = R.drawable.skill_c;
			break;

		case 2:
			_property = Property.Ice;
			_power = 3;
			_bitmapSkillA = R.drawable.skill_c;
			_bitmapSkillB = R.drawable.skill_d;
			break;

		case 3:
			_property = Property.Fire;
			_power = 5;
			_bitmapSkillA = R.drawable.skill_d;
			_bitmapSkillB = R.drawable.skill_a;
			break;

		default:
			_property = Property.None;
			_power = 1;
			_bitmapSkillA = R.drawable.skill_a;
			_bitmapSkillB = R.drawable.skill_b;
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

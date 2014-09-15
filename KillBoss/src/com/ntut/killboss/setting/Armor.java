package com.ntut.killboss.setting;


public class Armor {

	private int _healthPoint;

	public Armor() {
		changeArmor(1);
	}

	public void changeArmor(int armorID) {
		switch (armorID) {
		case 1:
			_healthPoint = 5;
			break;

		case 2:
			_healthPoint = 10;
			break;

		case 3:
			_healthPoint = 15;
			break;

		}
	}

	public int get_healthPoint() {
		return _healthPoint;
	}

}

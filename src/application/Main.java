package application;

import classes.Enchantments;
import classes.Images;
import classes.Inventory;
import classes.Settings;
import debug.Debug;
import interfaces.fxMain;
import util.system;

public class Main {

	public static final String version = "0.0.1";

	public static void main(String[] args) {
		Debug.setCrashReporting();
		system.loadData();
		Settings.loadSettings();
		Images.load();
		Inventory.load();
		Enchantments.load();

		fxMain.open(args);
	}
}
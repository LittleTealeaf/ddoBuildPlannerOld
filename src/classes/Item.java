package classes;

import java.util.List;

public class Item {
	
	public String name;
	public int minLevel;
	
	public Armor armor;
	public Weapon weapon;
	
	
	public double baseValue;
	public double weight;
	
	public List<String> dropLocations;
	
	public Item() {}
	
	
	//Sub Classes
	public static class Armor {
		public String armorType;
		public int armorBonus;
		public int maxDexBonus;
		public int armorCheckPenalty;
		public int spellFailure;
		public int damageReduction;
		
		public Armor() {}
		public Armor(String type, int bonus, int maxDex, int checkPenalty, int spellFail, int damageReduction) {
			armorType = type;
			armorBonus = bonus;
			maxDexBonus = maxDex;
			armorCheckPenalty = checkPenalty;
			spellFailure = spellFail;
		}
	}
	public static class Weapon {
		public Dice attackRoll;
		
		public List<String> damageTypes;
		
		public int critRange;
		public int critMultiplier;
		
		
		public Weapon() {}
		public Weapon(Dice attack, int CritRange, int CritMultiplier, List<String> DamageTypes) {
			attackRoll = attack;
			critRange = CritRange;
			critMultiplier = CritMultiplier;
			damageTypes = DamageTypes;
		}
		
		
		public double getBaseDamage() {
			double critChance = (double) (21 - critRange) / 20;
			double critDamage = critChance * critMultiplier * attackRoll.getAverage();
			
			return attackRoll.getAverage() + critDamage;
		}
	}
}

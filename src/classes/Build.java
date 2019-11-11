package classes;

import java.io.Serializable;
import java.util.List;

public class Build implements Serializable {
	//TODO implement Serializable ID to classes
	
	
	public List<Gear> gearSets;
	
	public class Gear implements Serializable {
		public Item goggles;
		public Item helmet;
		public Item necklace;
		public Item trinket;
		public Item armor;
		public Item cloak;
		public Item bracers;
		public Item belt;
		public Item ring1;
		public Item ring2;
		public Item boots;
		public Item gloves;
		
		public Gear() {}
		public Gear(Item Goggles, Item Helmet, Item Necklace, Item Trinket, Item Armor, Item Cloak, Item Bracers, Item Belt, Item Ring1, Item Ring2, Item Boots, Item Gloves) {
			goggles = Goggles;
			helmet = Helmet;
			necklace = Necklace;
			trinket = Trinket;
			armor = Armor;
			cloak = Cloak;
			bracers = Bracers;
			belt = Belt;
			ring1 = Ring1;
			ring2 = Ring2;
			boots = Boots;
			gloves = Gloves;
		}
	}
}
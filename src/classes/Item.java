package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import interfaces.fxItems;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.system;

public class Item {

	private transient String oldName;
	private String name;
	private String type;
	private String description;
	private String proficiency;
	private String bindStatus;
	private String material;
	private int minLevel;
	private int absoluteMinLevel;
	private double hardness;
	private double durability;
	private double weight;
	private List<Enchantment> enchantments;
	private List<String> equipSlots;

	// Armor Values
	private Armor armor;

	private class Armor {
		public Armor() {
			armorType = "";
		}

		public boolean isEmpty() {
			return armorType.contentEquals("") && armorBonus == 0 && maxDex == 0 && checkPenalty == 0 && spellFailure == 0;
		}

		public String armorType;
		public int armorBonus;
		public int maxDex;
		public int checkPenalty;
		public double spellFailure;
	}

	// Weapon Values
	private Weapon weapon;

	private class Weapon {
		public Weapon() {
			damageTypes = new ArrayList<String>();
			damage = new Dice();
			lowCritRoll = 20;
			critMultiplier = 2;
		}

		public boolean isEmpty() {
			return damage.isDefault() && damageTypes.size() == 0 && lowCritRoll == 0 && critMultiplier == 0.0;
		}

		private Dice damage;
		private List<String> damageTypes;
		private int lowCritRoll;
		private double critMultiplier;
	}

	public Item() {
		this("");
	}

	public Item(String name) {
		this.name = name;
		oldName = name;
		enchantments = new ArrayList<Enchantment>();
		equipSlots = new ArrayList<String>();
	}

	public boolean saveItem() {
		cleanItem();

		Images.renameImage(getImageName(oldName), getImageName());
		Images.renameImage(getImageName(oldName), getImageName());

		if(!(name == null || name.contentEquals(""))) {
			Items.saveItem(this);
			oldName = name + "";
			fxItems.updateTable();
			return true;
		} else return false;
	}

	public void deleteItem() {
		if(Settings.items.warnOnDelete) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete " + name + "?");
			alert.setHeaderText("Delete Item?");
			alert.setContentText("Do you really want to delete the following item? \n   " + name);

			if(alert.showAndWait().get().getButtonData() != ButtonData.OK_DONE) return;
		}

		if(Settings.items.deleteImages) {
			Images.deleteImage(getImageName());
			Images.deleteImage(getIconName());
		}

		system.getAppFile("items", name + ".json").delete();

		fxItems.updateTable();
	}

	public void cleanItem() {
		if(armor != null && armor.isEmpty()) armor = null;
		if(weapon != null && weapon.isEmpty()) weapon = null;
		minLevel = Math.max(absoluteMinLevel, minLevel);
		// TODO clear empty fields in damage types
	}

	public String getImageName() {
		return getImageName(name);
	}

	public String getImageName(String name) {
		return name + ".image";
	}

	public String getIconName() {
		return getIconName(name);
	}

	public String getIconName(String name) {
		return name + ".icon";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProficiency() {
		return proficiency;
	}

	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}

	public String getBindStatus() {
		return bindStatus;
	}

	public void setBindStatus(String bindStatus) {
		this.bindStatus = bindStatus;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Image getIcon() {
		return Images.getImage(getIconName());
	}

	public ImageView getIconView() {
		return new ImageView(getIcon());
	}

	public ImageView getIconViewSmall() {
		ImageView r = new ImageView(getIcon());
		double size = 40;
		r.setFitHeight(size);
		r.setFitWidth(size);
		r.setPreserveRatio(true);

		return r;
	}

	public void setIcon(String iconURL) {
		if(iconURL.contentEquals("")) {
			Images.deleteImage(getIconName());
			return;
		}
		if(iconURL.contentEquals(getIconName())) return;
		Images.saveImage(getIconName(), iconURL);
	}

	public Image getImage() {
		return Images.getImage(getImageName());
	}

	public ImageView getImageView() {
		return new ImageView(getImage());
	}

	public void setImage(String imageURL) {
		if(imageURL.contentEquals("")) {
			Images.deleteImage(getImageName());
			return;
		}
		
		if(imageURL.contentEquals(getImageName())) return;
		
		Images.saveImage(getImageName(), imageURL);
	}

	public int getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}

	public int getAbsoluteMinLevel() {
		return absoluteMinLevel;
	}

	public void setAbsoluteMinLevel(int absoluteMinLevel) {
		this.absoluteMinLevel = absoluteMinLevel;
	}

	public double getHardness() {
		return hardness;
	}

	public void setHardness(double hardness) {
		this.hardness = hardness;
	}

	public double getDurability() {
		return durability;
	}

	public void setDurability(double durability) {
		this.durability = durability;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public List<Enchantment> getEnchantments() {
		return enchantments;
	}

	public void setEnchantments(List<Enchantment> enchantments) {
		this.enchantments = enchantments;
	}

	public List<String> getEquipSlots() {
		return equipSlots;
	}

	public void setEquipSlots(List<String> equipSlots) {
		this.equipSlots = equipSlots;
	}

	public void removeEquipSlot(String slot) {
		while(equipSlots.contains(slot)) equipSlots.remove(slot);
	}

	public void addEquipSlot(String slot) {
		if(!equipSlots.contains(slot)) equipSlots.add(slot);
	}

	public void setEquipSlot(String slot, boolean equipped) {
		if(equipped) addEquipSlot(slot);
		else removeEquipSlot(slot);
	}

	public String getArmorType() {
		if(armor == null) return "";
		return armor.armorType;
	}

	public void setArmorType(String armorType) {
		if(armor == null) armor = new Armor();
		this.armor.armorType = armorType;
	}

	public int getArmorBonus() {
		if(armor == null) return 0;
		return armor.armorBonus;
	}

	public void setArmorBonus(int armorBonus) {
		if(armor == null) armor = new Armor();
		this.armor.armorBonus = armorBonus;
	}

	public int getMaxDex() {
		if(armor == null) return 0;
		return armor.maxDex;
	}

	public void setMaxDex(int maxDex) {
		if(armor == null) armor = new Armor();
		this.armor.maxDex = maxDex;
	}

	public int getCheckPenalty() {
		if(armor == null) return 0;
		return armor.checkPenalty;
	}

	public void setCheckPenalty(int checkPenalty) {
		if(armor == null) armor = new Armor();
		this.armor.checkPenalty = checkPenalty;
	}

	public double getSpellFailure() {
		if(armor == null) return 0;
		return armor.spellFailure;
	}

	public void setSpellFailure(double spellFailure) {
		if(armor == null) armor = new Armor();
		this.armor.spellFailure = spellFailure;
	}

	public Dice getDamage() {
		if(weapon == null) return new Dice();
		return weapon.damage;
	}

	public void setDamage(Dice damage) {
		if(weapon == null) weapon = new Weapon();
		this.weapon.damage = damage;
	}

	public List<String> getDamageTypes() {
		if(weapon == null) return null;
		return weapon.damageTypes;
	}

	public String getDamageTypeText() {
		if(weapon == null) return "";
		String r = "";
		for(String l : getDamageTypes()) {
			if(!r.contentEquals("")) r += "\n";
			r += l;
		}
		return r;
	}

	public void setDamageTypes(List<String> damageTypes) {
		if(weapon == null) weapon = new Weapon();
		this.weapon.damageTypes = damageTypes;
	}

	public void setDamageTypesText(String text) {
		setDamageTypes(Arrays.asList(text.split("\n")));
	}

	public int getLowCritRoll() {
		if(weapon == null) return 20;
		return weapon.lowCritRoll;
	}

	public void setLowCritRoll(int lowCritRoll) {
		if(weapon == null) weapon = new Weapon();
		this.weapon.lowCritRoll = lowCritRoll;
	}

	public double getCritMultiplier() {
		if(weapon == null) return 2;
		return weapon.critMultiplier;
	}

	public void setCritMultiplier(double critMultiplier) {
		if(weapon == null) weapon = new Weapon();
		this.weapon.critMultiplier = critMultiplier;
	}

}
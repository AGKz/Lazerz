package agkz.mods.lazerz.common;

import net.minecraft.item.ItemStack;

public class LazerValues {
	
	/* Max Charge Values */
	int MAX_CHARGE_MARK2, MAX_CHARGE_BIO, MAX_CHARGE_ENDER, MAX_CHARGE_MILITARY, MAX_CHARGE_UTILITY,  MAX_CHARGE_HANDGUN;

	/* Tier Values */
	int TIER_MARK2, TIER_BIO, TIER_ENDER, TIER_MILITARY, TIER_UTILITY,  TIER_HANDGUN;

	/* Should provide energy like a battery? */
	boolean PROVIDE_ENERGY_MARK2, PROVIDE_ENERGY_BIO, PROVIDE_ENERGY_ENDER, PROVIDE_ENERGY_MILITARY, PROVIDE_ENERGY_UTILITY, PROVIDE_ENERGY_HANDGUN;
	
	public LazerValues() {
		/* Max Charge Values */
		this.MAX_CHARGE_MARK2 = 200000;
		this.MAX_CHARGE_BIO = 200000; 
		this.MAX_CHARGE_ENDER = 200000; 
		this.MAX_CHARGE_MILITARY = 300000;
		this.MAX_CHARGE_UTILITY = 200000; 
		this.MAX_CHARGE_HANDGUN = 20000;
	
		/* Tier Values */
		this.TIER_MARK2 = 3;
		this.TIER_BIO = 3; 
		this.TIER_ENDER = 3; 
		this.TIER_MILITARY = 4;
		this.TIER_UTILITY = 3; 
		this.TIER_HANDGUN = 1;
	
		/* Should provide energy like a battery? */
		this.PROVIDE_ENERGY_MARK2 = true;
		this.PROVIDE_ENERGY_BIO = false; 
		this.PROVIDE_ENERGY_ENDER = false; 
		this.PROVIDE_ENERGY_MILITARY = false;
		this.PROVIDE_ENERGY_UTILITY = true; 
		this.PROVIDE_ENERGY_HANDGUN = false;
	}
	
	
	public int getMaxChargeForLazerType(int lazerType) {
		switch(lazerType) {
		case LazerType.BIOLOGICAL: return this.MAX_CHARGE_BIO;
		case LazerType.ENDER: return this.MAX_CHARGE_ENDER;
		case LazerType.HANDGUN: return this.MAX_CHARGE_HANDGUN;
		case LazerType.MARK2: return this.MAX_CHARGE_MARK2;
		case LazerType.MILITARY: return this.MAX_CHARGE_MILITARY;
		case LazerType.UTILITY: return this.MAX_CHARGE_UTILITY;
		default: return 1;
		}
	}
	
	/* Tier Choosers */
	
	public int getTierForLazerType(int lazerType) {
		switch(lazerType) {
		case LazerType.BIOLOGICAL: return this.TIER_BIO;
		case LazerType.ENDER: return this.TIER_ENDER;
		case LazerType.HANDGUN: return this.TIER_HANDGUN;
		case LazerType.MARK2: return this.TIER_MARK2;
		case LazerType.MILITARY: return this.TIER_MILITARY;
		case LazerType.UTILITY: return this.TIER_UTILITY;
		default: return 2;
		}
	}
	
	public boolean shouldProvideEnergyForLazerType(int lazerType) {
		switch(lazerType) {
		case LazerType.BIOLOGICAL: return this.PROVIDE_ENERGY_BIO;
		case LazerType.ENDER: return this.PROVIDE_ENERGY_ENDER;
		case LazerType.HANDGUN: return this.PROVIDE_ENERGY_HANDGUN;
		case LazerType.MARK2: return this.PROVIDE_ENERGY_MARK2;
		case LazerType.MILITARY: return this.PROVIDE_ENERGY_MILITARY;
		case LazerType.UTILITY: return this.PROVIDE_ENERGY_UTILITY;
		default: return false;
		}
	}
}

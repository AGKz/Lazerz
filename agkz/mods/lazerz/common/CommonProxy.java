package agkz.mods.lazerz.common;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import agkz.mods.lazerz.common.items.BurntNetherStarItem;
import agkz.mods.lazerz.common.items.ChromaCast;
import agkz.mods.lazerz.common.items.LazerHelmet;
import agkz.mods.lazerz.common.items.LazerItems;
import agkz.mods.lazerz.common.items.LazerMetaItems;
import agkz.mods.lazerz.common.lasers.BiologicalLazer;
import agkz.mods.lazerz.common.lasers.CheatLazer;
import agkz.mods.lazerz.common.lasers.EnderLazer;
import agkz.mods.lazerz.common.lasers.LazerHandgun;
import agkz.mods.lazerz.common.lasers.MilitaryLazerGun;
import agkz.mods.lazerz.common.lasers.MiningLazerMK2;
import agkz.mods.lazerz.common.lasers.UtilityLazer;

public class CommonProxy
{
	public static LazerValues lazervalues;
	
	public static EnumArmorMaterial LazerArmor;
	
	public static Item basicItems;
	
	public void init() {
		
	}
	
	public void preInit(Configuration config) {
		lazervalues = new LazerValues();

		LazerArmor = EnumHelper.addArmorMaterial("Lazer Armor", 25, new int[]{3, 10, 6, 3}, 1);
		
		LazerItems.burntNetherStar = new ItemStack(new BurntNetherStarItem(config, InternalName.burntNetherStar));
		LazerItems.chromaCast = new ItemStack(new ChromaCast(config, InternalName.chromaCast));
		
		basicItems = new LazerMetaItems(config).setUnlocalizedName("ItemLazer");
				
		LazerItems.lazerLens = new ItemStack(basicItems, 1, 0);
		LazerItems.advancedLapisPlate = new ItemStack(basicItems, 1, 1);
		
		LazerItems.HelmetMark2 = new ItemStack(new LazerHelmet(config, InternalName.HelmetMark2, this.addArmor("LazerArmor"), LazerType.MARK2));
		
		LazerItems.MiningLazerMK2 = new ItemStack(new MiningLazerMK2(config, InternalName.MiningLazerMK2));
		LazerItems.militaryLazerGun = new ItemStack(new MilitaryLazerGun(config, InternalName.militaryLazerGun));
		LazerItems.biologicalLazer = new ItemStack(new BiologicalLazer(config, InternalName.biologicalLazer));
		LazerItems.EnderLazer = new ItemStack(new EnderLazer(config, InternalName.EnderLazer));
		LazerItems.cheatLazer = new ItemStack(new CheatLazer(config, InternalName.cheatLazer));
		LazerItems.lazerHandGun = new ItemStack(new LazerHandgun(config, InternalName.lazerHandGun));
		LazerItems.utilityLazer = new ItemStack(new UtilityLazer(config, InternalName.utilityLazer));
	}

	public int addArmor(String armor) {
		return 0;
	}
} 

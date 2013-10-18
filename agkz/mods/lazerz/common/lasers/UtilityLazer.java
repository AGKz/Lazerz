package agkz.mods.lazerz.common.lasers;

import ic2.api.item.ElectricItem;
import agkz.mods.lazerz.common.InternalName;
import agkz.mods.lazerz.common.LazerType;
import agkz.mods.lazerz.common.items.MiningLazerBase;
import agkz.mods.lazerz.common.items.LazerItems;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;

public class UtilityLazer extends MiningLazerBase {

	public UtilityLazer(Configuration config, InternalName internalName)
	{
	    super(config, internalName, LazerItems.utilityLazer, LazerType.UTILITY);
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(world.isRemote) return itemstack;
		
	    if (ElectricItem.manager.use(itemstack, 100, player)) {
	    	shootLaser(world, player, 10.0F, 5.0F, 100, false, false, LazerType.UTILITY);	
	    }
	    return itemstack;
	    
	}

}

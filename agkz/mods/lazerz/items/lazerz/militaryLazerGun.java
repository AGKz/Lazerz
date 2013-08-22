package agkz.mods.lazerz.items.lazerz;

import ic2.api.item.ElectricItem;
import agkz.mods.lazerz.InternalName;
import agkz.mods.lazerz.items.MiningLazerBase;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;

public class militaryLazerGun extends MiningLazerBase {

	public militaryLazerGun(Configuration config, InternalName internalName)
	{
	    super(config, internalName, 1000000, 2, false);
	}
	

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(world.isRemote) return itemstack;
		
	    if (ElectricItem.manager.use(itemstack, 100, player)) {
	    	shootLaser(world, player, itemstack, 6, 10.0F, 5.0F, 2147483647);	
	    }
	    return itemstack;
	    
	}

}

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

public class nuclearLazer extends MiningLazerBase {

	public nuclearLazer(Configuration config, InternalName internalName)
	{
	    super(config, internalName, 500000, 2, true);
	}
	

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(world.isRemote) return itemstack;
		
	    if (ElectricItem.manager.use(itemstack, 100, player)) {
	    	shootLaser(world, player, itemstack, 3, 10.0F, 5.0F, 2147483647);	
	    }
	    return itemstack;
	    
	}

}

package agkz.mods.lazerz.items.lazerz;

import ic2.api.item.ElectricItem;
import ic2.api.keyboard.Keys;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import agkz.mods.lazerz.InternalName;
import agkz.mods.lazerz.items.MiningLazerBase;
import agkz.mods.lazerz.items.lazerItems;

public class lazerHandgun extends MiningLazerBase {

	public lazerHandgun(Configuration config, InternalName internalName)
	{
	    super(config, internalName, lazerItems.lazerHandGun, 20000, 1, false, 0);
	}
	

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(world.isRemote) return itemstack;
		
	    if (ElectricItem.manager.use(itemstack, 100, player)) {
	    	shootLaser(world, player, itemstack, 1, 10.0F, 5.0F, 2147483647);	
	    }
	    return itemstack;
	    
	}

}

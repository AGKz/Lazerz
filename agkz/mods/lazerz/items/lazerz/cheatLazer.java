package agkz.mods.lazerz.items.lazerz;

import ic2.api.item.ElectricItem;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import agkz.mods.lazerz.InternalName;
import agkz.mods.lazerz.items.MiningLazerBase;
import agkz.mods.lazerz.items.lazerItems;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;

public class cheatLazer extends MiningLazerBase {

	public cheatLazer(Configuration config, InternalName internalName)
	{
	    super(config, internalName, lazerItems.cheatLazer, 0, 3, false, 0);
	    
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(world.isRemote) return itemstack;
		
	    if (shootLaser(world, player, itemstack, 7, 10.0F, 5.0F, 2147483647, false, false, player.rotationYaw, player.rotationPitch, player.posY + player.getEyeHeight() - 0.1D)) {
	    	return itemstack;
	    }
	    return itemstack;
	    
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List list) {
		
		list.add(new ItemStack(this, 1));
	}
}

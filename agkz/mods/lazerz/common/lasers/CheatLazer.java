package agkz.mods.lazerz.common.lasers;

import ic2.api.item.ElectricItem;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import agkz.mods.lazerz.common.InternalName;
import agkz.mods.lazerz.common.LazerType;
import agkz.mods.lazerz.common.items.MiningLazerBase;
import agkz.mods.lazerz.common.items.LazerItems;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;

public class CheatLazer extends MiningLazerBase {

	public CheatLazer(Configuration config, InternalName internalName)
	{
	    super(config, internalName, LazerItems.cheatLazer, LazerType.CHEAT);
	    
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(world.isRemote) return itemstack;
		
	    if (shootLaser(world, player, 10.0F, 5.0F, 100, false, false, LazerType.CHEAT)) {
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

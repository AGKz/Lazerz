package agkz.mods.lazerz.items.lazerz;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.network.INetworkItemEventListener;
import agkz.mods.lazerz.InternalName;
import agkz.mods.lazerz.lazerz;
import agkz.mods.lazerz.entity.EntityLazer;
import agkz.mods.lazerz.items.MiningLazerBase;
import agkz.mods.lazerz.items.lazerItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;

public class MiningLazerMK2 extends MiningLazerBase {

	public MiningLazerMK2(Configuration config, InternalName internalName)
	{
	    super(config, internalName, lazerItems.MiningLazerMK2, 200000, 2, true, 0);
	}
	

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(world.isRemote) return itemstack;
		
	    if (ElectricItem.manager.use(itemstack, 100, player)) {
	    	shootLaser(world, player, itemstack, 0, 10.0F, 5.0F, 2147483647);	
	    }
	    return itemstack;
	    
	}
	

}

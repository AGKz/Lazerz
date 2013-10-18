package agkz.mods.lazerz.common.lasers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.network.INetworkItemEventListener;
import agkz.mods.lazerz.Lazerz;
import agkz.mods.lazerz.common.InternalName;
import agkz.mods.lazerz.common.LazerType;
import agkz.mods.lazerz.common.items.MiningLazerBase;
import agkz.mods.lazerz.common.items.LazerItems;
import agkz.mods.lazerz.entity.EntityLazer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;

public class MiningLazerMK2 extends MiningLazerBase {
	
	public MiningLazerMK2(Configuration config, InternalName internalName)
	{
	    super(config, internalName, LazerItems.MiningLazerMK2, LazerType.MARK2);
	}
	

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(world.isRemote) return itemstack;
		
		
	    if (ElectricItem.manager.use(itemstack, 100, player)) {
	    	//shootLaser(world, player, itemstack, 0, 10.0F, 5.0F, 2147483647);
	    	shootLaser(world, player, 10.0F, 5.0F, 100, false, false, LazerType.MARK2);
	    }
	    return itemstack;
	    
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(Lazerz.modid.toLowerCase() + ":baseBody");
	}
	

}

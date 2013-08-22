package agkz.mods.lazerz.items.lazerz;

import ic2.api.item.ElectricItem;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import agkz.mods.lazerz.InternalName;
import agkz.mods.lazerz.lazerz;
import agkz.mods.lazerz.items.MiningLazerBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class biologicalLazer extends MiningLazerBase{

	public biologicalLazer(Configuration config, InternalName internalName)
	{
	    super(config, internalName, 200000, 2, false);
	}
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(world.isRemote) return itemstack;
		
	    if (ElectricItem.manager.use(itemstack, 100, player)) {
	    	shootLaser(world, player, itemstack, 2, 50.0F, 5.0F, 2147483647);	
	    }
	    return itemstack;
	    
	}
	
	public static boolean biohitentity(Entity hitentity) {
		if(hitentity.isEntityAlive()) {
			EntityLivingBase livingEntity = (EntityLivingBase) hitentity;
			livingEntity.addPotionEffect(new PotionEffect(Potion.invisibility.id, 200, 1));
			livingEntity.extinguish();
			return true;
		} else {
			return false;
		}
	}
	
	

}


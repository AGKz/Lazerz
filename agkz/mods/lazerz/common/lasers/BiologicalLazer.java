package agkz.mods.lazerz.common.lasers;

import ic2.api.item.ElectricItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import agkz.mods.lazerz.common.InternalName;
import agkz.mods.lazerz.common.LazerType;
import agkz.mods.lazerz.common.items.MiningLazerBase;
import agkz.mods.lazerz.common.items.LazerItems;

public class BiologicalLazer extends MiningLazerBase {
	
	public BiologicalLazer(Configuration config, InternalName internalName)
	{
	    super(config, internalName, LazerItems.biologicalLazer, LazerType.BIOLOGICAL);
	    
	}
		
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(world.isRemote) return itemstack;
		
	    if (ElectricItem.manager.use(itemstack, 100, player)) {
	    	shootLaser(world, player, 10.0F, 5.0F, 1, false, false, LazerType.BIOLOGICAL);
	    }
	    return itemstack;
	    
	} 
	
	public static boolean biohitentity(Entity hitentity) {
		if(hitentity.isEntityAlive() && hitentity instanceof EntityLivingBase) {
			EntityLivingBase livingEntity = (EntityLivingBase) hitentity;
			livingEntity.addPotionEffect(new PotionEffect(Potion.invisibility.id, 200, 1));
			livingEntity.extinguish();
			return true;
		} else {
			return false;
		}
	}
	
	

}


package agkz.mods.lazerz.items.lazerz;

import ic2.api.item.ElectricItem;
import agkz.mods.lazerz.InternalName;
import agkz.mods.lazerz.items.MiningLazerBase;
import agkz.mods.lazerz.items.lazerItems;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;

public class blockplacerLazer extends MiningLazerBase {
	public static int lazerTier = 3;

	public blockplacerLazer(Configuration config, InternalName internalName)
	{
	    super(config, internalName, lazerItems.blockPlacerLazer, 200000, 2, false, 2);
	}
	

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(world.isRemote) return itemstack;
		
	    if (ElectricItem.manager.use(itemstack, 100, player)) {
	    	shootLaser(world, player, itemstack, 5, 10.0F, 5.0F, 2147483647);	
	    }
	    return itemstack;
	    
	}


	public static void placeBlock(World world, int side, int x, int y, int z) {
		switch (side) {
			default: break;
//			case 0: y--;
			case 1: y++;
//			case 2: z--;
			case 3: z++;
//			case 4: x--;
			case 5: x++;
		}
		world.setBlock(x, y, z, Block.cobblestone.blockID);
		ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Placing block: X-" + x + " Y:" + y +" Z:" + z);
	}

}

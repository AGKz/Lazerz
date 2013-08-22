package agkz.mods.lazerz.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import agkz.mods.lazerz.DefaultIds;
import agkz.mods.lazerz.InternalName;
import agkz.mods.lazerz.lazerz;
import agkz.mods.lazerz.utils.LazerUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BurntNetherStarItem extends Item {
	
	int lookingDistance = 50;
	float explosionSize = 1F;
	List information;
	boolean hasClicked = false;
	

	public BurntNetherStarItem(Configuration config, InternalName internalname) {
		super(DefaultIds.get(internalname));
		setMaxStackSize(16);
		setCreativeTab(lazerz.lazerTab);
		setUnlocalizedName("burntnetherstar");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(lazerz.modid.toLowerCase() + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack itemStack){
		return EnumRarity.rare;
	}
	
	
	public boolean takeXP(EntityPlayer player, int amount) {
		if (player.experienceLevel < amount) {
			return false;
		} else {
			player.addExperienceLevel(-amount);
			return true;
		}
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List l, boolean B) {
		
		l.clear();
		l.add(EnumChatFormatting.AQUA + "Burnt Nether Star: " + EnumChatFormatting.GOLD + "Seems dangerous");
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) 
		{
			l.add(EnumChatFormatting.GREEN + "" + EnumChatFormatting.BOLD + "10 levels are required");
			l.add(EnumChatFormatting.LIGHT_PURPLE + "You have been warned");
		} else {
			l.add("Hold <SHIFT> for more info");
		}
	}
	
	public boolean hitEntity(ItemStack is, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{ 
		par2EntityLiving.addPotionEffect(new PotionEffect(Potion.wither.id, 200, 1)); 
		return true;
	}
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		hasClicked = true;
		
		if(!world.isRemote) {
			if(player.isSneaking() || player.capabilities.isCreativeMode) {
				return itemstack;
			} else {
					boolean tookXP = takeXP(player, lazerz.netherStarXPAmount);
				
					if (tookXP) {
						world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / ((float) Math.random() * 0.4F + 0.8F));
  
						Vec3 look = player.getLookVec();
						EntityWitherSkull skull = new EntityWitherSkull(world, player, 1, 1, 1);
						skull.setPosition(player.posX + look.xCoord * 1.1, player.posY + look.yCoord + 1.5, player.posZ + look.zCoord * 1.1);
						skull.accelerationX = look.xCoord * 0.1;
						skull.accelerationY = look.yCoord * 0.1;
						skull.accelerationZ = look.zCoord * 0.1;
						world.spawnEntityInWorld(skull);
						
						return itemstack;
					}
					return itemstack;
			}
		
		} else {
			return itemstack;
		}
        
    }
	
	

}

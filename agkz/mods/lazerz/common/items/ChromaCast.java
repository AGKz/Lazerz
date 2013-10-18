package agkz.mods.lazerz.common.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;

import org.lwjgl.input.Keyboard;

import agkz.mods.lazerz.Lazerz;
import agkz.mods.lazerz.client.PixelColor;
import agkz.mods.lazerz.common.DefaultIds;
import agkz.mods.lazerz.common.InternalName;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ChromaCast extends Item {
	
	List information;
	PixelColor pixel = new PixelColor();
	

	public ChromaCast(Configuration config, InternalName internalname) {
		super(DefaultIds.get(internalname));
		setMaxStackSize(16);
		setCreativeTab(Lazerz.lazerTab);
		setUnlocalizedName("chromacast");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(Lazerz.modid.toLowerCase() + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
	@Override
	public String getItemDisplayName(ItemStack itemStack) {
		return EnumChatFormatting.AQUA + super.getItemStackDisplayName(itemStack) + "[UNFINISHED]";
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
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List l, boolean B) {
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) 
		{
			l.add(EnumChatFormatting.LIGHT_PURPLE + "Unfinished: Will be used to");
			l.add(EnumChatFormatting.LIGHT_PURPLE + "read block pixels for custom laser color");
			l.add(EnumChatFormatting.LIGHT_PURPLE + "Ignore this item for now");
		} else {
			l.add("Hold <SHIFT> for more info");
		}
	}
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz)
    {
    	
    	if (itemstack.stackTagCompound == null) {
    		itemstack.setTagCompound(new NBTTagCompound());
    	}
    	if(!world.isRemote) {
    		itemstack.stackTagCompound.setBoolean("colorSet", true);
    	
    		itemstack.stackTagCompound.setFloat("choosenColorR", 0.0F);
    		itemstack.stackTagCompound.setFloat("choosenColorG", 0.1F);
    		itemstack.stackTagCompound.setFloat("choosenColorG", 0.0F);
    		player.addChatMessage("Side: " + side + " PX: " + px + " PY: " + py + " PZ: " + pz);
    		float cx = 0, cy = 0;
    	
    		if(side == 0 || side == 1) cx = (px*16); cy = (pz*16F);
    		if(side == 2 || side == 3) cx = (px*16); cy = (py*16F);
    		if(side == 4 || side == 5) cx = (py*16); cy = (pz*16F);
    	
    		int ix = (int)cx;
    		int iy = (int)cy;
    	
    		int pixelColor = pixel.getIntColorNumber(Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
    		
    		Lazerz.logger.info("Pixel is " + pixelColor);
    		
    		/*
    		player.addChatMessage("Hit Block: " + Block.blocksList[world.getBlockId(x, y, z)].getUnlocalizedName() + " ix: " + ix + " iy: " + iy); 
    		player.addChatMessage("Icon is Name: " + pixel.icon.getIconName() + " H: " + pixel.icon.getIconHeight() + " W: " + pixel.icon.getIconWidth()); 
    		player.addChatMessage("U is Max: " + pixel.icon.getMaxU() + " Min: " + pixel.icon.getMinU() + " Inter with ix: " + pixel.icon.getInterpolatedU(ix));
    		player.addChatMessage("V is Max: " + pixel.icon.getMaxV() + " Min: " + pixel.icon.getMinV() + " Inter with iy: " + pixel.icon.getInterpolatedV(iy)); */
    	}
        return true;
    }

}

package agkz.mods.lazerz.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.Configuration;
import agkz.mods.lazerz.DefaultIds;
import agkz.mods.lazerz.InternalName;
import agkz.mods.lazerz.lazerz;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDusts extends Item {

	public static final String[] names = new String[] { "blue", "brown", "cyan", "gray", "green", "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "white"};

	public ItemDusts(Configuration config, InternalName internalname) {
		super(DefaultIds.get(internalname));
		setCreativeTab(lazerz.lazerTab);
		setUnlocalizedName("colorDust");
	}
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) 
	{
		icons = new Icon[names.length];
		
		for(int i=0; i < icons.length; i++)
		{
			icons[i] = iconRegister.registerIcon(lazerz.modid + ":" + (this.getColorNameFromNumber(i)) + "_dust");
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
	    for (int x = 0; x < names.length; x++)
	    {
	        par3List.add(new ItemStack(this, 1, x));
	    }
	}
	
	 
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
	    int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
	    return super.getUnlocalizedName() + "." + names[i];
	}
	
	public Icon getIconFromDamage(int damage)
	{
		return icons[damage];
	}
	
	private String getColorNameFromNumber(int color) {
		return names[color];
	}

}

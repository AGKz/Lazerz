package agkz.mods.lazerz.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.Configuration;
import agkz.mods.lazerz.Lazerz;
import agkz.mods.lazerz.common.DefaultIds;
import agkz.mods.lazerz.common.InternalName;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LazerMetaItems extends Item {
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	public LazerMetaItems(Configuration config)
	{ 
		super(DefaultIds.get(InternalName.genericItem));
				
		setCreativeTab(Lazerz.lazerTab);
		setHasSubtypes(true);
	}
	
	@Override
	public Icon getIconFromDamage(int i) {
		return icons[i];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icons = new Icon[2];
		
		for(int i = 0; i < icons.length; i++){
			icons[i] = iconRegister.registerIcon(Lazerz.modid.toLowerCase() + ":" + (this.getUnlocalizedName().substring(5)) + nameForMeta(i));
		}
	}
	
	private String nameForMeta(int i) {
		switch(i) {
		case 0: return "Lens";
		case 1: return "AdvancedLapisPlate";
		default: return "-" + i + "-error-meta-out-of-range";
		}
	}
	
	
	public static final String[] names = new String[] {"lazerlens", "advancedlapisplate"};
	
	public String getUnlocalizedName(ItemStack itemstack) {
		int i = MathHelper.clamp_int(itemstack.getItemDamage(),	0, 15);
		return super.getUnlocalizedName() + "." + names[i];
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs creativeTabs, List list) {
		
		for(int i = 0; i < icons.length; i++){
			list.add(new ItemStack(id, 1, i));
		}
		
	}
	
}

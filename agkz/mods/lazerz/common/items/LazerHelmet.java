package agkz.mods.lazerz.common.items;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.ISpecialArmor;
import agkz.mods.lazerz.Lazerz;
import agkz.mods.lazerz.common.DefaultIds;
import agkz.mods.lazerz.common.InternalName;
import agkz.mods.lazerz.common.LazerType;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LazerHelmet extends ItemArmor implements IElectricItem, ISpecialArmor {
	
	private boolean active;
	private int maxCharge;
	private int tier;

	public LazerHelmet(Configuration config, InternalName internalName, int render, int LazerType) {
		super(DefaultIds.get(internalName), Lazerz.proxy.LazerArmor, render, 0);
		
		setUnlocalizedName(internalName.toString());
		setCreativeTab(Lazerz.lazerTab);
		
		GameRegistry.registerItem(this, internalName.toString());
		
		this.maxCharge = Lazerz.proxy.lazervalues.getMaxChargeForLazerType(LazerType);
		int lazerTier = Lazerz.proxy.lazervalues.getTierForLazerType(LazerType);
		
		if (lazerTier == 1){
			this.tier = 2;
		} else this.tier = lazerTier;
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
		return itemstack;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List l, boolean B) {
		
		String text = this.active ? EnumChatFormatting.GREEN + "Armor is ACTIVE" : EnumChatFormatting.RED + "Armor is DEACTIVE";
		
		l.add(text + EnumChatFormatting.GRAY + " <SHIFT> for details");
	}

    @Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(Lazerz.modid.toLowerCase() + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return true;
	}
	@Override
	public int getChargedItemId(ItemStack itemStack) {
		return this.itemID;
	}
	@Override
	public int getEmptyItemId(ItemStack itemStack) {
		return this.itemID;
	}
	@Override
	public int getMaxCharge(ItemStack itemStack) {
		return maxCharge;
	}
	@Override
	public int getTier(ItemStack itemStack) {
		return tier;
	}
	@Override
	public int getTransferLimit(ItemStack itemStack) {
		switch(tier) {
		case 2: return 100;
		case 3: return 125;
		case 4: return 150;
		default: return 125;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List list) {
		
		ItemStack itemStack = new ItemStack(this, 1);
		
		if (getChargedItemId(itemStack) == this.itemID) {
			ItemStack charged = new ItemStack(this, 1);
			ElectricItem.manager.charge(charged, 2147483647, 2147483647, true, false);
			list.add(charged);
		}
		
		if (getEmptyItemId(itemStack) == this.itemID) list.add(new ItemStack(this, 1, getMaxDamage()));
	}
	
	@Override
	public String getItemDisplayName(ItemStack itemStack) {
		return EnumChatFormatting.GOLD + super.getItemDisplayName(itemStack);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player,
			ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(1, 1, 2);
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 4;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		int EUtoUse = damage * 50;
		if(!ElectricItem.manager.use(stack, EUtoUse, entity)) {
			stack.setItemDamage(stack.getMaxDamage());
			entity.attackEntityFrom(source, (float)damage);
		}
	}

}

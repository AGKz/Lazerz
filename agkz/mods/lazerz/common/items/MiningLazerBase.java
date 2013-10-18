package agkz.mods.lazerz.common.items;

import ic2.api.item.ElectricItem;
import ic2.api.item.IBoxable;
import ic2.api.item.IElectricItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import agkz.mods.lazerz.Lazerz;
import agkz.mods.lazerz.common.DefaultIds;
import agkz.mods.lazerz.common.InternalName;
import agkz.mods.lazerz.common.LazerValues;
import agkz.mods.lazerz.entity.EntityLazer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MiningLazerBase extends ItemTool implements IElectricItem, IBoxable
{
	private final ItemStack itemStack;
	private int operationEnergyCost;
	private Set<Block> mineableBlocks = new HashSet();
	private List list;
	private int maxCharge;
	private int tier;
	private boolean provideEnergy;
		
		
	public MiningLazerBase(Configuration config, InternalName internalName, ItemStack thisitem, int LazerType)
	{ 
		super(DefaultIds.get(internalName), 0.0F, EnumToolMaterial.IRON, new Block[0]);
		this.itemStack = thisitem;
		
		GameRegistry.registerItem(this, internalName.toString());
		setMaxStackSize(1);
		setMaxDamage(27);
		setCreativeTab(Lazerz.lazerTab);
		setUnlocalizedName(internalName.toString());
		
		this.maxCharge = Lazerz.proxy.lazervalues.getMaxChargeForLazerType(LazerType);
		this.tier = Lazerz.proxy.lazervalues.getTierForLazerType(LazerType);
		this.provideEnergy = Lazerz.proxy.lazervalues.shouldProvideEnergyForLazerType(LazerType);
	}

	public boolean shootLaser(World world, EntityLivingBase entityliving, float range, float power, int blockBreaks, boolean explosive, boolean smelt, int lazerType) 
	{
		 EntityLazer lazer = new EntityLazer(world, entityliving, range, power, blockBreaks, explosive, smelt);
		 
		 NBTTagCompound identification = lazer.getEntityData();
		 identification.setInteger("lazerType", lazerType);
		 
		 world.spawnEntityInWorld(lazer);
		 return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(Lazerz.modid.toLowerCase() + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return provideEnergy;
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
		case 1: return 100;
		case 2: return 115;
		case 3: return 125;
		case 4: return 150;
		default: return 125;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs creativeTabs, List list) {
		
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
	
	public static void openGuiForLazerType(World world, EntityPlayer player, int lazerType) {
		
		if(Minecraft.getMinecraft().currentScreen == null) {
			NBTTagCompound data = player.getEntityData();
			data.setInteger("currentLazer", lazerType);
		
			player.openGui(Lazerz.instance, 0, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		
		
	}

	@Override
	public boolean canBeStoredInToolbox(ItemStack itemstack) {
		return true;
	}

}

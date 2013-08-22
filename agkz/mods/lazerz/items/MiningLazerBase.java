package agkz.mods.lazerz.items;

import ic2.api.event.LaserEvent;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.network.NetworkHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import agkz.mods.lazerz.DefaultIds;
import agkz.mods.lazerz.InternalName;
import agkz.mods.lazerz.lazerz;
import agkz.mods.lazerz.entity.EntityLazer;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MiningLazerBase extends ItemTool implements IElectricItem
{
	private int operationEnergyCost;
	private Set<Block> mineableBlocks = new HashSet();
	private List list;
	private int maxCharge;
	private int tier;
	private boolean provideEnergy;
	
		
	public MiningLazerBase(Configuration config, InternalName internalName, int maxCharge, int tier, boolean provideEnergy)
	{ 
		super(DefaultIds.get(internalName), 0.0F, EnumToolMaterial.IRON, new Block[0]);
		
		GameRegistry.registerItem(this, internalName.toString());
		setMaxStackSize(1);
		setMaxDamage(27);
		setCreativeTab(lazerz.lazerTab);
		setUnlocalizedName(internalName.toString());
		
		this.maxCharge = maxCharge;
		this.tier = tier;
		this.provideEnergy = provideEnergy;
	}
	
	public boolean shootLaser(World world, EntityLivingBase player, ItemStack laseritem, int lazerType, float range, float power, int blockBreaks) 
	{
		return shootLaser(world, player, laseritem, lazerType, range, power, blockBreaks, false, false, player.rotationYaw, player.rotationPitch, player.posY + player.getEyeHeight() - 0.1D);
	}
	
	public boolean shootLaser(World world, EntityLivingBase player, ItemStack laseritem, int lazerType, float range, float power, int blockBreaks, boolean explosive, boolean smelt) 
	{
		return shootLaser(world, player, laseritem, lazerType, range, power, blockBreaks, explosive, smelt, player.rotationYaw, player.rotationPitch, player.posY + player.getEyeHeight() - 0.1D);
	}

	public boolean shootLaser(World world, EntityLivingBase entityliving, ItemStack laseritem, int lazerType, float range, float power, int blockBreaks, boolean explosive, boolean smelt, double yawDeg, double pitchDeg, double y) 
	{
		 EntityLazer tLaser = new EntityLazer(world, entityliving, range, power, blockBreaks, explosive, yawDeg, pitchDeg, y);
		 NBTTagCompound entityData = tLaser.getEntityData();
		 entityData.setInteger("lazerType", lazerType);
		
		 world.spawnEntityInWorld(tLaser);
		 return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(lazerz.modid.toLowerCase() + ":" + (this.getUnlocalizedName().substring(5)));
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
		return 150;
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

}

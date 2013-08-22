package agkz.mods.lazerz.items;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.core.ContainerBase;
import ic2.core.IC2;
import ic2.core.IHasGui;
import ic2.core.util.StackUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import agkz.mods.lazerz.DefaultIds;
import agkz.mods.lazerz.InternalName;
import agkz.mods.lazerz.lazerz;
import agkz.mods.lazerz.entity.EntityLazer;
import agkz.mods.lazerz.gui.ContainerLazer;
import agkz.mods.lazerz.gui.GuiLazer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MiningLazerBase extends ItemTool implements IElectricItem, IHasGui
{
	private final ItemStack itemStack;
	private int operationEnergyCost;
	private Set<Block> mineableBlocks = new HashSet();
	private List list;
	private int maxCharge;
	private int tier;
	private boolean provideEnergy;
	
	private final ItemStack[] inventory;
	
		
	public MiningLazerBase(Configuration config, InternalName internalName, ItemStack thisitem, int maxCharge, int tier, boolean provideEnergy, int numberOfInvSlots)
	{ 
		super(DefaultIds.get(internalName), 0.0F, EnumToolMaterial.IRON, new Block[0]);
		this.itemStack = thisitem;
		inventory = new ItemStack[numberOfInvSlots];
		
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
	
	public int getSizeInventory() {
		return this.inventory.length;
	}

	public ItemStack getStackInSlot(int slot) {
		return this.inventory[slot];
	}

	public ItemStack decrStackSize(int slot, int amount)
	{
		if (this.inventory[slot] != null) {
			if (this.inventory[slot].stackSize <= amount) {
				ItemStack itemstack = this.inventory[slot];
				this.inventory[slot] = null;

				return itemstack;
			}

			ItemStack ret = this.inventory[slot].splitStack(amount);

			if (this.inventory[slot].stackSize == 0) {
				this.inventory[slot] = null;
			}

			return ret;
	    	}
	    return null;
	  }

	public ItemStack getStackInSlotOnClosing(int i) {
		return null;
	}

	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		this.inventory[slot] = itemstack;

	    if ((itemstack != null) && (itemstack.stackSize > getInventoryStackLimit()))
	      itemstack.stackSize = getInventoryStackLimit();
	}

	public String getInvName() {
		return "Lazerz";
	}

	public boolean isInvNameLocalized() {
		return true;
	}

	public int getInventoryStackLimit() {
		return 64;
	}
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return false;
	}
	
	public void onInventoryChanged() {}
	public void openChest() {}
	public void closeChest() {}

	
	@SideOnly(Side.CLIENT)
	public GuiScreen getGui(EntityPlayer player, boolean arg1) {
		return new GuiLazer(new ContainerLazer(player, this));
	}

	public ContainerBase getGuiContainer(EntityPlayer player) {
		return new ContainerLazer(player, this);
	}

	public void onGuiClosed(EntityPlayer entityPlayer)
	  {
		NBTTagList nbtTagList;
		int i;
	    if (IC2.platform.isSimulating()) {

	      NBTTagCompound nbtTagCompound = StackUtil.getOrCreateNbtData(this.itemStack);
	      boolean dropItself = false;

	      for (i = 0; i < getSizeInventory(); i++)
	      {
	        
	        if (this.inventory[i] != null) {
	          NBTTagCompound nbtTagCompoundSlot = StackUtil.getOrCreateNbtData(this.inventory[i]);

	          /* if (nbtTagCompound.getInteger("uid") == nbtTagCompoundSlot.getInteger("uid")) {
	            this.itemStack.stackSize = 1;
	            this.inventory[i] = null;
	            dropItself = true;

	            break;
	          } */
	        }
	      }

	      nbtTagList = new NBTTagList();

	      for (i = 0; i < this.inventory.length; i++) {
	        if (this.inventory[i] != null) {
	          NBTTagCompound nbtTagCompoundSlot = new NBTTagCompound();

	          nbtTagCompoundSlot.setByte("Slot", (byte)i);
	          this.inventory[i].writeToNBT(nbtTagCompoundSlot);

	          nbtTagList.appendTag(nbtTagCompoundSlot);
	        }
	      }

	      nbtTagCompound.setTag("Items", nbtTagList);

//	      if (dropItself)
//	        StackUtil.dropAsEntity(entityPlayer.worldObj, (int)entityPlayer.posX, (int)entityPlayer.posY, (int)entityPlayer.posZ, this.itemStack);
//	      else
//	        for (i = -1; i < entityPlayer.inventory.getSizeInventory(); i++)
//	        {
//	          ItemStack itemStackSlot;
//	          if (i == -1)
//	            itemStackSlot = entityPlayer.inventory.getItemStack();
//	          else {
//	            itemStackSlot = entityPlayer.inventory.getStackInSlot(i);
//	          }
//
//	          if (itemStackSlot != null) {
//	            NBTTagCompound nbtTagCompoundSlot = itemStackSlot.getTagCompound();
//
//	            if ((nbtTagCompoundSlot != null) && (nbtTagCompound.getInteger("uid") == nbtTagCompoundSlot.getInteger("uid"))) {
//	              this.itemStack.stackSize = 1;
//
//	              if (i == -1) {
//	                entityPlayer.inventory.setItemStack(this.itemStack); break;
//	              }
//	              entityPlayer.inventory.setInventorySlotContents(i, this.itemStack);
//
//	              break;
//	            }
//	          }
//	        }
	    }
	  }

	public boolean matchesUid(int uid) {
	    NBTTagCompound nbtTagCompound = StackUtil.getOrCreateNbtData(this.itemStack);

	    return nbtTagCompound.getInteger("uid") == uid;
	  }

}

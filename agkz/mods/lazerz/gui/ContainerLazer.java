package agkz.mods.lazerz.gui;

import ic2.core.ContainerBase;
import ic2.core.item.ItemCropSeed;
import ic2.core.slot.SlotCustom;
import ic2.core.slot.SlotDischarge;
import ic2.core.util.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import agkz.mods.lazerz.items.MiningLazerBase;
import agkz.mods.lazerz.items.lazerz.biologicalLazer;
import cpw.mods.fml.common.FMLCommonHandler;

public class ContainerLazer extends ContainerBase
{
	  public MiningLazerBase baseLazer;

	  public ContainerLazer(EntityPlayer entityPlayer, MiningLazerBase miningLazerBase)
	  {
	    super(miningLazerBase);

	    this.baseLazer = miningLazerBase;

	    addSlotToContainer(new SlotCustom(miningLazerBase, new Object[] { ItemCropSeed.class }, 0, 8, 7));
	    addSlotToContainer(new SlotCustom(miningLazerBase, new Object[0], 1, 41, 7));
	    addSlotToContainer(new SlotDischarge(miningLazerBase, 2, 152, 7));

	    for (int j = 0; j < 9; j++)
	      addSlotToContainer(new Slot(entityPlayer.inventory, j, 8 + j * 18, 142));
	  }

	  public ItemStack slotClick(int slot, int button, int par3, EntityPlayer entityPlayer)
	  {
	    if (!FMLCommonHandler.instance().getEffectiveSide().isClient() && (slot == -999) && ((button == 0) || (button == 1))) {
	      ItemStack itemStackSlot = entityPlayer.inventory.getItemStack();

	      if (itemStackSlot != null) {
	        NBTTagCompound nbtTagCompoundSlot = StackUtil.getOrCreateNbtData(itemStackSlot);

	        if (this.baseLazer.matchesUid(nbtTagCompoundSlot.getInteger("uid"))) {
	          entityPlayer.closeScreen();
	        }
	      }
	    }

	    return super.slotClick(slot, button, par3, entityPlayer);
	  }

	  public void onContainerClosed(EntityPlayer entityPlayer)
	  {
	    this.baseLazer.onGuiClosed(entityPlayer);

	    super.onContainerClosed(entityPlayer);
	  }

}

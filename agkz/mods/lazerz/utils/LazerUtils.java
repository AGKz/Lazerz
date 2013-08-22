package agkz.mods.lazerz.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LazerUtils {

// Redo all of this code.
    
    public static ItemStack getFromInventory(IInventory inventory, ItemStack itemStackDestination, boolean simulate) {
        ItemStack ret = null;
        int toTransfer = itemStackDestination.stackSize;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
          ItemStack itemStack = inventory.getStackInSlot(i);

          if ((itemStack != null) && (isStackEqual(itemStack, itemStackDestination))) {
            if (ret == null) ret = copyWithSize(itemStack, 0);

            int transfer = Math.min(toTransfer, itemStack.stackSize);

            if (!simulate) {
              itemStack.stackSize -= transfer;
              if (itemStack.stackSize == 0) inventory.setInventorySlotContents(i, null);
            }

            toTransfer -= transfer;
            ret.stackSize += transfer;

            if (toTransfer == 0) return ret;
          }
        }

        return ret;
    }
    
    @SideOnly(Side.CLIENT)
	public static void drawParticleStreamTo(EntityPlayer source, World world, double x, double y, double z, String particle) {
        Vec3 direction = source.getLookVec().normalize();
        double scale = 1.0;
        double xoffset = 1.3f;
        double yoffset = -.2;
        double zoffset = 0.3f;
        Vec3 horzdir = direction.normalize();
        horzdir.yCoord = 0;
        horzdir = horzdir.normalize();
        double cx = source.posX + direction.xCoord * xoffset - direction.yCoord * horzdir.xCoord * yoffset - horzdir.zCoord * zoffset;
        double cy = source.posY + source.getEyeHeight() + direction.yCoord * xoffset + (1 - Math.abs(direction.yCoord)) * yoffset;
        double cz = source.posZ + direction.zCoord * xoffset - direction.yCoord * horzdir.zCoord * yoffset + horzdir.xCoord * zoffset;
        double dx = x - cx;
        double dy = y - cy;
        double dz = z - cz;
        double ratio = Math.sqrt(dx * dx + dy * dy + dz * dz);

        while (Math.abs(cx - x) > Math.abs(dx / ratio)) {
            world.spawnParticle(particle, cx, cy, cz, 0.0D, 0.0D, 0.0D);
            cx += dx * 0.1 / ratio;
            cy += dy * 0.1 / ratio;
            cz += dz * 0.1 / ratio;
        }
    }
    public void initiateItemEvent(EntityPlayer player, ItemStack itemStack, int event, boolean limitRange) {
        if (player.username.length() > 127) return;

        int maxDistance = limitRange ? 400 : MinecraftServer.getServer().getConfigurationManager().getEntityViewDistance() + 16;
        Packet250CustomPayload packet = null;

        for (Iterator i$ = player.worldObj.playerEntities.iterator(); i$.hasNext(); ) { Object obj = i$.next();
          EntityPlayerMP entityPlayer = (EntityPlayerMP)obj;

          int distanceX = (int)player.posX - (int)entityPlayer.posX;
          int distanceZ = (int)player.posZ - (int)entityPlayer.posZ;
          int distance;
          if (limitRange)
            distance = distanceX * distanceX + distanceZ * distanceZ;
          else {
            distance = Math.max(Math.abs(distanceX), Math.abs(distanceZ));
          }

          if (distance <= maxDistance)
          {
            if (packet == null) {
              try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                DataOutputStream os = new DataOutputStream(buffer);

                os.writeByte(2);

                os.writeByte(player.username.length());
                os.writeChars(player.username);
                os.writeInt(itemStack.itemID);
                os.writeInt(itemStack.getItemDamage());

                os.writeInt(event);

                os.close();

                packet = new Packet250CustomPayload();

                packet.channel = "ic2";
                packet.isChunkDataPacket = false;
                packet.data = buffer.toByteArray();
                packet.length = buffer.size();
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            }

            PacketDispatcher.sendPacketToPlayer(packet, (Player)entityPlayer);
          } }
      }
    
    
    
    public static int putInInventory(IInventory inventory, ItemStack itemStackSource, boolean simulate) {
        int transferred = 0;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
          if (inventory.isItemValidForSlot(i, itemStackSource))
          {
            ItemStack itemStack = inventory.getStackInSlot(i);

            if ((itemStack != null) && (itemStack.isItemEqual(itemStackSource))) {
              int transfer = Math.min(itemStackSource.stackSize - transferred, itemStack.getMaxStackSize() - itemStack.stackSize);

              if (!simulate) itemStack.stackSize += transfer;

              transferred += transfer;

              if (transferred == itemStackSource.stackSize) return transferred;
            }
          }
        }
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
          if (inventory.isItemValidForSlot(i, itemStackSource))
          {
            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack == null) {
              int transfer = Math.min(itemStackSource.stackSize - transferred, itemStackSource.getMaxStackSize());

              if (!simulate) {
                ItemStack dest = copyWithSize(itemStackSource, transfer);
                inventory.setInventorySlotContents(i, dest);
              }

              transferred += transfer;

              if (transferred == itemStackSource.stackSize) return transferred;
            }
          }
        }
        return transferred;
    }
    
    public static boolean isStackEqual(ItemStack stack1, ItemStack stack2) {
        return (stack1 != null) && (stack2 != null) && (stack1.itemID == stack2.itemID) && (((!stack1.getHasSubtypes()) && (!stack1.isItemStackDamageable())) || ((stack1.getItemDamage() == stack2.getItemDamage()) && (ItemStack.areItemStackTagsEqual(stack1, stack2))));
    }
    
    public static ItemStack copyWithSize(ItemStack itemStack, int newSize) {
        ItemStack ret = itemStack.copy();
        ret.stackSize = newSize;
        return ret;
    }
}

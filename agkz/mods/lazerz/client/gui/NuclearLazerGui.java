package agkz.mods.lazerz.client.gui;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IElectricItemManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.opengl.GL11;

import agkz.mods.lazerz.Lazerz;
import agkz.mods.lazerz.client.Textures;
import agkz.mods.lazerz.common.LazerType;
import agkz.mods.lazerz.common.items.MiningLazerBase;
import cpw.mods.fml.client.FMLClientHandler;

public class NuclearLazerGui extends GuiScreen {
	
	public final int xSizeOfTexture = 176;
	public final int ySizeOfTexture = 222;
	
	public NuclearLazerGui(EntityPlayer player) {
	}
	
	@Override
	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();
		GL11.glColor4f(1F, 1F, 1F, 1F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.GUI_NUCLEAR);
		
		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;
		this.drawLazerGui(posX, posY);
		
		drawTexturedModalRect(posX+12, posY+20, 0, 231, getPercentOfItemInHand(96), 9); //Battery
		drawTexturedModalRect(posX+12, posY+112, 0, 222, 96, 9);
		fontRenderer.drawString(this.getCharge() + " / " + Lazerz.proxy.lazervalues.getMaxChargeForLazerType(LazerType.NUCLEAR), posX+9, posY+37, 0x000000);
		
		fontRenderer.drawString("Nuclear Lazer", posX+9, posY+6, 0xffffff, true);
		
		super.drawScreen(x, y, f);
	}
	
	public int getPercentOfItemInHand (int scale) {
		int maxCharge = Lazerz.proxy.lazervalues.getMaxChargeForLazerType(LazerType.NUCLEAR);
		return (getCharge()*scale)/maxCharge;
	}
	
	public int getCharge() {
		
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		ItemStack hand = player.getCurrentItemOrArmor(0);
		if(hand != null)
			return ElectricItem.manager.getCharge(hand);
		
		return -1;
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	public void initGui()
	{
		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;
		
		//this.buttonList.add(new GuiButton(0, posX + 80, posY + 40, 100, 20, "No Use"));
				
	}
	
	public void drawLazerGui(int posX, int posY) {
		drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
	}

}

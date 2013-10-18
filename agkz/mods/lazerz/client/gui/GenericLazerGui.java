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

public class GenericLazerGui extends GuiScreen {
	
	/* GUI Organization
	 *
	 * GUI parts include:
	 * 	BatteryViewer
	 * 	InventorySlider
	 * 	SecondStoredInventory
	 * 	PotionEffectViewer
	 * 	PowerToggles
	 * 	GenericTextMessage ( Hints )
	 * 	GenericDoubleToggle
	 * 	NuclearInventory
	 * 	UtilityMode
	 * 
	 * 	* Extra Mod add-on
	 * 	EnderStorageColorChooser
	 * 
	 * What Lazer Uses What?
	 * 	Bio - Battery, InventorySlider, Potion, PowerToggles, genericTextMessage
	 *  Ender - Battery, Inventory, GenericDoubleToggle, EnderStorageColorChooser
	 *  Military - Battery, PowerToggles, Overdrive mode, GenericTextMessage
	 *  Mark2 - BatteryViewer, Inventory, SecondChestInventory
	 */
	
	public final int xSizeOfTexture = 256;
	public final int ySizeOfTexture = 204;
		
	public String[] LazerNames = new String[]{"", "Biological Lazer", "Ender Lazer", "Cheat Lazer", "Handgun", "Military", "Mining Laser Mark 2", "Nuclear Lazer", "Smart Tracker Lazer"};
	int currentLazer = 0;
	
	public GenericLazerGui(EntityPlayer player) {
		NBTTagCompound data = player.getEntityData();
		currentLazer = data.getInteger("currentLazer");
	}
	
	@Override
	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();
		GL11.glColor4f(1F, 1F, 1F, 1F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.GUI_LAZERGUI);
		
		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;

		int powerlevel = this.getPowerLevelOfItemInHand();
		this.drawLazerGui(posX, posY);
		
		//drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
		drawTexturedModalRect(posX+157, posY+23, xSizeOfTexture + 178, 205, getNumberAtPercentOf(56, powerlevel), 14);
		
		fontRenderer.drawString(powerlevel + "%", posX+215, posY+26, -6250336);
		fontRenderer.drawString(LazerNames[currentLazer], posX+79, posY+26, -6250336);
		
		super.drawScreen(x, y, f);
	}
	
	public int getPowerLevelOfItemInHand () {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		ItemStack hand = player.getCurrentItemOrArmor(0);
		
		if(hand != null) {
			int charge = ElectricItem.manager.getCharge(hand);
			int maxCharge = Lazerz.proxy.lazervalues.getMaxChargeForLazerType(currentLazer);
			
			//int percentCharged = (charge/maxCharge);
			int percentCharged = (charge*100)/maxCharge;
			return percentCharged;
		}
		return 0;
	}
	
	public int getNumberAtPercentOf(int scale, int number) {
		double decimalNumber = number / 100;
		return (int)decimalNumber * scale;	
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	public void initGui()
	{
		int posX = (this.width - xSizeOfTexture) / 2;
		int posY = (this.height - ySizeOfTexture) / 2;
		
		this.buttonList.add(new GuiButton(0, posX + 80, posY + 40, 100, 20, "No Use"));
				
	}
	
	public void drawLazerGui(int posX, int posY) {
		drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
	}

}

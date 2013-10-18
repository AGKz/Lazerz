package agkz.mods.lazerz.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import agkz.mods.lazerz.Lazerz;
import agkz.mods.lazerz.common.LazerType;
import agkz.mods.lazerz.common.items.MiningLazerBase;
import agkz.mods.lazerz.common.items.LazerItems;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class LazerGUIKeyBind extends KeyHandler {
	
	private EnumSet tickTypes = EnumSet.of(TickType.CLIENT);
	public boolean keyToggled = false;

	public LazerGUIKeyBind(KeyBinding[] keyBindings, boolean[] repeatings) {
		super(keyBindings, repeatings);
	}

	@Override
	public String getLabel() {
		return "LazerzGUI";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) 
	{
		if(Minecraft.getMinecraft().currentScreen == null) {
			World world = Minecraft.getMinecraft().theWorld;
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			try {
				Item hand = player.getCurrentItemOrArmor(0).getItem();
			
				if(LazerItems.biologicalLazer.getItem() == hand)
					MiningLazerBase.openGuiForLazerType(world, player, LazerType.BIOLOGICAL);
				if(LazerItems.cheatLazer.getItem() == hand)
					MiningLazerBase.openGuiForLazerType(world, player, LazerType.CHEAT);
				if(LazerItems.EnderLazer.getItem() == hand)
					MiningLazerBase.openGuiForLazerType(world, player, LazerType.ENDER);
				if(LazerItems.lazerHandGun.getItem() == hand)
					MiningLazerBase.openGuiForLazerType(world, player, LazerType.HANDGUN);
				if(LazerItems.militaryLazerGun.getItem() == hand)
					MiningLazerBase.openGuiForLazerType(world, player, LazerType.MILITARY);
				if(LazerItems.MiningLazerMK2.getItem() == hand)
					MiningLazerBase.openGuiForLazerType(world, player, LazerType.MARK2);
				if(LazerItems.nuclearLazer.getItem() == hand)
					player.openGui(Lazerz.instance, LazerType.NUCLEAR, world, (int)player.posX, (int)player.posY, (int)player.posZ);
				if(LazerItems.utilityLazer.getItem() == hand)
					MiningLazerBase.openGuiForLazerType(world, player, LazerType.UTILITY);
			} catch(NullPointerException e) {
					
			}
		}
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		
		
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return tickTypes;
	}
}

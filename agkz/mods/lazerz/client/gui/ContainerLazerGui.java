package agkz.mods.lazerz.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerLazerGui extends Container {

	public ContainerLazerGui(EntityPlayer player) {
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}

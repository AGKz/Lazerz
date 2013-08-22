package agkz.mods.lazerz.event;

import agkz.mods.lazerz.items.lazerz.biologicalLazer;
import agkz.mods.lazerz.items.lazerz.blockplacerLazer;
import ic2.api.event.LaserEvent;
import net.minecraft.src.ModLoader;
import net.minecraftforge.event.ForgeSubscribe;

public class LazerzEventManager {
	
	@ForgeSubscribe
	public void lazerHitEntity(LaserEvent.LaserHitsEntityEvent event) {
		//event.lasershot.setDead();
		switch (event.lasershot.getEntityData().getInteger("lazerType")) {
			case 2: biologicalLazer.biohitentity(event.hitentity);
			default: break;
		}
		ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Hit entity");
		
	}
	@ForgeSubscribe
	public void lazerHitBlock(LaserEvent.LaserHitsBlockEvent event) {
		//event.lasershot.setDead();
		ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Hit Block " + event.side + " " + event.x + " " + event.y + " " + event.z);
		
		
		switch (event.lasershot.getEntityData().getInteger("lazerType")) {
			case 5: blockplacerLazer.placeBlock(event.world, event.side, event.x, event.y, event.z); break;
			default: break;
		}
	}
}

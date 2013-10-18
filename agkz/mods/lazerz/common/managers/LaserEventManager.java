package agkz.mods.lazerz.common.managers;

import ic2.api.event.LaserEvent.LaserHitsBlockEvent;
import ic2.api.event.LaserEvent.LaserHitsEntityEvent;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import agkz.mods.lazerz.common.LazerType;
import agkz.mods.lazerz.common.lasers.BiologicalLazer;

public class LaserEventManager 
{
	
	private int lazerHorseSpawnRateCounter;
	@ForgeSubscribe
	public void LaserHitsBlockEvent(LaserHitsBlockEvent event) {
		
		NBTTagCompound identification = event.lasershot.getEntityData();
		if(identification.hasKey("lazerType")) {
			int lazerType = identification.getInteger("lazerType");
			
			switch(lazerType) {
				case LazerType.BIOLOGICAL:
					// Do or call biological actions here
					event.setCanceled(true);
					break;
				case LazerType.ENDER:
					// Do or call ender actions here
					break;
				case LazerType.CHEAT:
					// Do or call cheat actions here
					break;
				case LazerType.HANDGUN:
					// Do or call handgun actions here
					break;
				case LazerType.MARK2:
					// Do or call mark 2 actions here
					break;
				case LazerType.MILITARY:
					// Do or call military actions here
					break;
				case LazerType.NUCLEAR:
					// Do or call nuclear actions here
					break;
				case LazerType.UTILITY:
					event.setCanceled(true);
					break;
			}
			
			
			
		}
	}
	
	@ForgeSubscribe
	public void LaserHitsEntityEvent(LaserHitsEntityEvent event) {
		NBTTagCompound identification = event.lasershot.getEntityData();
		if(identification.hasKey("lazerType")) {
			int lazerType = identification.getInteger("lazerType");
			switch(lazerType) {
			case LazerType.BIOLOGICAL:
				// Do or call biological actions here
				BiologicalLazer.biohitentity(event.hitentity);
				event.setCanceled(true);
				break;
			case LazerType.ENDER:
				// Do or call ender actions here
				break;
			case LazerType.CHEAT:
				// Do or call cheat actions here
				break;
			case LazerType.HANDGUN:
				// Do or call handgun actions here
				break;
			case LazerType.MARK2:
				// Do or call mark 2 actions here
				break;
			case LazerType.MILITARY:
				// Do or call military actions here
				break;
			case LazerType.NUCLEAR:
				// Do or call nuclear actions here
				break;
			case LazerType.UTILITY:
				event.setCanceled(true);
				break;
			}
		}
	}
	
	
}

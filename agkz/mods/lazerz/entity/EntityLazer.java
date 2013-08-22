package agkz.mods.lazerz.entity;

import ic2.core.item.tool.EntityMiningLaser;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;


public class EntityLazer extends EntityMiningLaser {

	public EntityLazer(World world, EntityLivingBase entityliving, float range, float power, int blockBreaks, boolean explosive, double yawDeg, double pitchDeg, double y) {
		
		super(world, entityliving, range, power, blockBreaks, explosive, yawDeg, pitchDeg, y);
		
	}
	
	protected void onImpact(MovingObjectPosition movingobject) {
		ModLoader.getMinecraftInstance().thePlayer.sendChatMessage("Impacted");
		this.setDead();
	}

}
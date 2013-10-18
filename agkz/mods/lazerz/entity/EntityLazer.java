package agkz.mods.lazerz.entity;

import ic2.api.event.LaserEvent.LaserHitsBlockEvent;
import ic2.core.item.tool.EntityMiningLaser;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;


public class EntityLazer extends EntityMiningLaser {

	private boolean shouldAffectBlocks;

	public EntityLazer(World world, EntityLivingBase entityliving, float range, float power, int blockBreaks,  boolean explosive, boolean smelt) {
		super(world, entityliving, range, power, blockBreaks, explosive, entityliving.rotationYaw, entityliving.rotationPitch, entityliving.posY + entityliving.getEyeHeight() -0.0D);
	}
	
}
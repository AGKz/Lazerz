package agkz.mods.lazerz.client.render;

import ic2.core.item.tool.RenderCrossed;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import agkz.mods.lazerz.Lazerz;
import agkz.mods.lazerz.common.managers.LazerEffectManager;

public class RenderLaserOverride extends RenderCrossed {

	public RenderLaserOverride(ResourceLocation texture) {
		super(texture);
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
	{
		
		NBTTagCompound eData = entity.getEntityData();
		if (eData.hasKey("specialEffect")) {
			LazerEffectManager effects = new LazerEffectManager(eData.getInteger("specialEffect"));
			Lazerz.logger.info(effects.binaryNumber + " should color, hightpower, magic, bio " + effects.shouldColorize() + effects.shouldHighPower() + effects.shouldMagic() + effects.shouldBio());
			
			if (effects.shouldColorize() && eData.hasKey("colorRed") && eData.hasKey("colorGreen") && eData.hasKey("colorBlue")) {
				float red = entity.getEntityData().getFloat("colorRed");
				float green = entity.getEntityData().getFloat("colorGreen");
				float blue = entity.getEntityData().getFloat("colorBlue");
			}
			
			
		}
		
		
		super.doRender(entity, x, y, z, yaw, pitch);
	}

}

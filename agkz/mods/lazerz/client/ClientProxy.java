package agkz.mods.lazerz.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import agkz.mods.lazerz.CommonProxy;
import agkz.mods.lazerz.lazerz;
import agkz.mods.lazerz.entity.EntityLazer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		//This is for rendering entities and so forth later on
		//RenderingRegistry.registerEntityRenderingHandler(EntityLazer.class, new RenderLazer(new ResourceLocation(lazerz.modid.toLowerCase() + ":" + "textures/models/lazer_red.png")));
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if(tileEntity != null) {
			switch(ID)
			{
			case 0: /* containers go here */
			}
		}
		return null;
	}

	
}

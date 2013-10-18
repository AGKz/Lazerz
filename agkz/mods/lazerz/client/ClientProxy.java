package agkz.mods.lazerz.client;

import ic2.core.item.tool.EntityMiningLaser;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import agkz.mods.lazerz.Lazerz;
import agkz.mods.lazerz.client.render.ChromaCastItemRenderer;
import agkz.mods.lazerz.client.render.HelmetLazerItemRenderer;
import agkz.mods.lazerz.client.render.MiningLazerItemRenderer;
import agkz.mods.lazerz.client.render.RenderLaserOverride;
import agkz.mods.lazerz.common.CommonProxy;
import agkz.mods.lazerz.common.items.LazerItems;
import agkz.mods.lazerz.entity.EntityLazer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	
	public static int renderPass;
	public static int angleRailRenderType;
	public static PixelColor pixel = new PixelColor();
	
	@Override
	public void init() {
		//This is for rendering entities and so forth later on
		MinecraftForgeClient.registerItemRenderer(LazerItems.MiningLazerMK2.itemID, new MiningLazerItemRenderer());
		MinecraftForgeClient.registerItemRenderer(LazerItems.chromaCast.itemID, new ChromaCastItemRenderer());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMiningLaser.class, new RenderLaserOverride(new ResourceLocation(Lazerz.modid.toLowerCase(), "textures/lazerz/lazer.png")));
	}
	
	@Override
	public void preInit(Configuration config) {
		
	}
	
	@Override
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
}

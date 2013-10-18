package agkz.mods.lazerz.client;

import agkz.mods.lazerz.Lazerz;
import net.minecraft.util.ResourceLocation;

public class Textures {
	
	public static final ResourceLocation ITEM_BLANK_COLOR = getResourceLocation("textures/base/lazerColor.png");
	public static final ResourceLocation GUI_LAZERGUI = getResourceLocation("textures/gui/LazerGeneric.png");
	public static final ResourceLocation GUI_NUCLEAR = getResourceLocation("textures/gui/lazernuclear.png");
	public static final ResourceLocation DEFAULT_LAZER_TEXTURE = getResourceLocation("textures/lazerz/default.png");
	public static final ResourceLocation DEFAULT_HELMET_TEXTURE = getResourceLocation("textures/lazerz/helmet.png");

	public static ResourceLocation getResourceLocation(String path) {
		return new ResourceLocation(Lazerz.modid.toLowerCase(), path);
	}
}

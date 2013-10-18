package agkz.mods.lazerz.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import agkz.mods.lazerz.client.Textures;
import agkz.mods.lazerz.client.models.ModelLazerHelmet;

public class HelmetLazerItemRenderer implements IItemRenderer {
	
	protected ModelLazerHelmet helmetModel;

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type) {
		case EQUIPPED: return true;
		default: return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type)
		{
		
		case EQUIPPED:
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(Textures.DEFAULT_HELMET_TEXTURE);
			
			//GL11.glScalef(0.5F, 0.4F, 0.9F);
			
			//GL11.glRotatef(90F, 0.0F, 0.0F, 0.0F);
			//GL11.glRotatef(20F, 0.0F, -1.0F, 0.0F);
			//GL11.glRotatef(-100F, 0.0F, 0.0F, 1.0F);
			
			//GL11.glTranslatef(-0.1F, 0.5F, -1.1F);
			
			this.helmetModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			
			GL11.glPopMatrix();
			break;
			
		case EQUIPPED_FIRST_PERSON: break;
			
		case INVENTORY: break;
		
		default: break;
		}

	}

}

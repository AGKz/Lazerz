package agkz.mods.lazerz.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import agkz.mods.lazerz.client.Textures;
import agkz.mods.lazerz.client.models.ModelMiningLazer;

public class MiningLazerItemRenderer implements IItemRenderer {
	
	private static RenderLaserIcon renderLaserIcon = new RenderLaserIcon();
	protected ModelMiningLazer lazerModel;
	private double zLevel;
	
	public MiningLazerItemRenderer() {
		lazerModel = new ModelMiningLazer();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type) {
		case EQUIPPED: return true;
		case EQUIPPED_FIRST_PERSON: return true; 
		case INVENTORY: return true;
		default: return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		
		switch(type)
		{
		
		case EQUIPPED:
			GL11.glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(Textures.DEFAULT_LAZER_TEXTURE);
			
			GL11.glScalef(0.5F, 0.4F, 0.9F);
			
			GL11.glRotatef(90F, 0.0F, 0.0F, 0.0F);
			GL11.glRotatef(20F, 0.0F, -1.0F, 0.0F);
			GL11.glRotatef(-100F, 0.0F, 0.0F, 1.0F);
			
			GL11.glTranslatef(-0.1F, 0.5F, -1.1F);
			
			this.lazerModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			
			GL11.glPopMatrix();
			break;
			
		case EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();
			GL11.glClear(0);
			Minecraft.getMinecraft().renderEngine.bindTexture(Textures.DEFAULT_LAZER_TEXTURE);
			
			GL11.glScalef(0.5F, 0.4F, 0.9F);
			
			GL11.glRotatef(89F, 0.0F, 0.0F, 0.0F);
			GL11.glRotatef(40F, 0.0F, -1.0F, 0.0F);
			GL11.glRotatef(-80F, 0.0F, 0.0F, 1.0F);
			
			GL11.glTranslatef(-0.1F, 0.0F, -1.15F);
			
			this.lazerModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			
			GL11.glPopMatrix();
			break;
			
		case INVENTORY:
			float[] primaryColor = {1.0F, 0.0F, 0.0F};
			float[] secondaryColor = {0.6F, 0.0F, 0.0F};
			this.renderLaserIcon.renderLaserItemIcon(item, primaryColor, secondaryColor);
			break;
		
		default: break;
		}
	}

}

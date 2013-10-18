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

public class SmallMiningLazerItemRenderer implements IItemRenderer {
	
	private static RenderItem renderItem = new RenderItem();
	protected ModelMiningLazer lazerModel;
	private double zLevel;
	
	public SmallMiningLazerItemRenderer() {
		lazerModel = new ModelMiningLazer();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		
		switch(type) {
		case EQUIPPED: return true;
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
			
		case INVENTORY:
			float[] primaryColor = {1.0F, 0.0F, 0.0F};
			float[] secondaryColor = {0.6F, 0.0F, 0.0F};
			//drawLazerColor(item, primaryColor, secondaryColor);
		
		default: break;
		}
	}
	/**
	 * Draw Lazer Tip and Handle on Icon
	 * @param PrimaryColor float[] ~ {red, green, blue}
	 * @param SecondaryColor float[] ~ {red, green, blue}
	 */
    public void drawLazerColor(ItemStack item, float[] primaryColor, float[] secondaryColor)
    {
    	FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
		
		// ========== Render Item Texture =============
		Icon icon = item.getIconIndex();
		renderItem.renderIcon(0, 0, icon, 16, 16);
	
		// ============ Render OpenGL square shape =============
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
    	float pRed = primaryColor[0];
    	float pGreen = primaryColor[1];
    	float pBlue = primaryColor[2];
    	
    	float sRed = secondaryColor[0];
    	float sGreen = secondaryColor[1];
    	float sBlue = secondaryColor[2];
    	
		for (int i = 0; i <= 3; i++) {
			drawPixel(i+1, i, pRed, pGreen, pBlue, 1F);
			drawPixel(i, i+1, pRed, pGreen, pBlue, 1F);
		}
		
		drawPixel(0, 0, sRed + 0.1F, sGreen+ 0.1F, sBlue + 0.1F, 1F);
		for (int i = 0; i <= 2; i++) {
			
			drawPixel(i+2, i, sRed, sGreen, sBlue, 1F);
			drawPixel(i+1, i+1, sRed, sGreen, sBlue, 1F);
			drawPixel(i, i+2, sRed, sGreen, sBlue, 1F);
		}
		drawPixel(3, 5, pRed, pGreen, pBlue, 0.2F);
		drawPixel(4, 4, pRed, pGreen, pBlue, 0.2F);
		drawPixel(5, 3, pRed, pGreen, pBlue, 0.2F);
		
		drawPixel(10, 4, pRed, pGreen, pBlue, 1F);
		drawPixel(10, 5, pRed, pGreen, pBlue, 1F);
		drawPixel(10, 6, pRed, pGreen, pBlue, 1F);
		drawPixel(11, 5, pRed, pGreen, pBlue, 1F);
		drawPixel(11, 6, pRed, pGreen, pBlue, 1F);
		drawPixel(11, 7, pRed, pGreen, pBlue, 1F);
		drawPixel(12, 7, pRed, pGreen, pBlue, 1F);
		drawPixel(12, 8, pRed, pGreen, pBlue, 1F);
		
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
    /**
     * Draw a pixel at location x y on icon
     */
    public void drawPixel(int x, int y, float red, float green, float blue, float alpha) {
    	GL11.glBegin(GL11.GL_QUADS);
    	GL11.glColor4f(red, green, blue, alpha);
    	// Draw a 8x8 square
    	GL11.glVertex3d(0 + x, 0 + y, 0);
    	GL11.glVertex3d(0 + x, 1 + y, 0);
    	GL11.glVertex3d(1 + x, 1 + y, 0);
    	GL11.glVertex3d(1 + x, 0 + y, 0);
  

    	GL11.glEnd();
    }

}

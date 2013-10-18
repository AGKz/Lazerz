package agkz.mods.lazerz.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class RenderLaserIcon {
	
	public void renderLaserItemIcon(ItemStack item, float[] primaryColor, float[] secondaryColor) {
		
		Icon icon = item.getIconIndex();
		this.renderIcon(0, 0, icon, 16, 16);
		
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
	
	public void renderIcon(int x, int y, Icon icon, int height, int width)
	{
	        Tessellator tessellator = Tessellator.instance;
	        tessellator.startDrawingQuads();
	        tessellator.addVertexWithUV((x), y + height, /*(double)this.zLevel*/0.0D, icon.getMinU(), icon.getMaxV());
	        tessellator.addVertexWithUV(x + width, y + height, 0.0D, icon.getMaxU(), icon.getMaxV());
	        tessellator.addVertexWithUV(x + width, (y), 0.0D, icon.getMaxU(), icon.getMinV());
	        tessellator.addVertexWithUV((x), (y), 0.0D, icon.getMinU(), icon.getMinV());
	        tessellator.draw();
    }
	/**
     * Draw a pixel at location x y on icon	     
     * */
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

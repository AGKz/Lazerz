package agkz.mods.lazerz.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import agkz.mods.lazerz.lazerz;

public class GuiLazer extends GuiContainer {
	
	public ContainerLazer container;
	  public String name;
	  private static final ResourceLocation background = new ResourceLocation(lazerz.modid + ":textures/gui/basicbackground.png");

	  public GuiLazer(ContainerLazer container)
	  {
	    super(container);

	    this.container = container;
	   
	  }

	  protected void drawGuiContainerForegroundLayer(int par1, int par2)
	  {
	    
	  }

	  protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	  {
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    this.mc.func_110434_K().func_110577_a(background);
	    int j = (this.width - this.xSize) / 2;
	    int k = (this.height - this.ySize) / 2;
	    drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
	  }
	 
}

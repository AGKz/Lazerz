package agkz.mods.lazerz.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import agkz.mods.lazerz.models.ModelConjurationTable;

public class ConjurationTableRenderer extends TileEntitySpecialRenderer {
	
	private final ModelConjurationTable model;
	public static final ResourceLocation RESOURCE_CONJURATION_TABLE_PNG = new ResourceLocation("textures/models/conjurationtabletexture.png");
	//private static TextureManager textureManager = mc.func_110434_K();
	
	public ConjurationTableRenderer() {
		this.model = new ModelConjurationTable();
	}
	
	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		GL11.glPushMatrix();
		GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float scale) {
		GL11.glPushMatrix(); //Tells renderer to start up
		GL11.glTranslatef((float) x + 0.5F,  (float) y + 1.5F, (float) z + 0.5F); //Setting location
		GL11.glScalef(0.1F, 0.1F, 0.1F);
		
		Minecraft.getMinecraft().renderEngine.func_110577_a(RESOURCE_CONJURATION_TABLE_PNG);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.625F);
		//GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		//this.model.render((Entity)null, 0.0F, 0.0F, 0F, 0.0F, 0.0F, 0F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	private void adjustLightFixture(World world, int i, int j, int k, Block block) {
		Tessellator tess = Tessellator.instance;
		float brightness = block.getBlockBrightness(world, i, j, k);
		int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int modulousModifier = skyLight % 65536;
		int divModifier = skyLight / 65536;
		
		tess.setColorOpaque_F(brightness, brightness, brightness);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier);
	}

}

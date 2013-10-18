package agkz.mods.lazerz.client;

import java.io.File;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import agkz.mods.lazerz.Lazerz;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PixelColor {
	
	private static IntBuffer pixels;
	private static int[] arrayPixels;

	    public static int getIntColorNumber(int widthInPixels, int heightInPixels)
	    {
	        try
	        {
	            int k = widthInPixels * heightInPixels;

	            if (pixels == null || pixels.capacity() < k)
	            {
	            	pixels = BufferUtils.createIntBuffer(k);
	                arrayPixels = new int[k];
	            }

	            GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, 1);
	            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
	            pixels.clear();
	            GL11.glReadPixels(0, 0, widthInPixels, heightInPixels, GL12.GL_BGRA, GL12.GL_UNSIGNED_INT_8_8_8_8_REV, pixels);
	            pixels.get(arrayPixels);
	            func_74289_a(arrayPixels, widthInPixels, heightInPixels);
	            return arrayPixels[k/2];
	        }
	        catch (Exception exception)
	        {
	            exception.printStackTrace();
	            Lazerz.logger.warning("Color was not found: " + exception.getMessage());
	            return 0;
	        }
	    }
	    
	    private static void func_74289_a(int[] arrayPixels, int width, int height)
	    {
	        int[] aint1 = new int[width];
	        int k = height / 2;

	        for (int l = 0; l < k; ++l)
	        {
	            System.arraycopy(arrayPixels, l * width, aint1, 0, width);
	            System.arraycopy(arrayPixels, (height - 1 - l) * width, arrayPixels, l * width, width);
	            System.arraycopy(aint1, 0, arrayPixels, (height - 1 - l) * width, width);
	        }
	    }

}

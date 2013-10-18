package agkz.mods.lazerz.common;

import agkz.mods.lazerz.Lazerz;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Loader;

public class ModSocialization {
	
	public static boolean isThaumcraftLoaded() {
		return Loader.isModLoaded("Thaumcraft");
	}
	
	public static boolean isEnderStorageLoaded() {
		return Loader.isModLoaded("EnderStorage");
	}
	
	public static boolean isModularPowersuitsLoaded() {
		return Loader.isModLoaded("powersuits");
	}
	
	private static Class Ic2Items;

	public static ItemStack getIC2Item(String name) {
		try {
			if (Ic2Items == null)
				Ic2Items = Class.forName("ic2.core.Ic2Items");
			
			Object ret = Ic2Items.getField(name).get(null);
			
			if (ret instanceof ItemStack) {
				return ((ItemStack) ret).copy();
			} else {
				return null;
			}
			
		} catch (Exception e) {
			Lazerz.logger.warning("IC2 API: Lazer'z call failed for getItem " + name);
			return null;
		}
	}
}

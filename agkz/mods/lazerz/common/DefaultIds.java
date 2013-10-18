package agkz.mods.lazerz.common;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

public final class DefaultIds
{
	private static HashMap<InternalName, Integer> idMap = new HashMap();

	public static int get(InternalName name)
	{
		if (!idMap.containsKey(name)) {
			throw new IllegalArgumentException("default id for " + name + " not registered");
		}
		return ((Integer)idMap.get(name)).intValue();
	}

	private static void add(InternalName name, int id)
	{
		if (idMap.containsValue(Integer.valueOf(id))) {
			throw new RuntimeException("duplicate default id: " + id);
		}

		idMap.put(name, Integer.valueOf(id));
	}

	static
	{

		
		
		add(InternalName.angleRail, 3065);
		
		add(InternalName.MiningLazerMK2, 25800);
		add(InternalName.lazerHandGun, 25801);
		add(InternalName.militaryLazerGun, 25802);
		add(InternalName.biologicalLazer, 25803);
		add(InternalName.cheatLazer, 25804);
		add(InternalName.utilityLazer, 25806);
		add(InternalName.EnderLazer, 25807);
		
		add(InternalName.genericItem, 25820);
		add(InternalName.HelmetMark2, 25821);
		add(InternalName.burntNetherStar, 25822);
		add(InternalName.chromaCast, 25823);
		
	 }
}
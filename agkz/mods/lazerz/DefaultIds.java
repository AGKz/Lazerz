package agkz.mods.lazerz;

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

	add(InternalName.coloredDust, 25800);
    add(InternalName.burntNetherStar, 25801);
    add(InternalName.MiningLazerMK2, 25802);
    add(InternalName.lazerHandGun, 25803);
    add(InternalName.militaryLazerGun, 25804);
    add(InternalName.biologicalLazer, 25805);
    add(InternalName.blockPlacerLazer, 25806);
    add(InternalName.cheatLazer, 25807);
    add(InternalName.nuclearLazer, 25808);
    add(InternalName.smartTrackerLazer, 25809);
  }
}
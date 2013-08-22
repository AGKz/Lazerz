package agkz.mods.lazerz;

import ic2.api.recipe.Recipes;

import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import agkz.mods.lazerz.event.LazerzEventManager;
import agkz.mods.lazerz.items.BurntNetherStarItem;
import agkz.mods.lazerz.items.ItemDusts;
import agkz.mods.lazerz.items.lazerItems;
import agkz.mods.lazerz.items.lazerz.MiningLazerMK2;
import agkz.mods.lazerz.items.lazerz.biologicalLazer;
import agkz.mods.lazerz.items.lazerz.blockplacerLazer;
import agkz.mods.lazerz.items.lazerz.cheatLazer;
import agkz.mods.lazerz.items.lazerz.lazerHandgun;
import agkz.mods.lazerz.items.lazerz.militaryLazerGun;
import agkz.mods.lazerz.items.lazerz.nuclearLazer;
import agkz.mods.lazerz.items.lazerz.smartTrackerLazer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = lazerz.modid, name = lazerz.name, version = lazerz.version)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class lazerz {
	
	// Instance of Lazerz
	@Instance("lazerz")
	public static lazerz instance;
	public static int defaultEnergyConsume;
	public static int defaultAcceleration;
	public static int netherStarXPAmount;
	
	public static String conjurationSound = "Machines/MaceratorOp.ogg";
	public static Configuration config;
		
	public static final String modid = "lazerz";
	public static final String version = "0.1";
	public static final String name = "Lazerz";
	public static Logger log;
	
	public static CreativeTabs lazerTab = new CreativeTabs("agk_lazerTab") {
		public ItemStack getIconItemStack() {
			return lazerItems.MiningLazerMK2;
		}
	};
	
	
	@SidedProxy(clientSide="agkz.mods.lazerz.client.ClientProxy", serverSide="agkz.mods.lazerz.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		LanguageRegistry.instance().addStringLocalization("itemGroup.agk_lazerTab", "Lazerz");
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		lazerItems.MiningLazerMK2 = new ItemStack(new MiningLazerMK2(config, InternalName.MiningLazerMK2));
		lazerItems.burntNetherStar = new ItemStack(new BurntNetherStarItem(config, InternalName.burntNetherStar));
		lazerItems.militaryLazerGun = new ItemStack(new militaryLazerGun(config, InternalName.militaryLazerGun));
		lazerItems.biologicalLazer = new ItemStack(new biologicalLazer(config, InternalName.biologicalLazer));
		lazerItems.blockPlacerLazer = new ItemStack(new blockplacerLazer(config, InternalName.blockPlacerLazer));
		lazerItems.cheatLazer = new ItemStack(new cheatLazer(config, InternalName.cheatLazer));
		lazerItems.lazerHandGun = new ItemStack(new lazerHandgun(config, InternalName.lazerHandGun));
		lazerItems.nuclearLazer = new ItemStack(new nuclearLazer(config, InternalName.nuclearLazer));
		lazerItems.smartTrackerLazer = new ItemStack(new smartTrackerLazer(config, InternalName.smartTrackerLazer));
		//lazerItems.photonLazer = new ItemStack(new photonLazerBlock(config, InternalName.photonLazer));
		
		lazerItems.blueDust = new ItemStack(new ItemDusts(config, InternalName.coloredDust), 1, 0);
		lazerItems.brownDust = new ItemStack(new ItemDusts(config, InternalName.coloredDust), 1, 1);
		lazerItems.cyanDust = new ItemStack(new ItemDusts(config, InternalName.coloredDust), 1, 2);
		lazerItems.grayDust = new ItemStack(new ItemDusts(config, InternalName.coloredDust), 1, 3);
		lazerItems.greenDust = new ItemStack(new ItemDusts(config, InternalName.coloredDust), 1, 4);
		lazerItems.lightBlueDust = new ItemStack(new ItemDusts(config, InternalName.coloredDust), 1, 5);
		lazerItems.lightGrayDust = new ItemStack(new ItemDusts(config, InternalName.coloredDust), 1, 6);
		lazerItems.limeDust = new ItemStack(new ItemDusts(config, InternalName.coloredDust), 1, 7);
		lazerItems.magentaDust = new ItemStack(new ItemDusts(config, InternalName.coloredDust), 1, 8);
		lazerItems.orangeDust = new ItemStack(new ItemDusts(config, InternalName.coloredDust), 1, 9);
		lazerItems.pinkDust = new ItemStack(new ItemDusts(config, InternalName.coloredDust), 1, 10);
		lazerItems.purpleDust = new ItemStack(new ItemDusts(config, InternalName.coloredDust), 1, 11);
		lazerItems.whiteDust = new ItemStack(new ItemDusts(config, InternalName.coloredDust), 1, 12);
		
        config.load();

       //Notice there is nothing that gets the value of this property so the expression results in a Property object.
        Property netherStarXPAmountProperty = config.get(Configuration.CATEGORY_GENERAL, "BurntNetherStarRequiredXP", 10);
        
        // Here we add a comment to our new property.
        netherStarXPAmountProperty.comment = "How many levels of experience are required to use the Burnt Nether Star?";
        
        netherStarXPAmount = netherStarXPAmountProperty.getInt();
        // this could also be:
        // int someInt = someProperty.getInt();
        // boolean someBoolean = someProperty.getBoolean(true);

        config.save();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event){
		
		//TODO Make working lazer
		//TODO Change lazer color dynamically
		//TODO Make dynamic recipe for lazer, changed color of lazer\
		
		
		LanguageRegistry.addName(lazerItems.MiningLazerMK2, "Mining Laser Plus");
		LanguageRegistry.addName(lazerItems.burntNetherStar, "Burnt Nether Star");
		LanguageRegistry.addName(lazerItems.biologicalLazer, "Biological Lazer");
		LanguageRegistry.addName(lazerItems.blockPlacerLazer, "Block Placer Lazer");
		LanguageRegistry.addName(lazerItems.cheatLazer, "Cheat Lazer");
		LanguageRegistry.addName(lazerItems.lazerHandGun, "Lazergun");
		LanguageRegistry.addName(lazerItems.militaryLazerGun, "Military Grade Lazer");
		LanguageRegistry.addName(lazerItems.nuclearLazer, "Nuclear Lazer");
		//LanguageRegistry.addName(lazerItems.photonLazer, "Photon Lazer");
		LanguageRegistry.addName(lazerItems.smartTrackerLazer, "SmartTracer Lazer");
		
		
		LanguageRegistry.addName(lazerItems.blueDust, "Lapis Dust");
		LanguageRegistry.addName(lazerItems.brownDust, "Cocoa Dust");
		LanguageRegistry.addName(lazerItems.cyanDust, "Cyan Dust");
		LanguageRegistry.addName(lazerItems.grayDust, "Gray Dust");
		LanguageRegistry.addName(lazerItems.greenDust, "Green Dust");
		LanguageRegistry.addName(lazerItems.lightBlueDust, "Light Blue Dust");
		LanguageRegistry.addName(lazerItems.lightGrayDust, "Light Gray Dust");
		LanguageRegistry.addName(lazerItems.limeDust, "Lime Dust");
		LanguageRegistry.addName(lazerItems.magentaDust, "Magenta Dust");
		LanguageRegistry.addName(lazerItems.orangeDust, "Orange Dust");
		LanguageRegistry.addName(lazerItems.pinkDust, "Pink Dust");
		LanguageRegistry.addName(lazerItems.purpleDust, "Purple Dust");
		LanguageRegistry.addName(lazerItems.whiteDust, "Bone Dust");
		
		//EntityRegistry.registerModEntity(EntityLazer.class, "Lazer", ModLoader.getUniqueEntityId(), this, 160, 5, true);
		
		NetworkRegistry.instance().registerGuiHandler(this, this.proxy);
		MinecraftForge.EVENT_BUS.register(new LazerzEventManager());
		//addRecipes();
		proxy.registerRenderers();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	public void addRecipes()
    {
		
		Recipes.macerator.addRecipe(new ItemStack(Item.dyePowder, 1, 4), null, lazerItems.blueDust);
		Recipes.macerator.addRecipe(new ItemStack(Item.dyePowder, 1, 3), null, lazerItems.brownDust);
		Recipes.macerator.addRecipe(new ItemStack(Item.dyePowder, 1, 6), null, lazerItems.cyanDust);
		Recipes.macerator.addRecipe(new ItemStack(Item.dyePowder, 1, 8), null, lazerItems.grayDust);
		Recipes.macerator.addRecipe(new ItemStack(Item.dyePowder, 1, 2), null, lazerItems.greenDust);
		Recipes.macerator.addRecipe(new ItemStack(Item.dyePowder, 1, 12), null, lazerItems.lightBlueDust);
		Recipes.macerator.addRecipe(new ItemStack(Item.dyePowder, 1, 7), null, lazerItems.lightGrayDust);
		Recipes.macerator.addRecipe(new ItemStack(Item.dyePowder, 1, 10), null, lazerItems.limeDust);
		Recipes.macerator.addRecipe(new ItemStack(Item.dyePowder, 1, 13), null, lazerItems.magentaDust);
		Recipes.macerator.addRecipe(new ItemStack(Item.dyePowder, 1, 14), null, lazerItems.orangeDust);
		Recipes.macerator.addRecipe(new ItemStack(Item.dyePowder, 1, 9), null, lazerItems.pinkDust);
		Recipes.macerator.addRecipe(new ItemStack(Item.dyePowder, 1, 6), null, lazerItems.purpleDust);
		Recipes.macerator.addRecipe(new ItemStack(Item.dyePowder, 1, 15), null, lazerItems.whiteDust);
		// Coal dust = black
		// Redstone = red
            
    }
	
}

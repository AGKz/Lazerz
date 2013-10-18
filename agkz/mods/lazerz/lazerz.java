package agkz.mods.lazerz;

import ic2.api.item.ElectricItem;
import ic2.api.item.Items;
import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.Recipes;

import java.util.Random;
import java.util.logging.Logger;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;

import org.lwjgl.input.Keyboard;

import agkz.mods.lazerz.client.LazerGUIKeyBind;
import agkz.mods.lazerz.client.gui.GuiHandler;
import agkz.mods.lazerz.common.CommonProxy;
import agkz.mods.lazerz.common.DataUtil;
import agkz.mods.lazerz.common.ModSocialization;
import agkz.mods.lazerz.common.items.LazerItems;
import agkz.mods.lazerz.common.managers.LaserEventManager;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Lazerz.modid, name = Lazerz.name, version = Lazerz.version)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class Lazerz {
	
	@SidedProxy(clientSide="agkz.mods.lazerz.client.ClientProxy", serverSide="agkz.mods.lazerz.common.CommonProxy")
	public static CommonProxy proxy;
	
	// Instance of Lazerz
	@Instance("lazerz")
	public static Lazerz instance;
	
	public static Logger logger;
	
	public static int defaultEnergyConsume;
	public static int defaultAcceleration;
	public static int netherStarXPAmount;
	
	public static Configuration config;

		
	static int startEntityId = 300;
		
	public static final String modid = "lazerz";
	public static final String version = "0.1";
	public static final String name = "Lazerz";
	
	
	
	
	public static DataUtil lazerDataUtil;
	
	
	
	public static CreativeTabs lazerTab = new CreativeTabs("agk_lazerTab") {
		public ItemStack getIconItemStack() {
			return LazerItems.MiningLazerMK2;
		}
	};
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		proxy.preInit(config);
		
		logger = event.getModLog();
		
		
		KeyBinding[] key = {new KeyBinding("Open Lazer GUI", Keyboard.KEY_U)};
		boolean[] repeat = {false};
		KeyBindingRegistry.registerKeyBinding(new LazerGUIKeyBind(key, repeat));
		
		new GuiHandler();
		
        
                		
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event){
		
		MinecraftForge.EVENT_BUS.register(new LaserEventManager());
		
		addNames();
		addRecipes();
		addChestGen();
	
		proxy.init();
	}
	
	@EventHandler
	public void postLoad(FMLPostInitializationEvent event) {
		if (ModSocialization.isThaumcraftLoaded()) {
			logger.info("Lazerz has provided a bridge to a whole new world");
		}
		if (ModSocialization.isEnderStorageLoaded()) {
			logger.info("Lazerz has provided a the most useful ender functionality");
		}
		if (ModSocialization.isModularPowersuitsLoaded()) {
			logger.info("Lazerz has provided a solution to your two favorite tools");
		}
	}
	
	public void addChestGen() {
		
		ItemStack handgun = LazerItems.lazerHandGun;
				
		ElectricItem.manager.charge(handgun, 2000 + new Random().nextInt(4000), 2147483647, true, false);
		
		this.addItemStackToAllChests(handgun, 1, 1, 8);
		
		this.addItemStackToAllChests(LazerItems.lazerLens, 1, 3, 10);
		
		this.addItemStackToAllChests(LazerItems.militaryLazerGun, 1, 1, 4);
		this.addItemStackToAllChests(LazerItems.biologicalLazer, 1, 1, 4);
		this.addItemStackToAllChests(LazerItems.MiningLazerMK2, 1, 1, 4);
	}
	
	public void addItemStackToAllChests(ItemStack itemstack, int min, int max, int chance) {
		ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(itemstack, min, max, chance));
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(itemstack, min, max, chance));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(itemstack, min, max, chance));
		ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(itemstack, min, max, chance));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(itemstack, min, max, chance));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(itemstack, min, max, chance));
		ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(itemstack, min, max, chance));
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(itemstack, min, max, chance));
	}
	
	
	public void addNames() {		
		
		 
	}
	
	public void addRecipes() {
		
		GameRegistry.addRecipe(LazerItems.biologicalLazer, new Object[] { "Gcc", "AAC", " AA", Character.valueOf('A'), ModSocialization.getIC2Item("advancedAlloy"), Character.valueOf('C'), ModSocialization.getIC2Item("advancedCircuit"), Character.valueOf('c'), ModSocialization.getIC2Item("energyCrystal"), Character.valueOf('G'), LazerItems.burntNetherStar });
		GameRegistry.addRecipe(LazerItems.MiningLazerMK2, new Object[] { "lcc", "AAC", " AA", Character.valueOf('A'), LazerItems.advancedLapisPlate, Character.valueOf('C'), ModSocialization.getIC2Item("advancedCircuit"), Character.valueOf('c'), ModSocialization.getIC2Item("energyCrystal"), Character.valueOf('l'), ModSocialization.getIC2Item("lapiDust") });
		GameRegistry.addRecipe(LazerItems.EnderLazer, new Object[] { "Ecc", "AAC", " AA", Character.valueOf('A'), LazerItems.advancedLapisPlate, Character.valueOf('C'), ModSocialization.getIC2Item("advancedCircuit"), Character.valueOf('c'), ModSocialization.getIC2Item("energyCrystal"), Character.valueOf('E'), Item.enderPearl });
		GameRegistry.addRecipe(LazerItems.lazerHandGun, new Object[] { "Gcc", "AAC", " AA", Character.valueOf('A'), ModSocialization.getIC2Item("casingtin"), Character.valueOf('C'), ModSocialization.getIC2Item("electronicCircuit"), Character.valueOf('c'), Items.getItem("reBattery"), Character.valueOf('G'), LazerItems.lazerLens });
		GameRegistry.addRecipe(LazerItems.nuclearLazer, new Object[] { "lcr", "AAC", " AA", Character.valueOf('A'), ModSocialization.getIC2Item("advancedAlloy"), Character.valueOf('C'), ModSocialization.getIC2Item("advancedCircuit"), Character.valueOf('c'), ModSocialization.getIC2Item("lapotronCrystal"), Character.valueOf('l'), ModSocialization.getIC2Item("UranFuel"), Character.valueOf('r'), ModSocialization.getIC2Item("nuclearReactor") });
		GameRegistry.addRecipe(LazerItems.militaryLazerGun, new Object[] { "Gcc", "AAC", " AA", Character.valueOf('A'), ModSocialization.getIC2Item("advancedAlloy"), Character.valueOf('C'), ModSocialization.getIC2Item("advancedCircuit"), Character.valueOf('c'), Items.getItem("energyCrystal"), Character.valueOf('G'), ModSocialization.getIC2Item("stoneDust")});
	
		
		Recipes.metalformerRolling.addRecipe(new RecipeInputOreDict("plateLapis"), null, LazerItems.advancedLapisPlate);
		GameRegistry.addShapelessRecipe(LazerItems.advancedLapisPlate, Items.getItem("advancedCircuit"), ModSocialization.getIC2Item("densePlateLapis"));
	
	}
	
	public void addConfig(Configuration config) {
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
	
}

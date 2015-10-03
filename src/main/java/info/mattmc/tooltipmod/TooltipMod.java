package info.mattmc.tooltipmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME)
public class TooltipMod {
	
	@Instance
	public static TooltipMod instance;
	
	@SidedProxy(clientSide = "info.mattmc.tooltipmod.client.ClientProxy", serverSide = "info.mattmc.tooltipmod.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		event.getModMetadata().version = Reference.VERSION;
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

}

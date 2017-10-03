/**
 * Copyright (c) 2016, MattsOnMc and Aroma1997
 * <p>
 * The Tooltipmod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.mattsmeets.tooltipmod;

import com.mattsmeets.tooltipmod.commands.CommandTooltip;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME)
public class TooltipMod {

	@Instance
	public static TooltipMod instance;

	@SidedProxy(clientSide = "com.mattsmeets.tooltipmod.client.ClientProxy", serverSide = "com.mattsmeets.tooltipmod.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		event.getModMetadata().version = Reference.VERSION;
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

	@EventHandler
	public void serverStartup(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandTooltip("tooltip"));
	}

}

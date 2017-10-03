/**
 * Copyright (c) 2016, MattsOnMc and Aroma1997
 * <p>
 * The Tooltipmod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.mattsmeets.tooltipmod.client;

import com.mattsmeets.tooltipmod.CommonProxy;
import com.mattsmeets.tooltipmod.commands.CommandTooltip;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		new EventListener();
		ClientCommandHandler.instance.registerCommand(new CommandTooltip("ctooltip"));
	}

}

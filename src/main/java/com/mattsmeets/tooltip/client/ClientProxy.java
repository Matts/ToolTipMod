/*
  Copyright (c) 2016 / 2017, Matt Smeets and Aroma1997
  <p>
  The Tooltipmod is distributed under the terms of the Minecraft Mod Public
  License 1.0, or MMPL. Please check the contents of the license located in
  http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.mattsmeets.tooltip.client;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.mattsmeets.tooltip.CommonProxy;
import com.mattsmeets.tooltip.commands.CommandTooltip;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		new EventListener();
		ClientCommandHandler.instance.registerCommand(new CommandTooltip("ctooltip"));
	}

}

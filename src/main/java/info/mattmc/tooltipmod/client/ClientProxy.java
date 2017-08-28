/**
 * Copyright (c) 2016, MattsOnMc and Aroma1997
 * <p>
 * The Tooltipmod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package info.mattmc.tooltipmod.client;

import info.mattmc.tooltipmod.CommonProxy;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		new EventListener();
	}

}

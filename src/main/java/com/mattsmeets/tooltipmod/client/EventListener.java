/**
 * Copyright (c) 2016, MattsOnMc and Aroma1997
 * <p>
 * The Tooltipmod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.mattsmeets.tooltipmod.client;

import com.mattsmeets.tooltipmod.TooltipHelper;
import org.lwjgl.input.Keyboard;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EventListener {

	EventListener() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void drawTooltip(ItemTooltipEvent event) {
		event.getToolTip().addAll(TooltipHelper.getItemInfo(event.getItemStack(), Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)));
	}

}

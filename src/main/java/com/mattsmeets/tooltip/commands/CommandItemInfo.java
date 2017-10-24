/*
  Copyright (c) 2016 / 2017, Matt Smeets and Aroma1997
  <p>
  The Tooltipmod is distributed under the terms of the Minecraft Mod Public
  License 1.0, or MMPL. Please check the contents of the license located in
  http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.mattsmeets.tooltip.commands;

import aroma1997.core.command.AromaBaseCommand;
import aroma1997.core.util.ServerUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

import com.mattsmeets.tooltip.TooltipHelper;

public class CommandItemInfo extends AromaBaseCommand {

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof EntityPlayer)) {
			throw new CommandException("Only Players can execute this command.");
		}
		int slot;
		if (args.length > 1) {
			throw new CommandException("Invalid amount of arguments.");
		} else if (args.length == 1) {
			slot = parseInt(args[0]);
		} else {
			slot = ((EntityPlayer) sender).inventory.currentItem;
		}
		ItemStack stack = ((EntityPlayer) sender).inventory.getStackInSlot(slot);
		sender.sendMessage(ServerUtil.getChatForString(stack.getDisplayName()));
		for (String info : TooltipHelper.getItemInfo(stack, true)) {
			sender.sendMessage(ServerUtil.getChatForString(info));
		}
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/iteminfo [slot number]";
	}
}

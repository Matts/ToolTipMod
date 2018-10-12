/*
  Copyright (c) 2016 / 2017, Matt Smeets and Aroma1997
  <p>
  The Tooltipmod is distributed under the terms of the Minecraft Mod Public
  License 1.0, or MMPL. Please check the contents of the license located in
  http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.mattsmeets.tooltip.commands;

import com.mattsmeets.tooltip.TooltipHelper;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

import aroma1997.core.command.AromaBaseCommand;
import aroma1997.core.util.ServerUtil;

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
		List<String> info = new ArrayList<>();
		info.add(stack.getDisplayName());

		addInfo(stack, info);

		for (String msg : info) {
			sender.sendMessage(ServerUtil.getChatForString(msg));
		}
	}

	protected void addInfo(ItemStack stack, List<String>info) {
		for (String current : TooltipHelper.getItemInfo(stack, true)) {
			info.add(current);
		}
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/iteminfo [slot number]";
	}
}

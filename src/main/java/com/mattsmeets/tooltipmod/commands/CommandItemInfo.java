package com.mattsmeets.tooltipmod.commands;


import com.mattsmeets.tooltipmod.TooltipHelper;

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

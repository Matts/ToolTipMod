package com.mattsmeets.tooltip.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

import aroma1997.core.command.AromaBaseCommand;
import aroma1997.core.util.ServerUtil;

public class CommandItemExtraTick extends AromaBaseCommand {
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (!(sender instanceof EntityPlayer)) {
			throw new CommandException("Only Players can execute this command.");
		}
		int slot = ((EntityPlayer) sender).inventory.currentItem;
		int ticks = 100;
		if (args.length > 2) {
			throw new CommandException("Invalid amount of arguments.");
		} else if (args.length == 2) {
			slot = parseInt(args[1]);
			ticks = parseInt(args[0]);
		} else if (args.length == 1) {
			ticks = parseInt(args[0]);
		}
		ItemStack stack = ((EntityPlayer) sender).inventory.getStackInSlot(slot);

		for (int i = 0; i < ticks; i++) {
			stack.updateAnimation(((EntityPlayer) sender).world, sender.getCommandSenderEntity(), slot, slot == ((EntityPlayer) sender).inventory.currentItem);
		}

		sender.sendMessage(ServerUtil.getChatForString("Did " + ticks + " extra ticks."));

	}
}

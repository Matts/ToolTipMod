package com.mattsmeets.tooltipmod.commands;

import java.util.ArrayList;
import java.util.List;

import com.mattsmeets.tooltipmod.TooltipHelper;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

import aroma1997.core.command.AromaBaseCommand;
import aroma1997.core.util.ServerUtil;

public class CommandBlockInfo extends AromaBaseCommand {
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		BlockPos targetPos;
		if (args.length == 3) {
			targetPos = new BlockPos(
					parseInt(args[0]),
					parseInt(args[1]),
					parseInt(args[2])
			);
		} else if (args.length == 0) {
			if (sender instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) sender;
				Vec3d eyePos = player.getPositionEyes(0);
				RayTraceResult raytrace = player.world.rayTraceBlocks(eyePos, eyePos.add(player.getLook(0).scale(5D)));
				if (raytrace == null) {
					throw new CommandException("Could not find block collision.");
				}
				targetPos = raytrace.getBlockPos();
			} else {
				throw new CommandException("Non-Players need to specify a position for the block to look up.");
			}
		} else {
			throw new CommandException("Invalid amount of arguments specified.");
		}
		sender.sendMessage(ServerUtil.getChatForString(targetPos + ""));
		sender.sendMessage(ServerUtil.getChatForString(sender.getEntityWorld().getBlockState(targetPos) + ""));
		TileEntity te = sender.getEntityWorld().getTileEntity(targetPos);
		if (te == null) {
			sender.sendMessage(ServerUtil.getChatForString("No TE detected."));
		} else {
			NBTTagCompound nbt = te.writeToNBT(new NBTTagCompound());
			List<String> info = new ArrayList<>();
			TooltipHelper.addNBTTooltip(nbt, info);
			for (String str : info) {
				sender.sendMessage(ServerUtil.getChatForString(str));
			}
		}
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/blockinfo [<x> <y> <z>]";
	}
}

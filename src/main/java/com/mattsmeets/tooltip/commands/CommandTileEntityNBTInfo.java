/*
  Copyright (c) 2016 / 2017, Matt Smeets and Aroma1997
  <p>
  The Tooltipmod is distributed under the terms of the Minecraft Mod Public
  License 1.0, or MMPL. Please check the contents of the license located in
  http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.mattsmeets.tooltip.commands;

import java.util.ArrayList;
import java.util.List;

import com.mattsmeets.tooltip.TooltipHelper;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CommandTileEntityNBTInfo extends CommandBlockInfo {

	@Override
	public List<String> getBlockInfo(World world, BlockPos pos) {
		List<String> list = new ArrayList<>();
		TileEntity te = world.getTileEntity(pos);
		if (te == null) {
			list.add("No TE detected.");
		} else {
			NBTTagCompound nbt = te.writeToNBT(new NBTTagCompound());
			TooltipHelper.addNBTTooltip(nbt, list);
		}
		return list;
	}
}

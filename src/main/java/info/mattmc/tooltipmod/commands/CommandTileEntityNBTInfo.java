package info.mattmc.tooltipmod.commands;

import java.util.ArrayList;
import java.util.List;

import info.mattmc.tooltipmod.TooltipHelper;

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

/*
  Copyright (c) 2016 / 2017, Matt Smeets and Aroma1997
  <p>
  The Tooltipmod is distributed under the terms of the Minecraft Mod Public
  License 1.0, or MMPL. Please check the contents of the license located in
  http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.mattsmeets.tooltip.commands;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import aroma1997.core.coremod.asm.AsmUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CommandTileEntityFieldInfo extends CommandBlockInfo {

	@Override
	public List<String> getBlockInfo(World world, BlockPos pos) {
		List<String> list = new ArrayList<>();
		TileEntity te = world.getTileEntity(pos);
		if (te == null) {
			list.add("No TE detected.");
		} else {
			addTileEntityInfo(te.getClass(), te, list);
		}
		return list;
	}

	private void addTileEntityInfo(Class<?> clazz, Object o, List<String> list) {
		if (clazz == Object.class || clazz == TileEntity.class) {
			return;
		}
		list.add("Class: " + clazz.getName());
		for (Class<?> iface : clazz.getInterfaces()) {
			list.add("Iface: " + iface.getName());
		}

		for (Field field : clazz.getDeclaredFields()) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			if ((field.getModifiers() & Modifier.STATIC) == 0) {
				try {
					list.add(AsmUtils.getDescriptorForClass(field.getType()) + field.getName() + " = " + field.get(o));
				} catch (IllegalAccessException e) {}
			}
		}
		addTileEntityInfo(clazz.getSuperclass(), o, list);
	}
}

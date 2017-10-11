package info.mattmc.tooltipmod.commands;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import aroma1997.core.coremod.asm.AsmUtils;

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

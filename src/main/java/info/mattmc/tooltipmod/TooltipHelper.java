/**
 * Copyright (c) 2016, MattsOnMc and Aroma1997
 * <p>
 * The Tooltipmod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */

package info.mattmc.tooltipmod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class TooltipHelper {

	public static List<String> getItemInfo(ItemStack stack, boolean advanced) {
		List<String> tooltip = new ArrayList<>();
		if (stack == null) {
			tooltip.add("Null Stack!");
		} else if (stack.isEmpty()) {
			tooltip.add("Empty Stack!");
		} else {
			addRegistryName(stack, tooltip);
			addOreDictTooltip(stack, tooltip);
			addMetaTooltip(stack, tooltip);
			if (advanced) {
				addStackClassTooltip(stack, tooltip);
				if (stack.hasTagCompound()) {
					addNBTTooltip(stack.getTagCompound(), tooltip);
				} else {
					tooltip.add("No NBT Tags.");
				}
			}
		}
		return tooltip;
	}

	public static void addRegistryName(ItemStack stack, List<String> tooltip) {
		tooltip.add(stack.getItem().getRegistryName() + "");
	}

	public static void addOreDictTooltip(ItemStack stack, List<String> tooltip) {
		int[] ids = OreDictionary.getOreIDs(stack);
		if (ids.length != 0) {
			tooltip.add("OreDictionary names:");
			for (int id : ids) {
				tooltip.add("  " + OreDictionary.getOreName(id));
			}
		} else {
			tooltip.add("No OreDictionary names.");
		}
	}

	@Deprecated //Remove in 1.13
	public static void addMetaTooltip(ItemStack stack, List<String> tooltip) {
		tooltip.add("Damage: " + stack.getItemDamage() + "/" + stack.getMaxDamage());
		tooltip.add("Metadata:" + stack.getMetadata());
	}

	public static void addStackClassTooltip(ItemStack stack, List<String> tooltip) {
		Item item = stack.getItem();
		tooltip.add("Item class: " + item.getClass().getName());
		Block block = ForgeRegistries.BLOCKS.getValue(item.getRegistryName());
		if (block != null) {
			tooltip.add("Block class: " + block.getClass().getName());
		}
	}

	public static void addNBTTooltip(NBTTagCompound nbt, List<String> tooltip) {
		if (nbt == null) {
			tooltip.add("Null tag.");
			return;
		}
		addTagsToList("", nbt, tooltip);
	}

	private static void addTagsToList(String prefix, NBTTagCompound nbt, List<String> tooltip) {
		for (String key : nbt.getKeySet()) {
			NBTBase nbtNew = nbt.getTag(key);
			if (nbtNew == null) {
				continue;
			}
			if (nbtNew instanceof NBTTagCompound) {
				tooltip.add(prefix + key + " (" + getType(nbtNew) + ") =");
				addTagsToList(prefix + "  ", (NBTTagCompound) nbtNew, tooltip);
			} else if (nbtNew instanceof NBTTagList) {
				tooltip.add(prefix + key + " (" + getType(nbtNew) + ") =");
				addTagsToList(prefix + "  ", (NBTTagList) nbtNew, tooltip);
			} else {
				tooltip.add(prefix + "  " + key + " = " + nbtNew + " (" + getType(nbtNew) + ")");
			}
		}
	}

	private static void addTagsToList(String prefix, NBTTagList nbt, List<String> tooltip) {
		for (int i = 0; i < nbt.tagCount(); i++) {
			NBTTagCompound nbtNew = nbt.getCompoundTagAt(i);
			tooltip.add(prefix + "Tag " + i + ": (" + getType(nbtNew) + ")");
			addTagsToList(prefix + "  ", nbtNew, tooltip);
		}
	}

	private static String getType(NBTBase nbt) {
		return nbt.getClass().getSimpleName().substring(6);
	}
}

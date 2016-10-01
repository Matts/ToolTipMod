/**
 * Copyright (c) 2016, MattsOnMc and Aroma1997
 *
 * The Tooltipmod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package info.mattmc.tooltipmod.client;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class EventListener {

	EventListener() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void drawTooltip(ItemTooltipEvent event) {
		ItemStack stack = event.getItemStack();
		event.getToolTip().add("Registry name:");
		event.getToolTip().add("" + Item.REGISTRY.getNameForObject(stack.getItem()));
		int[] ids = OreDictionary.getOreIDs(stack);
		if (ids.length != 0) {
			event.getToolTip().add("OreDictionary names:");
			for (int id : ids) {
				event.getToolTip().add("    " + OreDictionary.getOreName(id));
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			event.getToolTip().add("Item Class: " + stack.getItem().getClass().getCanonicalName());
			if (stack.getItem() instanceof ItemBlock) {
				event.getToolTip().add("Block Class: " + ((ItemBlock)stack.getItem()).getBlock().getClass().getCanonicalName());
			}
			if (event.getItemStack().hasTagCompound()) {
				event.getToolTip().add("NBT Tags:");
				addTagsToList("", event.getItemStack().getTagCompound(), event.getToolTip());
			}
			else {
				event.getToolTip().add("No NBT Tags.");
			}
		}
		event.toolTip.add("Damage:");
		event.toolTip.add(stack.getItemDamage() + "/" + stack.getMaxDamage());
	}

	private static void addTagsToList(String prefix, NBTTagCompound nbt, List tooltip) {
		for (String key : nbt.getKeySet()) {
			NBTBase nbtNew = nbt.getTag(key);
			if (nbtNew == null) continue;
			if (nbtNew instanceof NBTTagCompound) {
				tooltip.add(prefix + key + " (" + getType(nbtNew) + ") =");
				addTagsToList(prefix + "  ", (NBTTagCompound) nbtNew, tooltip);
			}
			else if (nbtNew instanceof NBTTagList) {
				tooltip.add(prefix + key + " (" + getType(nbtNew) + ") =");
				addTagsToList(prefix + "  ", (NBTTagList)nbtNew, tooltip);
			}
			else {
				tooltip.add(prefix + "  " + key + " = " + nbtNew + " (" + getType(nbtNew) + ")");
			}
		}
	}

	private static void addTagsToList(String prefix, NBTTagList nbt, List tooltip) {
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

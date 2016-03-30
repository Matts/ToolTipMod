package info.mattmc.tooltipmod.client;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.item.Item;
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

@SideOnly(Side.CLIENT)
public class EventListener {

	EventListener() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void drawTooltip(ItemTooltipEvent event) {
		ItemStack stack = event.itemStack;
		event.toolTip.add("Registry name:");
		event.toolTip.add("" + Item.itemRegistry.getNameForObject(stack.getItem()));
		int[] ids = OreDictionary.getOreIDs(stack);
		if (ids.length != 0) {
			event.toolTip.add("OreDictionary names:");
			for (int id : ids) {
				event.toolTip.add("    " + OreDictionary.getOreName(id));
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			if (event.itemStack.hasTagCompound()) {
				event.toolTip.add("NBT Tags:");
				addTagsToList("", event.itemStack.getTagCompound(), event.toolTip);
			}
			else {
				event.toolTip.add("No NBT Tags.");
			}
		}
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
			tooltip.add(prefix + "(" + getType(nbtNew) + ")");
			addTagsToList(prefix + "  ", nbtNew, tooltip);
		}
	}

	private static String getType(NBTBase nbt) {
		return nbt.getClass().getSimpleName().substring(6);
	}

}

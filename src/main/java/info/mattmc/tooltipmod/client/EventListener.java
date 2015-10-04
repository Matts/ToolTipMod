package info.mattmc.tooltipmod.client;

import java.util.List;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EventListener {
	
	EventListener() {
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@SubscribeEvent
	public void drawTooltip(ItemTooltipEvent event) {
		ItemStack stack = event.itemStack;
		event.toolTip.add("Registry name:");
		event.toolTip.add(Item.itemRegistry.getNameForObject(stack.getItem()));
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
				addTagsToList("", event.itemStack.stackTagCompound, event.toolTip);
			}
			else {
				event.toolTip.add("No NBT Tags.");
			}
		}
	}
	
	private static void addTagsToList(String prefix, NBTTagCompound nbt, List tooltip) {
		for (String key : (Set<String>)nbt.func_150296_c()) {
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

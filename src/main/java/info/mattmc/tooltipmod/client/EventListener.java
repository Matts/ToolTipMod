package info.mattmc.tooltipmod.client;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

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
	}

}

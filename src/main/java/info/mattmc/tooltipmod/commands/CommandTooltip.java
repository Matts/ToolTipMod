package info.mattmc.tooltipmod.commands;

import aroma1997.core.command.AromaSubCommand;

public class CommandTooltip extends AromaSubCommand {

	public CommandTooltip(String... names) {
		super(names);
		addSubCommand(new CommandItemInfo());
		addSubCommand(new CommandBlockInfo());
		addSubCommand(new CommandTileEntityNBTInfo());
	}

}

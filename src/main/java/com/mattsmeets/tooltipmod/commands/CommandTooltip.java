/*
  Copyright (c) 2016 / 2017, MattsOnMc and Aroma1997
  <p>
  The Tooltipmod is distributed under the terms of the Minecraft Mod Public
  License 1.0, or MMPL. Please check the contents of the license located in
  http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package com.mattsmeets.tooltipmod.commands;

import aroma1997.core.command.AromaSubCommand;

public class CommandTooltip extends AromaSubCommand {

    public CommandTooltip(String... names) {
        super(names);
        addSubCommand(new CommandItemInfo());
        addSubCommand(new CommandBlockInfo());
    }

}

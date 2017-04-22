/**
 * Copyright (c) 2016, MattsOnMc and Aroma1997
 *
 * The Tooltipmod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package info.mattmc.tooltipmod;

import java.io.InputStream;
import java.util.Properties;

import com.google.common.base.Throwables;

public class Reference {

	private static String getProp(String id) {
		if (prop == null) {
			try {
				prop = new Properties();
				InputStream stream = Reference.class
						.getResourceAsStream("reference.properties");
				prop.load(stream);
				stream.close();
			} catch (Exception e) {
				prop = null;
				Throwables.propagate(e);
			}
		}
		return prop.getProperty(id);
	}

	private static Properties prop;

	public static final String MOD_ID = "tooltipmod";

	public static final String MOD_NAME = "TooltipMod";

	public static final String VERSION = getProp("version");

}

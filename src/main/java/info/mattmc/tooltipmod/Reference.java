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

	public static final String MOD_ID = "TooltipMod";

	public static final String MOD_NAME = MOD_ID;

	public static final String VERSION = getProp("version");

}

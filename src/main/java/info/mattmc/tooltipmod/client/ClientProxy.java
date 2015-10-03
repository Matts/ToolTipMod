package info.mattmc.tooltipmod.client;

import info.mattmc.tooltipmod.CommonProxy;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void init() {
		new EventListener();
	}

}

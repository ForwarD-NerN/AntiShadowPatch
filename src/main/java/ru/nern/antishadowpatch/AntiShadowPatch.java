package ru.nern.antishadowpatch;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nern.antishadowpatch.config.ConfigurationManager;

public class AntiShadowPatch implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("antishadowpatch");
	public static ConfigurationManager.Config config = new ConfigurationManager.Config();

	@Override
	public void onInitialize() {
		ConfigurationManager.onInit();
	}
}

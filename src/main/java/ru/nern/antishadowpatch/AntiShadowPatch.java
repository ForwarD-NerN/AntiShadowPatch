package ru.nern.antishadowpatch;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nern.antishadowpatch.config.ConfigFixes;
import ru.nern.fconfiglib.v1.config.ConfigManager;
import ru.nern.fconfiglib.v1.config.LoggerWrapper;
import ru.nern.fconfiglib.v1.config.json.JsonConfigManager;

import java.util.Collections;

public class AntiShadowPatch implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("antishadowpatch");

	private static final LoggerWrapper wrapper = new LoggerWrapper() {
		@Override
		public void info(String message) {
			LOGGER.info(message);
		}

		@Override
		public void warn(String message) {
			LOGGER.warn(message);
		}

		@Override
		public void error(String message) {
			LOGGER.error(message);
		}
	};

	public static int CONFIG_VERSION = 2;
	public static ConfigManager<Config, JsonObject> configManager = JsonConfigManager
			.builderOf(Config.class)
			.modId("antishadowpatch")
			.validateFields(false)
			.fixers(Sets.newLinkedHashSet(Collections.singleton(ConfigFixes.V2_FIXER)))
			.logger(wrapper)
			.version(CONFIG_VERSION).create();

	@Override
	public void onInitialize() {
		configManager.init();
	}

	public static Config config() {
		return configManager.config();
	}

	public static class Config
	{
		public BlockUpdates Block_Updates = new BlockUpdates();
		public Blocks Blocks = new Blocks();
		public BlockEntities Block_Entities = new BlockEntities();
		public Items Items = new Items();
		public World World = new World();
		public Entities Entities = new Entities();
		public Misc Misc = new Misc();

		public static class BlockUpdates {
			public boolean bringBackSOSuppression = true;
			public boolean bringBackCCESuppression = true;
			public boolean bringBackTrapdoorUpdateSkipping = true;
		}

		public static class Blocks {
			public boolean bringBackFloatingRedstoneComponentsOnTopOfTrapdoor = true;
			public boolean bringBackFurnaceXPDupe = true;
		}

		public static class BlockEntities {
			public boolean bringBackBlockEntitySwap = true;
			public boolean bringBackBlocksWithSwappedBlockEntities = true;
		}

		public static class Items {
			public boolean bringBackItemShadowing_1_17 = true;
			public boolean bringBackItemShadowing_1_18 = true;
		}

		public static class World {
			public boolean bringBackChunkSaveState_1_14 = false;
		}

		public static class Entities {
			public boolean bringBackOldDragonFreezing = true;
			public boolean bringBackArmorStandInvulnerableToWitherDamage = true;
			public boolean bringBackShadowItemsInMobInventory = true;
		}

		public static class Misc {
			public boolean bringBackGracefulSOHandling = true;
			public boolean bringBackGracefulOOMHandling = true;
		}
	}
}

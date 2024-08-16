package ru.nern.antishadowpatch;

import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nern.antishadowpatch.config.ConfigFixes;
import ru.nern.fconfiglib.v1.ConfigManager;
import ru.nern.fconfiglib.v1.LoggerWrapper;
import ru.nern.fconfiglib.v1.json.JsonConfigManager;

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

	public static boolean isConfigInitialized() {
		return configManager.isInitialized();
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
			public boolean BringBackSOSuppression = true;
			public boolean BringBackCCESuppression = true;
			public boolean BringBackTrapdoorUpdateSkipping = true;
		}

		public static class Blocks {
			public boolean BringBackFloatingRedstoneComponentsOnTopOfTrapdoor = true;
			public boolean BringBackFurnaceXPDupe = true;
		}

		public static class BlockEntities {
			public boolean BringBackBlockEntitySwap = true;
			public boolean BringBackBlocksWithSwappedBlockEntities = true;
		}

		public static class Items {
			public boolean BringBackItemShadowing_1_17 = true;
			public boolean BringBackItemShadowing_1_18 = true;
		}

		public static class World {
			public boolean BringBackChunkSaveState_1_14 = false;
		}

		public static class Entities {
			public boolean BringBackOldDragonFreezing = true;
			public boolean BringBackArmorStandInvulnerableToWitherDamage = true;
			public boolean BringBackShadowItemsInMobInventory = true;
		}

		public static class Misc {
			public boolean BringBackGracefulSOHandling = true;
			public boolean BringBackGracefulOOMHandling = true;
		}
	}
}

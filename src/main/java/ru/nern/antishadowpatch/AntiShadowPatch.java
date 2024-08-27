package ru.nern.antishadowpatch;

import com.google.gson.JsonObject;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nern.antishadowpatch.config.ConfigFixes;
import ru.nern.fconfiglib.v1.ConfigManager;
import ru.nern.fconfiglib.v1.api.annotations.mixins.MixinOption;
import ru.nern.fconfiglib.v1.json.JsonConfigManager;
import ru.nern.fconfiglib.v1.log.Sl4jLoggerWrapper;

public class AntiShadowPatch implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("antishadowpatch");

	public static int CONFIG_VERSION = 2;
	public static ConfigManager<Config, JsonObject> configManager = JsonConfigManager
			.builderOf(Config.class)
			.modId("antishadowpatch")
			.fixers((fixerMap) -> fixerMap.put(2, ConfigFixes.V2_FIXER))
			.logger(Sl4jLoggerWrapper.createFrom(LOGGER))
			.version(CONFIG_VERSION).create();

	@Override
	public void onInitialize() {}

	public static Config config() {
		return configManager.config();
	}

	public static class Config {
		public BlockUpdates Block_Updates = new BlockUpdates();
		public Blocks Blocks = new Blocks();
		public BlockEntities Block_Entities = new BlockEntities();
		public Items Items = new Items();
		public World World = new World();
		public Entities Entities = new Entities();
		public Misc Misc = new Misc();

		public static class BlockUpdates {
			@MixinOption({"block.update.WorldMixin", "block.update.SimpleNeighborUpdaterMixin"})
			public boolean BringBackSOSuppression = true;

			@MixinOption("block.update.ShulkerBoxBlockMixin")
			public boolean BringBackCCESuppression = true;

			@MixinOption("block.update.RedstoneWireBlockMixin")
			public boolean BringBackTrapdoorUpdateSkipping = true;
		}

		public static class Blocks {
			@MixinOption("block.floating.*")
			public boolean BringBackFloatingRedstoneComponentsOnTopOfTrapdoor = true;

			@MixinOption("block.AbstractFurnaceBlockMixin")
			public boolean BringBackFurnaceXPDupe = true;
		}

		public static class BlockEntities {
			@MixinOption("block.entity.BlockEntityMixin")
			public boolean BringBackBlockEntitySwap = true;

			@MixinOption("block.entity.obtain.*")
			public boolean BringBackBlocksWithSwappedBlockEntities = true;
		}

		public static class Items {
			@MixinOption("item.ScreenHandlerMixin_1_17")
			public boolean BringBackItemShadowing_1_17 = true;

			@MixinOption("item.ScreenHandlerMixin_1_18")
			public boolean BringBackItemShadowing_1_18 = true;
		}

		public static class World {
			@MixinOption("world.RegionFileMixin")
			public boolean BringBackChunkSaveState_1_14 = false;
		}

		public static class Entities {
			@MixinOption("entity.EnderDragonEntityMixin")
			public boolean BringBackOldDragonFreezing = true;

			@MixinOption("entity.ArmorStandEntityMixin")
			public boolean BringBackArmorStandInvulnerableToWitherDamage = true;

			@MixinOption("entity.MobEntityMixin")
			public boolean BringBackShadowItemsInMobInventory = true;
		}

		public static class Misc {
			@MixinOption("misc.SystemDetailsMixin")
			public boolean BringBackGracefulSOHandling = true;

			@MixinOption("misc.NetworkThreadUtils")
			public boolean BringBackGracefulOOMHandling = true;
		}
	}
}

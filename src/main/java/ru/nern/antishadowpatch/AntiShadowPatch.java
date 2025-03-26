package ru.nern.antishadowpatch;

import com.google.gson.JsonObject;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nern.antishadowpatch.config.ConfigFixes;
import ru.nern.fconfiglib.v1.ConfigManager;
import ru.nern.fconfiglib.v1.api.annotations.mixins.MixinOption;
import ru.nern.fconfiglib.v1.api.annotations.validation.ConfigValidators;
import ru.nern.fconfiglib.v1.json.JsonConfigManager;
import ru.nern.fconfiglib.v1.log.Sl4jLoggerWrapper;
import ru.nern.fconfiglib.v1.validation.VersionConfigValidator;

public class AntiShadowPatch implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("antishadowpatch");

	public static int CONFIG_VERSION = 3;
	public static ConfigManager<Config, JsonObject> configManager = JsonConfigManager
			.builderOf(Config.class)
			.modId("antishadowpatch")
			.fixers((fixers) ->
					fixers.put(2, ConfigFixes.V2_FIXER))
			.logger(Sl4jLoggerWrapper.createFrom(LOGGER))
			.version(CONFIG_VERSION).create();

	@Override
	public void onInitialize() {}

	public static Config config() {
		return configManager.config();
	}

	@ConfigValidators({
			VersionConfigValidator.class,
	})
	public static class Config {
		public BlockUpdates Block_Updates = new BlockUpdates();
		public Blocks Blocks = new Blocks();
		public BlockEntities Block_Entities = new BlockEntities();
		public Items Items = new Items();
		public World World = new World();
		public Entities Entities = new Entities();
		public Misc Misc = new Misc();

		public static class BlockUpdates {
			@MixinOption({"block_updates.WorldMixin", "block_updates.SimpleNeighborUpdaterMixin"})
			public boolean BringBackSOSuppression = true;

			@MixinOption("block_updates.ShulkerBoxBlockMixin")
			public boolean BringBackCCESuppression = true;

			@MixinOption("block_updates.RedstoneWireBlockMixin")
			public boolean BringBackTrapdoorUpdateSkipping = true;
		}

		public static class Blocks {
			@MixinOption("blocks.floating.*")
			public boolean BringBackFloatingRedstoneComponentsOnTopOfTrapdoor = true;

			public boolean BringBackFurnaceXPDupe = true;

			@MixinOption("blocks.FullBlockCollisionsMixin.*") // https://bugs.mojang.com/browse/MC/issues/MC-295395
			public boolean BringBackFullBlockInnerCollisions = false;
		}

		public static class BlockEntities {
			@MixinOption("block_entities.obtain.*")
			public boolean BringBackBlockEntitySwap = true;

			@MixinOption("block_entities.BlockEntityMixin")
			public boolean KeepBlocksWithSwappedBlockEntities = true;
		}

		public static class Items {
			@MixinOption("items.shadowing.ScreenHandlerMixin_1_17")
			public boolean BringBackItemShadowing_1_17 = true;

			@MixinOption("items.shadowing.ScreenHandlerMixin_1_18")
			public boolean BringBackItemShadowing_1_18 = true;
		}

		public static class World {
			@MixinOption("world.RegionFileMixin")
			public boolean BringBackChunkSaveState_1_14 = false;

			@MixinOption("network.BookUpdateC2SPacketMixin")
			public boolean BringBackChunkSaveState_1_21 = false;
		}

		public static class Entities {
			@MixinOption("entities.EnderDragonEntityMixin")
			public boolean BringBackOldDragonFreezing = true;

			@MixinOption("entities.ArmorStandEntityMixin")
			public boolean BringBackArmorStandInvulnerableToWitherDamage = true;

			@MixinOption("entities.MobEntityMixin")
			public boolean BringBackShadowItemsInMobInventory = true;

			@MixinOption("entities.MerchantEntityMixin")
			public boolean BringBackVoidlessVoidTrading = true;
		}

		public static class Misc {
			@MixinOption("misc.SystemDetailsMixin")
			public boolean BringBackGracefulSOHandling = true;

			@MixinOption("network.NetworkThreadUtilsMixin")
			public boolean BringBackGracefulOOMHandling = true;
		}
	}

}

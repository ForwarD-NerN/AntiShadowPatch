package ru.nern.antishadowpatch.config;

import com.google.gson.JsonObject;
import ru.nern.antishadowpatch.AntiShadowPatch;
import ru.nern.fconfiglib.v1.config.ConfigFixer;

import static ru.nern.fconfiglib.v1.config.json.JsonConfigUtils.*;

public class ConfigFixes {

    public static ConfigFixer<AntiShadowPatch.Config, JsonObject> V2_FIXER = new ConfigFixer<>(2) {
        @Override
        public void apply(AntiShadowPatch.Config config, JsonObject raw) {
            AntiShadowPatch.LOGGER.info("Converting to config V2...");
            createPath(raw, "Block_Updates");
            createPath(raw, "Blocks");
            createPath(raw, "Block_Entities");
            createPath(raw, "Items");
            createPath(raw, "World");
            createPath(raw, "Entities");
            createPath(raw, "Misc");

            move(raw, "blocks.bringBackStackOverflowSuppression", "Block_Updates.bringBackSOSuppression");
            move(raw, "blocks.bringBackCCESuppression", "Block_Updates");
            move(raw, "blocks.bringBackFloatingRedstoneComponentsOnTopOfTrapdoor", "Blocks.bringBackFloatingRedstoneComponentsOnTopOfTrapdoor");
            move(raw, "blocks.bringBackTrapdoorUpdateSkipping", "Block_Updates");
            move(raw, "entities.bringBackInfiniteFurnaceXPBug", "Blocks.bringBackFurnaceXPDupe");
            move(raw, "entities.bringBackOldDragonFreezing", "Entities.bringBackOldDragonFreezing");
            move(raw, "entities.bringBackShadowItemsInMobInventory", "Entities.bringBackShadowItemsInMobInventory");
            move(raw, "blocks.bringBackBlockEntitySwap", "Block_Entities.bringBackBlockEntitySwap");
            move(raw, "blocks.bringBackSwappedBlockEntitiesExistence", "Block_Entities.bringBackBlocksWithSwappedBlockEntities");
            move(raw, "items.bringBack1_17ItemShadowing", "Items.bringBackItemShadowing_1_17");
            move(raw, "items.bringBack1_18ItemShadowing", "Items.bringBackItemShadowing_1_18");
            move(raw, "blocks.bringBackChunkSaveState", "World.bringBackChunkSaveState_1_14");
            move(raw, "misc.bringBackWitherInvulnerableArmorStands", "Entities.bringBackArmorStandInvulnerableToWitherDamage");
            move(raw, "blocks.bringBackGracefulOOMHandling", "Misc.bringBackGracefulOOMHandling");
            move(raw, "blocks.gracefulStackOverflowHandling", "Misc.bringBackGracefulSOHandling");
            AntiShadowPatch.LOGGER.info("Conversion completed");
        }
    };
}

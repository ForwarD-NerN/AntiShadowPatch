package ru.nern.antishadowpatch.config;

import com.google.gson.JsonObject;
import ru.nern.antishadowpatch.AntiShadowPatch;
import ru.nern.fconfiglib.v1.api.ConfigFixer;

import static ru.nern.fconfiglib.v1.json.JsonConfigUtils.createPath;
import static ru.nern.fconfiglib.v1.json.JsonConfigUtils.move;

public class ConfigFixes {

    public static ConfigFixer<AntiShadowPatch.Config, JsonObject> V2_FIXER = (config, raw) -> {
        AntiShadowPatch.LOGGER.info("Converting to config V2...");
        createPath(raw, "Block_Updates");
        createPath(raw, "Blocks");
        createPath(raw, "Block_Entities");
        createPath(raw, "Items");
        createPath(raw, "World");
        createPath(raw, "Entities");
        createPath(raw, "Misc");

        move(raw, "blocks.bringBackStackOverflowSuppression", "Block_Updates.BringBackSOSuppression");
        move(raw, "blocks.bringBackCCESuppression", "Block_Updates.BringBackCCESuppression");
        move(raw, "blocks.bringBackFloatingRedstoneComponentsOnTopOfTrapdoor", "Blocks.BringBackFloatingRedstoneComponentsOnTopOfTrapdoor");
        move(raw, "blocks.bringBackTrapdoorUpdateSkipping", "Block_Updates.BringBackTrapdoorUpdateSkipping");
        move(raw, "entities.bringBackInfiniteFurnaceXPBug", "Blocks.BringBackFurnaceXPDupe");
        move(raw, "entities.bringBackOldDragonFreezing", "Entities.BringBackOldDragonFreezing");
        move(raw, "entities.bringBackShadowItemsInMobInventory", "Entities.BringBackShadowItemsInMobInventory");
        move(raw, "blocks.bringBackBlockEntitySwap", "Block_Entities.BringBackBlockEntitySwap");
        move(raw, "blocks.bringBackSwappedBlockEntitiesExistence", "Block_Entities.KeepBlocksWithSwappedBlockEntities");
        move(raw, "items.bringBack1_17ItemShadowing", "Items.BringBackItemShadowing_1_17");
        move(raw, "items.bringBack1_18ItemShadowing", "Items.BringBackItemShadowing_1_18");
        move(raw, "blocks.bringBackChunkSaveState", "World.BringBackChunkSaveState_1_14");
        move(raw, "misc.bringBackWitherInvulnerableArmorStands", "Entities.BringBackArmorStandInvulnerableToWitherDamage");
        move(raw, "blocks.bringBackGracefulOOMHandling", "Misc.BringBackGracefulOOMHandling");
        move(raw, "blocks.gracefulStackOverflowHandling", "Misc.BringBackGracefulSOHandling");
        AntiShadowPatch.LOGGER.info("Conversion completed");
    };
}

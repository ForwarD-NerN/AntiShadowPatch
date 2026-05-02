package ru.nern.antishadowpatch.mixin.block_updates;

import net.minecraft.world.level.redstone.InstantNeighborUpdater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(InstantNeighborUpdater.class)
public class InstantNeighborUpdaterMixin {
    @ModifyArg(method = "shapeUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/redstone/NeighborUpdater;executeShapeUpdate(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;II)V"),
            index = 6)
    private int antishadowpatch$correctUpdateDepth(int maxUpdateDepth) {
        return maxUpdateDepth + 1;
    }
}

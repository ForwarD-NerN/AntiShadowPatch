package ru.nern.antishadowpatch.mixin.block_updates;

import net.minecraft.world.block.SimpleNeighborUpdater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SimpleNeighborUpdater.class)
public class SimpleNeighborUpdaterMixin {
    @ModifyArg(method = "replaceWithStateForNeighborUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/block/NeighborUpdater;replaceWithStateForNeighborUpdate(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/Direction;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;II)V"),
            index = 6)
    private int antishadowpatch$correctUpdateDepth(int maxUpdateDepth) {
        return maxUpdateDepth + 1;
    }
}

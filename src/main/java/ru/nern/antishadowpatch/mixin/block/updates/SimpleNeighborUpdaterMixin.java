package ru.nern.antishadowpatch.mixin.block.updates;

import net.minecraft.world.block.SimpleNeighborUpdater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(SimpleNeighborUpdater.class)
public class SimpleNeighborUpdaterMixin {
    @ModifyArg(method = "replaceWithStateForNeighborUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/block/NeighborUpdater;replaceWithStateForNeighborUpdate(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/Direction;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;II)V"),
            index = 6)
    private int antishadowpatch$correctUpdateDepth(int maxUpdateDepth) {
        return AntiShadowPatch.config().Block_Updates.BringBackSOSuppression ? maxUpdateDepth + 1 : maxUpdateDepth;
    }
}

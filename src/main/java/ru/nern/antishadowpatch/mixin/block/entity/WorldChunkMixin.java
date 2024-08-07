package ru.nern.antishadowpatch.mixin.block.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(WorldChunk.class)
public class WorldChunkMixin {
    @ModifyExpressionValue(
            method = "setBlockState",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/BlockEntity;supports(Lnet/minecraft/block/BlockState;)Z")
    )
    private boolean antishadowpatch$bringBackBlockEntitySwap(boolean support) {
        return support || AntiShadowPatch.config.blocks.bringBackBlockEntitySwap;
    }
}

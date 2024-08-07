package ru.nern.antishadowpatch.mixin.block.entity;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(BlockEntity.class)
public class BlockEntityMixin {
    @WrapWithCondition(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/BlockEntity;validateSupports(Lnet/minecraft/block/BlockState;)V")
    )
    private boolean antishadowpatch$bringBackBlockEntitySwap(BlockEntity instance, BlockState state) {
        return !AntiShadowPatch.config.blocks.bringBackBlockEntitySwap;
    }
}

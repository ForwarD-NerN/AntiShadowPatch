package ru.nern.antishadowpatch.mixin.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(BlockEntity.class)
public class BlockEntityMixin {
    @Inject(method = "supports", at = @At("HEAD"), cancellable = true)
    private void antishadowpatch$bringBackSwappedBlockEntitiesExistence(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if(AntiShadowPatch.config().Block_Entities.bringBackBlocksWithSwappedBlockEntities) {
            cir.setReturnValue(true);
        }
    }
}

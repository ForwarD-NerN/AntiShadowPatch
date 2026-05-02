package ru.nern.antishadowpatch.mixin.block_entities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(BlockEntity.class)
public class BlockEntityMixin {

    @Inject(method = "isValidBlockState", at = @At("HEAD"), cancellable = true)
    private void antishadowpatch$bringBackSwappedBlockEntitiesExistence(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}

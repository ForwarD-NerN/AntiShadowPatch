package ru.nern.antishadowpatch.mixin.block_entities.obtain;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LecternBlockEntity.class)
public abstract class LecternBlockEntityMixin {
    @Shadow public abstract void clear();

    // In 1.21.5 Mojang removed this method call
    @Inject(method = "onBlockReplaced", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z", shift = At.Shift.AFTER))
    private void antishadowpatch$updateBlockEntity(BlockPos pos, BlockState oldState, CallbackInfo ci) {
        clear();
    }

}

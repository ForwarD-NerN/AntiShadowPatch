package ru.nern.antishadowpatch.mixin.block_updates.eid;

import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.atomic.AtomicInteger;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
    @Shadow
    @Final
    private static AtomicInteger ENTITY_COUNTER;

    @Inject(method = "getNextEntityId", at = @At("HEAD"), cancellable = true)
    private void antishadowpatch$bringBackEntityIDSuppression(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(ENTITY_COUNTER.incrementAndGet());
    }
}

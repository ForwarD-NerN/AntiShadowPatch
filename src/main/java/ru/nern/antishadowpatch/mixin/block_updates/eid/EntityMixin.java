package ru.nern.antishadowpatch.mixin.block_updates.eid;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow
    private int id;

    // Reverting this change in order for EID suppression to work as before, or else it will cause an extra crash with id 0(that would probably happen anyway if id 0 is the player)
    @Inject(method = "getId", at = @At("HEAD"), cancellable = true)
    private void antishadowpatch$preventIllegalIdCrash(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.id);
    }
}

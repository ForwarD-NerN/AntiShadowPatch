package ru.nern.antishadowpatch.mixin.items.overstacking.movement;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract String toString();

    // How dare you Mojang
    @Inject(method = "capCount", at = @At("HEAD"), cancellable = true)
    private void antishadowpatch$doNotCapCount(int maxCount, CallbackInfo ci) {
        ci.cancel();
    }
}

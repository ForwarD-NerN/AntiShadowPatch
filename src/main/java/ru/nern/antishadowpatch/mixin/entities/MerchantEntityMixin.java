package ru.nern.antishadowpatch.mixin.entities;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MerchantEntity.class)
public abstract class MerchantEntityMixin {
    @Shadow @Nullable public abstract PlayerEntity getCustomer();

    @ModifyReturnValue(
            method = "canInteract",
            at = @At(value = "RETURN", ordinal = 0)
    )
    private boolean antishadowpatch$bringBackVoidlessVoidTrading(boolean original, @Local(argsOnly = true) PlayerEntity player) {
        return this.getCustomer() == player;
    }
}

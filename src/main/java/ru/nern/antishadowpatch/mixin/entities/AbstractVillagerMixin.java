package ru.nern.antishadowpatch.mixin.entities;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractVillager.class)
public abstract class AbstractVillagerMixin {
    @Shadow @Nullable public abstract Player getTradingPlayer();

    @ModifyReturnValue(
            method = "stillValid",
            at = @At(value = "RETURN", ordinal = 0)
    )
    private boolean antishadowpatch$bringBackVoidlessVoidTrading(boolean original, @Local(argsOnly = true, name = "player") Player player) {
        return this.getTradingPlayer() == player;
    }
}

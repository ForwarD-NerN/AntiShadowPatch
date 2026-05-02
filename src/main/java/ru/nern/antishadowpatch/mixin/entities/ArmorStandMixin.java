package ru.nern.antishadowpatch.mixin.entities;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.tags.TagKey;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStand.class)
public class ArmorStandMixin {

    @Redirect(
            method = "hurtServer",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSource;is(Lnet/minecraft/tags/TagKey;)Z", ordinal = 4)
    )
    private boolean antishadowpatch$bringBackWitherInvulnerableArmorStands(DamageSource source, TagKey<DamageType> tag) {
        return "player".equals(source.getMsgId());
    }

    @Redirect(
            method = "hurtServer",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSource;is(Lnet/minecraft/tags/TagKey;)Z", ordinal = 5)
    )
    private boolean antishadowpatch$bringBackWitherInvulnerableArmorStands2(DamageSource source, TagKey<DamageType> tag) {
        return source.getDirectEntity() instanceof AbstractArrow;
    }

    @Inject(method = "hurtServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/ArmorStand;kill(Lnet/minecraft/server/level/ServerLevel;)V", ordinal = 2, shift = At.Shift.AFTER), cancellable = true)
    private void antishadowpatch$bringBackWitherInvulnerableArmorStands3(ServerLevel world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(source.getDirectEntity() instanceof AbstractArrow projectile && projectile.getPierceLevel() > 0);
    }
}

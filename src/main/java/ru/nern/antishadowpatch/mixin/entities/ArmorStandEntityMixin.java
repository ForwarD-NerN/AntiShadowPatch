package ru.nern.antishadowpatch.mixin.entities;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStandEntity.class)
public class ArmorStandEntityMixin {

    @Redirect(
            method = "damage",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSource;isIn(Lnet/minecraft/registry/tag/TagKey;)Z", ordinal = 4)
    )
    private boolean antishadowpatch$bringBackWitherInvulnerableArmorStands(DamageSource source, TagKey<DamageType> tag) {
        return "player".equals(source.getName());
    }

    @Redirect(
            method = "damage",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSource;isIn(Lnet/minecraft/registry/tag/TagKey;)Z", ordinal = 5)
    )
    private boolean antishadowpatch$bringBackWitherInvulnerableArmorStands2(DamageSource source, TagKey<DamageType> tag) {
        return source.getSource() instanceof PersistentProjectileEntity;
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ArmorStandEntity;kill(Lnet/minecraft/server/world/ServerWorld;)V", ordinal = 2, shift = At.Shift.AFTER), cancellable = true)
    private void antishadowpatch$bringBackWitherInvulnerableArmorStands3(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(source.getSource() instanceof PersistentProjectileEntity projectile && projectile.getPierceLevel() > 0);
    }
}

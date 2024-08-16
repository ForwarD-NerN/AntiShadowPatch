package ru.nern.antishadowpatch.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(ArmorStandEntity.class)
public class ArmorStandEntityMixin {

    @WrapOperation(
            method = "damage",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSource;isIn(Lnet/minecraft/registry/tag/TagKey;)Z", ordinal = 4)
    )
    private boolean antishadowpatch$bringBackWitherInvulnerableArmorStands(DamageSource source, TagKey<DamageType> tag, Operation<Boolean> original) {
        return AntiShadowPatch.config().Entities.BringBackArmorStandInvulnerableToWitherDamage ? "player".equals(source.getName()) : original.call(source, tag);
    }

    @WrapOperation(
            method = "damage",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSource;isIn(Lnet/minecraft/registry/tag/TagKey;)Z", ordinal = 5)
    )
    private boolean antishadowpatch$bringBackWitherInvulnerableArmorStands2(DamageSource source, TagKey<DamageType> tag, Operation<Boolean> original) {
        return AntiShadowPatch.config().Entities.BringBackArmorStandInvulnerableToWitherDamage ? source.getSource() instanceof PersistentProjectileEntity : original.call(source, tag);
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ArmorStandEntity;kill()V", ordinal = 2, shift = At.Shift.AFTER), cancellable = true)
    private void antishadowpatch$bringBackWitherInvulnerableArmorStands3(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(AntiShadowPatch.config().Entities.BringBackArmorStandInvulnerableToWitherDamage)
            cir.setReturnValue(source.getSource() instanceof PersistentProjectileEntity projectile && projectile.getPierceLevel() > 0);
    }
}

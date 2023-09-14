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
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(ArmorStandEntity.class)
public class ArmorStandEntityMixin {
    @WrapOperation(
            method = "damage",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSource;isIn(Lnet/minecraft/registry/tag/TagKey;)Z", ordinal = 4)
    )
    private boolean antishadowpatch_bringBackWitherInvulnerableArmorStands(DamageSource source, TagKey<DamageType> tag, Operation<Boolean> original) {
        if (AntiShadowPatch.config.misc.bringBackWitherInvulnerableArmorStands) {
            return source.getSource() instanceof PersistentProjectileEntity && ((PersistentProjectileEntity)source.getSource()).getPierceLevel() > 0;
        } else {
            return original.call(source, tag);
        }
    }
}

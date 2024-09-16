package ru.nern.antishadowpatch.mixin.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(EnderDragonEntity.class)
public class EnderDragonEntityMixin {
    @Shadow private @Nullable EnderDragonFight fight;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void antishadowpatch$bringBackOldDragonFreezing(EntityType<? extends EnderDragonEntity> entityType, World world, CallbackInfo ci) {
        this.fight = world instanceof ServerWorld ? ((ServerWorld) world).getEnderDragonFight() : null;
    }

}

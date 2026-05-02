package ru.nern.antishadowpatch.mixin.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.end.EnderDragonFight;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(EnderDragon.class)
public class EnderDragonMixin {
    @Shadow private @Nullable EnderDragonFight dragonFight;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void antishadowpatch$bringBackOldDragonFreezing(EntityType<? extends EnderDragon> entityType, Level level, CallbackInfo ci) {
        this.dragonFight = level instanceof ServerLevel ? ((ServerLevel) level).getDragonFight() : null;
    }

}

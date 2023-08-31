package ru.nern.antishadowpatch.mixin.dragon;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
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
import ru.nern.antishadowpatch.AntiShadowPatch;

@Restriction(
        require = {
                @Condition(value = "minecraft", versionPredicates = {">=1.20-pre1", ">=1.20"}),
        }
)
@Mixin(EnderDragonEntity.class)
public class EnderDragonEntityMixin {

    @Shadow private @Nullable EnderDragonFight fight;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void antishadowpatch_bringBackOldDragonFreezing(EntityType<? extends EnderDragonEntity> entityType, World world, CallbackInfo ci) {
        if(AntiShadowPatch.config.bringBackOldDragonFreezing) this.fight = world instanceof ServerWorld ? ((ServerWorld)world).getEnderDragonFight() : null;
    }

}

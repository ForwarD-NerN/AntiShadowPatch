package ru.nern.antishadowpatch.mixin.entities;

import net.minecraft.block.EndPortalBlock;
import net.minecraft.block.Portal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow protected abstract void tickPortalTeleportation();

    /*
     * Before(24w21a), the entity was teleported through an end portal the moment it collided with it.
     * In 24w21a it was made consistent with the nether portal and so now the teleportation happens inside the entity tick phase.
     * However, due to EnderDragonParts not being able to tick, it is no longer possible for them to be transported through an end portal.
     * Here we just call tickPortalTeleportation() on them instantly, thus reintroducing the old behavior.
     */
    @Inject(method = "tryUsePortal", at = @At("TAIL"))
    private void antishadowpatch$bringBackEndPortalEnderDragonDupe(Portal portal, BlockPos pos, CallbackInfo ci)
    {
        if(portal instanceof EndPortalBlock && ((Entity) (Object) this) instanceof EnderDragonPart)
        {
            this.tickPortalTeleportation();
        }
    }
}

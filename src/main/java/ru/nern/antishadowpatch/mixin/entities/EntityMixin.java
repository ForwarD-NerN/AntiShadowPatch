package ru.nern.antishadowpatch.mixin.entities;

import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragonPart;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow protected abstract void handlePortal();

    /*
     * Before(24w21a), the entity was teleported through an end portal the moment it collided with it.
     * In 24w21a it was made consistent with the nether portal and so now the teleportation happens inside the entity tick phase.
     * However, due to EnderDragonParts not being able to tick, it is no longer possible for them to be transported through an end portal.
     * Here we just call tickPortalTeleportation() on them instantly, thus reintroducing the old behavior.
     */
    @Inject(method = "setAsInsidePortal", at = @At("TAIL"))
    private void antishadowpatch$bringBackEndPortalEnderDragonDupe(Portal portal, BlockPos pos, CallbackInfo ci)
    {
        if(portal instanceof EndPortalBlock && ((Entity) (Object) this) instanceof EnderDragonPart)
        {
            this.handlePortal();
        }
    }
}

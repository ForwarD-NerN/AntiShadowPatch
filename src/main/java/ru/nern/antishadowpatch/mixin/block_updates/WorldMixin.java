package ru.nern.antishadowpatch.mixin.block_updates;

import net.minecraft.world.World;
import net.minecraft.world.block.ChainRestrictedNeighborUpdater;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.nern.antishadowpatch.utils.ChainRestrictedNeighborUpdaterWrapper;

@Mixin(World.class)
public class WorldMixin {

    @Mutable
    @Shadow @Final protected ChainRestrictedNeighborUpdater neighborUpdater;

    //Just replaces the default neighbor updater with the simple one(for some reason Mojang left the pre 1.19 neighbor updater in the game)
    @Inject(method = "<init>", at = @At("TAIL"))
    private void antishadowpatch$bringBackStackOverflowSuppression(CallbackInfo ci) {
        this.neighborUpdater = new ChainRestrictedNeighborUpdaterWrapper((World) (Object) this);
    }


}
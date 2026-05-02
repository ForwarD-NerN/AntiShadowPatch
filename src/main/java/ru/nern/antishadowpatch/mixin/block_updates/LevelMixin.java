package ru.nern.antishadowpatch.mixin.block_updates;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.redstone.CollectingNeighborUpdater;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.nern.antishadowpatch.utils.CollectingNeighborUpdaterWrapper;

@Mixin(Level.class)
public class LevelMixin {

    @Mutable
    @Shadow @Final protected CollectingNeighborUpdater neighborUpdater;

    //Just replaces the default neighbor updater with the simple one(for some reason Mojang left the pre 1.19 neighbor updater in the game)
    @Inject(method = "<init>", at = @At("TAIL"))
    private void antishadowpatch$bringBackStackOverflowSuppression(CallbackInfo ci) {
        this.neighborUpdater = new CollectingNeighborUpdaterWrapper((Level) (Object) this);
    }


}
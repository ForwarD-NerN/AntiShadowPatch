package ru.nern.antishadowpatch.mixin.block_updates;

import net.minecraft.world.World;
import net.minecraft.world.block.NeighborUpdater;
import net.minecraft.world.block.SimpleNeighborUpdater;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(value = World.class)
public class WorldMixin {


    @Mutable
    @Shadow
    @Final
    protected NeighborUpdater neighborUpdater;

    //Just replaces the default neighbor updater with the simple one(for some reason Mojang left the pre 1.19 neighbor updater in the game)
    @Inject(method = "<init>(Lnet/minecraft/world/MutableWorldProperties;Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/registry/DynamicRegistryManager;Lnet/minecraft/registry/entry/RegistryEntry;Ljava/util/function/Supplier;ZZJI)V", at = @At("TAIL"))
    private void antishadowpatch_bringBackStackOverflowSuppression(CallbackInfo ci) {
        if(AntiShadowPatch.config.blocks.bringBackStackOverflowSuppression)
        {
            this.neighborUpdater = new SimpleNeighborUpdater((World) (Object) this);
        }
    }


}
package ru.nern.antishadowpatch.mixin.block_updates;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.block.NeighborUpdater;
import net.minecraft.world.block.SimpleNeighborUpdater;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.nern.antishadowpatch.AntiShadowPatch;

import java.util.function.Supplier;

@Restriction(
        require = {
                @Condition(value = "minecraft", versionPredicates = ">=1.19"),
        }
)
@Mixin(World.class)
public class WorldMixin {
    @Mutable
    @Shadow @Final protected NeighborUpdater neighborUpdater;

    //Just replaces the default neighbor updater with the simple one(for some reason Mojang left the pre 1.19 neighbor updater in the game)
    @Inject(method = "<init>", at = @At("TAIL"))
    private void antishadowpatch_bringBackStackOverflowSuppression(MutableWorldProperties properties, RegistryKey<World> registryRef, DynamicRegistryManager registryManager, RegistryEntry<DimensionType> dimensionEntry, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long biomeAccess, int maxChainedNeighborUpdates, CallbackInfo ci) {
        if(AntiShadowPatch.config.bringBackStackOverflowSuppression)
        {
            this.neighborUpdater = new SimpleNeighborUpdater((World) (Object) this);
        }
    }
}

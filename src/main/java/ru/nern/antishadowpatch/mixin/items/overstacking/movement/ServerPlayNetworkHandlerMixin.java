package ru.nern.antishadowpatch.mixin.items.overstacking.movement;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

    // Prevents item stacks above max count from disappearing in creative mode in some cases
    @Redirect(method = "onCreativeInventoryAction", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxCount()I", ordinal = 0))
    private int antishadowpatch$bringBackShiftClickOverstackedItemMovement2(ItemStack stack) {
        return 64;
    }
}

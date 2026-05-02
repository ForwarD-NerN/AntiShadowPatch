package ru.nern.antishadowpatch.mixin.items.overstacking.movement;

import net.minecraft.world.item.ItemStack;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin {

    // Prevents item stacks above max count from disappearing in creative mode in some cases
    @Redirect(method = "handleSetCreativeModeSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getMaxStackSize()I", ordinal = 0))
    private int antishadowpatch$bringBackShiftClickOverstackedItemMovement2(ItemStack stack) {
        return 64;
    }
}

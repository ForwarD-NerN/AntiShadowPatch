package ru.nern.antishadowpatch.mixin.items.overstacking.movement;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(AbstractContainerMenu.class)
public abstract class AbstractContainerMenuMixin {

    @Redirect(method = "moveItemStackTo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;getMaxStackSize(Lnet/minecraft/world/item/ItemStack;)I", ordinal = 0))
    private int antishadowpatch$bringBackShiftClickOverstackedItemMovement(Slot instance, ItemStack itemStack, @Local(argsOnly = true) ItemStack stack) {
        return stack.getMaxStackSize();
    }

    @Redirect(method = "moveItemStackTo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;getMaxStackSize(Lnet/minecraft/world/item/ItemStack;)I", ordinal = 1))
    private int antishadowpatch$bringBackShiftClickOverstackedItemMovement2(Slot slot, ItemStack itemStack) {
        return slot.getMaxStackSize();
    }
}

package ru.nern.antishadowpatch.mixin.items.overstacking.movement;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin {

    @Redirect(method = "insertItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;getMaxItemCount(Lnet/minecraft/item/ItemStack;)I", ordinal = 0))
    private int antishadowpatch$bringBackShiftClickOverstackedItemMovement(Slot instance, ItemStack itemStack, @Local(argsOnly = true) ItemStack stack) {
        return stack.getMaxCount();
    }

    @Redirect(method = "insertItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;getMaxItemCount(Lnet/minecraft/item/ItemStack;)I", ordinal = 1))
    private int antishadowpatch$bringBackShiftClickOverstackedItemMovement2(Slot slot, ItemStack itemStack) {
        return slot.getMaxItemCount();
    }
}

package ru.nern.antishadowpatch.mixin.items.overstacking;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.GrindstoneMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GrindstoneMenu.class)
public abstract class GrindstoneMenuMixin {

    @Redirect(method = "mergeItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getMaxStackSize()I", ordinal = 0))
    private int antishadowpatch$bringBackCurseBookOverstacking(ItemStack stack) {
        return 3; // Makes the condition(firstInput.getMaxCount() < 2) always fail
    }
}

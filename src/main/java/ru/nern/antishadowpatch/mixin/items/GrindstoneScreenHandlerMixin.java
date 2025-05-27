package ru.nern.antishadowpatch.mixin.items;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.screen.GrindstoneScreenHandler;
import org.spongepowered.asm.mixin.*;

@Mixin(GrindstoneScreenHandler.class)
public abstract class GrindstoneScreenHandlerMixin {

    @Redirect(method = "combineItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxCount()I", ordinal = 0))
    private int antishadowpatch$bringBackCurseBookOverstacking(ItemStack stack) {
        return 3; // Makes the condition(firstInput.getMaxCount() < 2) always fail
    }
}

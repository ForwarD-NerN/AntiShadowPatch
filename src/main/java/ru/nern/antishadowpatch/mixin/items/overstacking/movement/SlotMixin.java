package ru.nern.antishadowpatch.mixin.items.overstacking.movement;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Slot.class)
public abstract class SlotMixin {

    /*
     * It was so difficult to bypass if(i <= 0) {} here
     * There might be a better way, but I'm not a bytecode wizard
     * In short, this is needed to make overstacked items able to be overstacked even more by clicking on them with another overstacked item in the cursor slot
     */
    @WrapOperation(
            method = "insertStack(Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/item/ItemStack;",
            at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I", ordinal = 1)
    )
    private int antishadowpatch$trickConditionIntoFailing(int a, int b, Operation<Integer> original) {
        return original.call(a, b) + 1028;
    }

    @Inject(method = "insertStack(Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD)
    private void antishadowpatch$revertToTheOldValue(ItemStack stack, int count, CallbackInfoReturnable<ItemStack> cir, @Local(ordinal = 1) LocalIntRef i) {
        i.set(i.get() - 1028);
    }


}

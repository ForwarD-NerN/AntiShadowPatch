package ru.nern.antishadowpatch.mixin.item;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Shadow @Final public static ItemStack EMPTY;

    @Shadow @Final @Deprecated @Nullable private Item item;

    @Shadow private int count;

    //I don't think it's possible to bring 0-stacked items as it introduces a lot of bugs. Maybe I'll look into it in the future.
    @Inject(method = "isEmpty", at = @At("HEAD"), cancellable = true)
    private void antishadowpatch$bringBackUnderstackedItems(CallbackInfoReturnable<Boolean> cir) {
        if(AntiShadowPatch.config.items.bringBackUnderstackedItems) {
            cir.setReturnValue((Object) this == EMPTY || this.item == Items.AIR || this.count == 0);
        }
    }

    @WrapOperation(
            method = "split",
            at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I")
    )
    private int antishadowpatch$reintroduceUnderstackedItemsBehavior(int a, int b, Operation<Integer> original) {
        if (b < 0 && AntiShadowPatch.config.items.bringBackUnderstackedItemsBehavior) {
            return a;
        } else {
            return original.call(a, b);
        }
    }
}

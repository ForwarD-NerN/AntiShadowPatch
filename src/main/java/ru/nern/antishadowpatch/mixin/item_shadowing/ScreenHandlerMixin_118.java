package ru.nern.antishadowpatch.mixin.item_shadowing;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.nern.antishadowpatch.AntiShadowPatch;


@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin_118 {

    @Shadow @Final public DefaultedList<Slot> slots;

    @Shadow public abstract ItemStack getCursorStack();

    //Brings back 1.18 item shadowing
    @WrapWithCondition(
            method = "internalOnSlotClick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;setStack(Lnet/minecraft/item/ItemStack;)V", ordinal = 1)
    )
    private boolean antishadowpatch_cancel118ItemShadowingPatch(Slot slot, ItemStack stack)
    {
        return !AntiShadowPatch.config.items.bringBack1_18ItemShadowing;
    }

    @Inject(method = "internalOnSlotClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/ScreenHandler;setCursorStack(Lnet/minecraft/item/ItemStack;)V", ordinal = 4))
    private void antishadowpatch_bringBack118ItemShadowing(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        if(AntiShadowPatch.config.items.bringBack1_18ItemShadowing)
        {
            slots.get(slotIndex).setStack(getCursorStack());
        }
    }
}

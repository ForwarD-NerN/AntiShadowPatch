package ru.nern.antishadowpatch.mixin.item;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
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
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.nern.antishadowpatch.AntiShadowPatch;


@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin {

    @Shadow @Final public DefaultedList<Slot> slots;

    @Shadow public abstract ItemStack getCursorStack();

    //Brings back 1.17 item shadowing
    @WrapWithCondition(
            method = "internalOnSlotClick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;setStack(ILnet/minecraft/item/ItemStack;)V", ordinal = 1)
    )
    private boolean antishadowpatch$cancel117ItemShadowingPatch(PlayerInventory inventory, int button, ItemStack stack)
    {
        return !AntiShadowPatch.config.items.bringBack1_17ItemShadowing;
    }

    @Inject(method = "internalOnSlotClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;setStack(Lnet/minecraft/item/ItemStack;)V", ordinal = 4, shift = At.Shift.AFTER))
    private void antishadowpatch$bringBack117ItemShadowing(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        if(AntiShadowPatch.config.items.bringBack1_17ItemShadowing)
        {
            player.getInventory().setStack(button, ItemStack.EMPTY);
        }
    }

    //Brings back 1.18 item shadowing
    @WrapWithCondition(
            method = "internalOnSlotClick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;setStack(Lnet/minecraft/item/ItemStack;)V", ordinal = 1)
    )
    private boolean antishadowpatch$cancel118ItemShadowingPatch(Slot slot, ItemStack stack)
    {
        return !AntiShadowPatch.config.items.bringBack1_18ItemShadowing;
    }

    @Inject(method = "internalOnSlotClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/ScreenHandler;setCursorStack(Lnet/minecraft/item/ItemStack;)V", ordinal = 4))
    private void antishadowpatch$bringBack118ItemShadowing(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        if(AntiShadowPatch.config.items.bringBack1_18ItemShadowing)
        {
            slots.get(slotIndex).setStack(getCursorStack());
        }
    }
}

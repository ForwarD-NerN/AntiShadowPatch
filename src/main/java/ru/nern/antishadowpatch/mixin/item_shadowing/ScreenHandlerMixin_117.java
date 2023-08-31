package ru.nern.antishadowpatch.mixin.item_shadowing;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Restriction(
        require = {
                @Condition(value = "minecraft", versionPredicates = ">=1.18"),
        }
)
@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin_117 {

    //Brings back 1.17 item shadowing
    @WrapWithCondition(
            method = "internalOnSlotClick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;setStack(ILnet/minecraft/item/ItemStack;)V", ordinal = 1)
    )
    private boolean antishadowpatch_cancel117ItemShadowingPatch(PlayerInventory inventory, int button, ItemStack stack)
    {
        return !AntiShadowPatch.config.bringBack1_17ItemShadowing;
    }

    @Inject(method = "internalOnSlotClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;setStack(Lnet/minecraft/item/ItemStack;)V", ordinal = 4, shift = At.Shift.AFTER))
    private void antishadowpatch_bringBack117ItemShadowing(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        if(AntiShadowPatch.config.bringBack1_17ItemShadowing)
        {
            player.getInventory().setStack(button, ItemStack.EMPTY);
        }
    }
}

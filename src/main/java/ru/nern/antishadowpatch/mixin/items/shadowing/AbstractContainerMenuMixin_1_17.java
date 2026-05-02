package ru.nern.antishadowpatch.mixin.items.shadowing;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//Brings back 1.17 item shadowing
@Mixin(AbstractContainerMenu.class)
public abstract class AbstractContainerMenuMixin_1_17 {

    @Redirect(
            method = "doClick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;setItem(ILnet/minecraft/world/item/ItemStack;)V", ordinal = 1)
    )
    private void antishadowpatch$cancel117ItemShadowingPatch(Inventory inventory, int button, ItemStack stack) {}

    @Inject(method = "doClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;setByPlayer(Lnet/minecraft/world/item/ItemStack;)V", ordinal = 4, shift = At.Shift.AFTER))
    private void antishadowpatch$bringBack117ItemShadowing(int slotIndex, int buttonNum, ContainerInput containerInput, Player player, CallbackInfo ci) {
        player.getInventory().setItem(buttonNum, ItemStack.EMPTY);
    }


}

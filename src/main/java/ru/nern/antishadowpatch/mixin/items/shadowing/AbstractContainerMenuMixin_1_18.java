package ru.nern.antishadowpatch.mixin.items.shadowing;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.core.NonNullList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//Brings back 1.18 item shadowing
@Mixin(AbstractContainerMenu.class)
public abstract class AbstractContainerMenuMixin_1_18 {
    @Shadow
    @Final
    public NonNullList<Slot> slots;

    @Shadow public abstract ItemStack getCarried();

    @Redirect(
            method = "doClick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;setByPlayer(Lnet/minecraft/world/item/ItemStack;)V", ordinal = 1)
    )
    private void antishadowpatch$cancel118ItemShadowingPatch(Slot slot, ItemStack stack) {}

    @Inject(method = "doClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/AbstractContainerMenu;setCarried(Lnet/minecraft/world/item/ItemStack;)V", ordinal = 4))
    private void antishadowpatch$bringBack118ItemShadowing(int slotIndex, int buttonNum, ContainerInput containerInput, Player player, CallbackInfo ci) {
        slots.get(slotIndex).set(getCarried());
    }
}

package ru.nern.antishadowpatch.mixin.items.overstacking.movement;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements DataComponentHolder, ItemInstance {

    @Shadow public abstract String toString();

    @Shadow public abstract boolean isEmpty();

    // How dare you Mojang
    @Inject(method = "limitSize", at = @At("HEAD"), cancellable = true)
    private void antishadowpatch$doNotCapCount(int maxCount, CallbackInfo ci) {
        ci.cancel();
    }

    /*
     * This brings back an oversight that Mojang had in the old code.
     * When a stack is empty, getItem() had always returned Items.AIR, which means that this method was returning 64.
     * In PlayerInventory.addStack(slot, stack), the slot was initially prepopulated with the item that was being added, and the count of that stack was set to 0.
     * So if an overstacked item was picked up, instead of splitting into multiple slots, it would think that its max count is 64 and would go into a single slot.
     * I don't know why they coded it like this, but whatever.
     * Also, it was the only place that used copyWithCount(0)
     */

    @Override
    public int getMaxStackSize() {
        return isEmpty() ? Items.AIR.getDefaultMaxStackSize() : ItemInstance.super.getMaxStackSize();
    }
}

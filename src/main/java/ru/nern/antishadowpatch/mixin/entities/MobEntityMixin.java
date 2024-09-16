package ru.nern.antishadowpatch.mixin.entities;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @ModifyArg(method = "loot", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/MobEntity;tryEquip(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"), index = 0)
    private ItemStack antishadowpatch$bringBackShadowItemsInMobInventory(ItemStack copiedStack, @Local(argsOnly = true) ItemEntity itemEntity) {
        return itemEntity.getStack();
    }

    @ModifyArg(method = "tryEquip", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/MobEntity;equipLootStack(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;)V"), index = 1)
    private ItemStack antishadowpatch$bringBackShadowItemsInMobInventory(ItemStack stack, @Local(argsOnly = true) ItemStack origStack) {
        return origStack;
    }

    @Redirect(method = "loot", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
    private void antishadowpatch$disableDecrementAndDiscard(ItemStack instance, int amount, @Local(argsOnly = true) ItemEntity item) {
        item.discard();
    }

    @Redirect(method = "tryEquip", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EquipmentSlot;split(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack antishadowpatch$disableSplit(EquipmentSlot slot, ItemStack stack) {
        return stack;
    }
}

package ru.nern.antishadowpatch.mixin.entities;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Mob.class)
public class MobMixin {
    @ModifyArg(method = "pickUpItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;equipItemIfPossible(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;"), index = 1)
    private ItemStack antishadowpatch$bringBackShadowItemsInMobInventory(ServerLevel world, ItemStack copiedStack, @Local(argsOnly = true) ItemEntity itemEntity) {
        return itemEntity.getItem();
    }

    @ModifyArg(method = "equipItemIfPossible", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;setItemSlotAndDropWhenKilled(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"), index = 1)
    private ItemStack antishadowpatch$bringBackShadowItemsInMobInventory(ItemStack stack, @Local(argsOnly = true) ItemStack origStack) {
        return origStack;
    }

    @Redirect(method = "pickUpItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    private void antishadowpatch$disableDecrementAndDiscard(ItemStack instance, int amount, @Local(argsOnly = true) ItemEntity item) {
        item.discard();
    }

    @Redirect(method = "equipItemIfPossible", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/EquipmentSlot;limit(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack antishadowpatch$disableSplit(EquipmentSlot slot, ItemStack stack) {
        return stack;
    }
}

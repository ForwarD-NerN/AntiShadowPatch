package ru.nern.antishadowpatch.mixin.item_shadowing;

import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin {

    @Shadow @Final public DefaultedList<Slot> slots;

    @Shadow public abstract ItemStack getCursorStack();


}

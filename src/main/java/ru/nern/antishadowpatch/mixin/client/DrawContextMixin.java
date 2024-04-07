package ru.nern.antishadowpatch.mixin.client;

import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(DrawContext.class)
public class DrawContextMixin {

    //Not sure if the color is correct, got it from Google
    @ModifyArgs(method = "drawItemInSlot(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I"))
    private void antishadowpatch$changeItemCountColor(Args args) {
        if(AntiShadowPatch.config.items.bringBackUnderstackedItems && ((String)args.get(1)).startsWith("-")) {
            args.set(4, 0xFF5555);
        }
    }
}

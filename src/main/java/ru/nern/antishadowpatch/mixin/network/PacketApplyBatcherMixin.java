package ru.nern.antishadowpatch.mixin.network;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(targets = "net.minecraft.network.PacketApplyBatcher$Entry")
public class PacketApplyBatcherMixin {

    @WrapOperation(
            method = "apply",
            constant = @Constant(classValue = OutOfMemoryError.class))
    private static boolean antishadowpatch$bringBackGracefulOOMHandling(Object obj, Operation<Boolean> original) {
        return false;
    }
}

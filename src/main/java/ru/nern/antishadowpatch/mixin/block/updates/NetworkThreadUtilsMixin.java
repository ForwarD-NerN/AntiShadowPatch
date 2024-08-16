package ru.nern.antishadowpatch.mixin.block.updates;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.network.NetworkThreadUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(NetworkThreadUtils.class)
public class NetworkThreadUtilsMixin {


    @WrapOperation(
            method = "method_11072(Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/network/packet/Packet;)V",
            constant = @Constant(classValue = OutOfMemoryError.class))
    private static boolean antishadowpatch$bringBackGracefulOOMHandling(Object obj, Operation<Boolean> original) {
        return !AntiShadowPatch.config().Misc.BringBackGracefulOOMHandling && original.call(obj);
    }


}

package ru.nern.antishadowpatch.mixin.block.updates;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.OffThreadException;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.thread.ThreadExecutor;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Redirect;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(NetworkThreadUtils.class)
public class NetworkThreadUtilsMixin {
    @WrapOperation(
            method = "method_11072(Lnet/minecraft/network/listener/PacketListener;Lnet/minecraft/network/packet/Packet;)V",
            constant = @Constant(classValue = OutOfMemoryError.class))
    private static boolean antishadowpatch_bringBackGracefulOOMHandling(Object obj, Operation<Boolean> original) {

        return !AntiShadowPatch.config.blocks.bringBackGracefulOOMHandling && original.call(obj);
    }
}

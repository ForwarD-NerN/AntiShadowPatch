package ru.nern.antishadowpatch.mixin.block.updates;

import net.minecraft.util.SystemDetails;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(SystemDetails.class)
public class SystemDetailsMixin {

    //Unfortunately, it's impossible to fully revert stack overflow handling to pre 1.18(as described in MC-248200)
    //So we just prevent further error handling in the logger by removing the throwable from .warn() method, it shouldn't change the behaviour(at least I hope so).
    @ModifyArg(method = "tryAddGroup", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"),
            index = 2)
    private Object antishadowpatch$gracefulStackOverflowHandling(Object throwable) {
        return AntiShadowPatch.config().Misc.bringBackGracefulSOHandling ? null : throwable;
    }

    @ModifyArg(method = "addSection(Ljava/lang/String;Ljava/util/function/Supplier;)V", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"),
            index = 2)
    private Object antishadowpatch$gracefulStackOverflowHandling2(Object throwable) {
        return AntiShadowPatch.config().Misc.bringBackGracefulSOHandling ? null : throwable;
    }



}
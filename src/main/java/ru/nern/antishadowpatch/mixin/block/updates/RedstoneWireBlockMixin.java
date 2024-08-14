package ru.nern.antishadowpatch.mixin.block.updates;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.TrapdoorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin {

    @WrapOperation(
            method = "getRenderConnectionType(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;Z)Lnet/minecraft/block/enums/WireConnection;",
            constant = @Constant(classValue = TrapdoorBlock.class))
    private boolean antishadowpatch$bringBackTrapdoorUpdateSkipping(Object obj, Operation<Boolean> original) {
        return !AntiShadowPatch.config().Block_Updates.bringBackTrapdoorUpdateSkipping && original.call(obj);
    }
}

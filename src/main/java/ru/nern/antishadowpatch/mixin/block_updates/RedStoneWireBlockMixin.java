package ru.nern.antishadowpatch.mixin.block_updates;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(RedStoneWireBlock.class)
public class RedStoneWireBlockMixin {

    @WrapOperation(
            method = "getConnectingSide(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Z)Lnet/minecraft/world/level/block/state/properties/RedstoneSide;",
            constant = @Constant(classValue = TrapDoorBlock.class))
    private boolean antishadowpatch$bringBackTrapdoorUpdateSkipping(Object obj, Operation<Boolean> original) {
        return false;
    }
}

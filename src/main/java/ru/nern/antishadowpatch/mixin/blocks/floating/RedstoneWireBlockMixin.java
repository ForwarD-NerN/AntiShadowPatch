package ru.nern.antishadowpatch.mixin.blocks.floating;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//Brings back floating redstone dust
@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin {
    @ModifyReturnValue(
            method = "getStateForNeighborUpdate",
            at = @At(value = "RETURN", ordinal = 0)
    )
    private BlockState antishadowpatch$bringBackFloatingRedstoneOnTrapdoor(BlockState state, @Local(ordinal = 0, argsOnly = true) BlockState original) {
        return original;
    }
}

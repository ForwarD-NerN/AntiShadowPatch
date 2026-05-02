package ru.nern.antishadowpatch.mixin.blocks.floating;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.RedStoneWireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//Brings back floating redstone dust
@Mixin(RedStoneWireBlock.class)
public class RedStoneWireBlockMixin {
    @ModifyReturnValue(
            method = "updateShape",
            at = @At(value = "RETURN", ordinal = 0)
    )
    private BlockState antishadowpatch$bringBackFloatingRedstoneOnTrapdoor(BlockState state, @Local(ordinal = 0, argsOnly = true) BlockState original) {
        return original;
    }
}

package ru.nern.antishadowpatch.mixin.block.floating;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import ru.nern.antishadowpatch.AntiShadowPatch;

//Brings back floating redstone dust
@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin
{

    @ModifyReturnValue(
            method = "getStateForNeighborUpdate",
            at = @At(value = "RETURN", ordinal = 0)
    )
    private BlockState antishadowpatch$bringBackFloatingRedstoneOnTrapdoor(BlockState original, BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return AntiShadowPatch.config.blocks.bringBackFloatingRedstoneComponentsOnTopOfTrapdoor ? state : original;
    }
}

package ru.nern.antishadowpatch.mixin.block.floating;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComparatorBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import ru.nern.antishadowpatch.AntiShadowPatch;


@Mixin(ComparatorBlock.class)
public class ComparatorBlockMixin
{

    //Brings back floating comparator
    @ModifyReturnValue(
            method = "getStateForNeighborUpdate",
            at = @At(value = "RETURN", ordinal = 0)
    )
    private BlockState antishadowpatch$bringBackFloatingComparatorOnTrapdoor(BlockState original, BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return AntiShadowPatch.config().Blocks.BringBackFloatingRedstoneComponentsOnTopOfTrapdoor ? state : original;
    }

}


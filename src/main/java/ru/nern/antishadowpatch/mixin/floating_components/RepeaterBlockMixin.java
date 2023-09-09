package ru.nern.antishadowpatch.mixin.floating_components;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.BlockState;
import net.minecraft.block.RepeaterBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(RepeaterBlock.class)
public class RepeaterBlockMixin {
    //Brings back floating repeater
    @ModifyReturnValue(
            method = "getStateForNeighborUpdate",
            at = @At(value = "RETURN", ordinal = 0)
    )
    private BlockState antishadowpatch_bringBackFloatingRepeaterOnTrapdoor(BlockState original, BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return AntiShadowPatch.config.blocks.bringBackFloatingRedstoneComponentsOnTopOfTrapdoor ? state : original;
    }
}

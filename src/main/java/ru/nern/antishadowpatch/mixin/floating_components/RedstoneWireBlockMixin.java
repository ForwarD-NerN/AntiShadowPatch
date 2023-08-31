package ru.nern.antishadowpatch.mixin.floating_components;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import ru.nern.antishadowpatch.AntiShadowPatch;

//Brings back floating redstone dust
@Restriction(
        require = {
                @Condition(value = "minecraft", versionPredicates = {">=23w35a", ">=1.20.2"}),
        }
)
@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin
{

    @ModifyReturnValue(
            method = "getStateForNeighborUpdate",
            at = @At(value = "RETURN", ordinal = 0)
    )
    private BlockState antishadowpatch_bringBackFloatingRedstoneOnTrapdoor(BlockState original, BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return AntiShadowPatch.config.bringBackFloatingRedstoneComponentsOnTopOfTrapdoor ? state : original;
    }
}

package ru.nern.antishadowpatch.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Restriction(
        require = {
                @Condition(value = "minecraft", versionPredicates = {">=23w35a", ">=1.20.2"}),
        }
)
@Mixin(AbstractFurnaceBlock.class)
public class AbstractFurnaceBlockMixin {


    @WrapWithCondition(
            method = "onStateReplaced",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockWithEntity;onStateReplaced(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)V", ordinal = 0)
    )
    private boolean antishadowpatch_bringBackFurnaceXPDupe(BlockWithEntity block, BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        return !AntiShadowPatch.config.bringBackInfiniteFurnaceXPBug;
    }
}

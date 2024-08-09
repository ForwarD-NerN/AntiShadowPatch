package ru.nern.antishadowpatch.mixin.block.entity;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChiseledBookshelfBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(ChiseledBookshelfBlock.class)
public class ChiseledBookShelfBlockMixin {

    @WrapWithCondition(
            method = "onStateReplaced",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;updateComparators(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;)V")
    )
    private boolean antishadowpatch$disableComparatorUpdateAfter(World instance, BlockPos pos, Block block) {
        return !AntiShadowPatch.config.blocks.bringBackBlockEntitySwap;
    }

    @Inject(method = "onStateReplaced", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/ChiseledBookshelfBlockEntity;clear()V", shift = At.Shift.AFTER))
    private void antishadowpatch$moveComparatorUpdateBefore(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci) {
        if(AntiShadowPatch.config.blocks.bringBackBlockEntitySwap) {
            world.updateComparators(pos, (Block) (Object) this);
        }
    }
}

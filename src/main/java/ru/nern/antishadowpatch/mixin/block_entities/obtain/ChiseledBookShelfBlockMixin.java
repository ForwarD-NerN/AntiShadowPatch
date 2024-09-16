package ru.nern.antishadowpatch.mixin.block_entities.obtain;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChiseledBookshelfBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChiseledBookshelfBlock.class)
public class ChiseledBookShelfBlockMixin {

    @Redirect(
            method = "onStateReplaced",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;updateComparators(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;)V")
    )
    private void antishadowpatch$disableComparatorUpdateAfter(World instance, BlockPos pos, Block block) {}

    @Inject(method = "onStateReplaced", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/ChiseledBookshelfBlockEntity;clear()V", shift = At.Shift.AFTER))
    private void antishadowpatch$moveComparatorUpdateBefore(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci) {
        world.updateComparators(pos, (Block) (Object) this);
    }
}

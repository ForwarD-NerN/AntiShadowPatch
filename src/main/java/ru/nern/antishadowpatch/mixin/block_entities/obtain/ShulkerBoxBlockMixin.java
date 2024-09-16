package ru.nern.antishadowpatch.mixin.block_entities.obtain;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ShulkerBoxBlock.class)
public abstract class ShulkerBoxBlockMixin extends BlockWithEntity {
    protected ShulkerBoxBlockMixin(Settings settings) {
        super(settings);
    }

    @Redirect(
            method = "onStateReplaced",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;updateComparators(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;)V")
    )
    private void antishadowpatch$disableComparatorUpdateAfter(World world, BlockPos pos, Block block) {}

    @Inject(method = "onStateReplaced", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockWithEntity;onStateReplaced(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void antishadowpatch$moveComparatorUpdateBefore(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci, BlockEntity blockEntity) {
        if(blockEntity instanceof ShulkerBoxBlockEntity) {
            world.updateComparators(pos, state.getBlock());
        }
    }
}

package ru.nern.antishadowpatch.mixin.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class FullBlockCollisionsMixin {

    @Mixin(NetherPortalBlock.class)
    public static class NetherPortalBlockMixin {
        @Redirect(method = "getInsideCollisionShape", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getOutlineShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/shape/VoxelShape;"))
        private VoxelShape antishadowpatch$bringBackFullBlockCollision(BlockState state, BlockView blockView, BlockPos blockPos) {
            return VoxelShapes.fullCube();
        }
    }

    @Mixin(EndPortalBlock.class)
    public static class EndPortalBlockMixin {
        @Redirect(method = "getInsideCollisionShape", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getOutlineShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/shape/VoxelShape;"))
        private VoxelShape antishadowpatch$bringBackFullBlockCollision(BlockState state, BlockView blockView, BlockPos blockPos) {
            return VoxelShapes.fullCube();
        }
    }

    @Mixin(PowderSnowBlock.class)
    public static class PowderSnowBlockMixin {
        @Inject(method = "getInsideCollisionShape", at = @At("HEAD"), cancellable = true)
        private void antishadowpatch$bringBackFullBlockCollision(BlockState state, BlockView world, BlockPos pos, Entity entity, CallbackInfoReturnable<VoxelShape> cir) {
            cir.setReturnValue(VoxelShapes.fullCube());
        }
    }
}

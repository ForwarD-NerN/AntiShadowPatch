package ru.nern.antishadowpatch.mixin.block_entities.obtain;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LecternBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LecternBlock.class)
public class LecternBlockMixin {
    @Shadow @Final
    public static BooleanProperty POWERED;

    @Redirect(
            method = "onStateReplaced",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/LecternBlock;updateNeighborAlways(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V")
    )
    private void antishadowpatch$disableComparatorUpdateAfter(World world, BlockPos pos, BlockState state) {}

    @Inject(method = "onStateReplaced", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockWithEntity;onStateReplaced(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)V"))
    private void antishadowpatch$moveComparatorUpdateBefore(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci) {
        if(state.get(POWERED)) {
            world.updateNeighborsAlways(pos.down(), (Block) (Object) this);
        }
    }
}

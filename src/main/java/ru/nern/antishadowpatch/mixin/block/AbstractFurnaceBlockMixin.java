package ru.nern.antishadowpatch.mixin.block;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.nern.antishadowpatch.AntiShadowPatch;


@Mixin(AbstractFurnaceBlock.class)
public abstract class AbstractFurnaceBlockMixin extends BlockWithEntity {


    protected AbstractFurnaceBlockMixin(Settings settings) {
        super(settings);
    }

    @WrapWithCondition(
            method = "onStateReplaced",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockWithEntity;onStateReplaced(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)V")
    )
    private boolean antishadowpatch$bringBackFurnaceXPDupe(BlockWithEntity block, BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        return !AntiShadowPatch.config().Blocks.BringBackFurnaceXPDupe;
    }

    @Inject(method = "onStateReplaced", at = @At("TAIL"))
    private void antishadowpatch$moveStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci) {
        if(AntiShadowPatch.config().Blocks.BringBackFurnaceXPDupe) {
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }
}

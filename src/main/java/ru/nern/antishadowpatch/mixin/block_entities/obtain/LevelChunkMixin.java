package ru.nern.antishadowpatch.mixin.block_entities.obtain;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.levelgen.blending.BlendingData;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(LevelChunk.class)
public abstract class LevelChunkMixin extends ChunkAccess {


    public LevelChunkMixin(ChunkPos chunkPos, UpgradeData upgradeData, LevelHeightAccessor levelHeightAccessor, PalettedContainerFactory containerFactory, long inhabitedTime, LevelChunkSection @Nullable [] sections, @Nullable BlendingData blendingData) {
        super(chunkPos, upgradeData, levelHeightAccessor, containerFactory, inhabitedTime, sections, blendingData);
    }


    /*
     * In 25w02a, Mojang moved all block-entity-related code for onStateReplaced to a separate method - BlockEntity.onBlockReplaced
     * Because of that, many blocks (if BringBackBlockEntitySwap is enabled) can now contain unsupported block entities.
     * To prevent things like AbstractFurnaceBlockEntity from dropping xp in onBlockReplaced, we first check if the block entity supports the block (so that shulker boxes, for example, wouldn't be able to drop xp from a furnace block entity)
     */
    @WrapWithCondition(
            method = "setBlockState",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BlockEntity;preRemoveSideEffects(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V", ordinal = 0)
    )
    private boolean antishadowpatch$preventUnwantedSideEffects(BlockEntity blockEntity, BlockPos pos, BlockState oldState) {
        return blockEntity.getType().isValid(oldState);
    }


    @WrapWithCondition(
            method = "setBlockState",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/chunk/LevelChunk;removeBlockEntity(Lnet/minecraft/core/BlockPos;)V", ordinal = 0)
    )
    private boolean antishadowpatch$preventRemoveBlockEntity(LevelChunk instance, BlockPos pos, @Local(ordinal = 1) BlockState oldState) {
        return !AntiShadowPatch.config().Blocks.BringBackFurnaceXPDupe && oldState.is(Blocks.FURNACE);
    }


    @Inject(method = "setBlockState", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z", ordinal = 1))
    private void antishadowpatch$moveRemoveBlockEntity(BlockPos pos, BlockState newState, int flags, CallbackInfoReturnable<BlockState> cir, @Local(ordinal = 1) BlockState oldState) {
        boolean shouldRemoveIfFurnace = AntiShadowPatch.config().Blocks.BringBackFurnaceXPDupe || !oldState.is(Blocks.FURNACE);

        if(!oldState.is(newState.getBlock()) && oldState.hasBlockEntity() && shouldRemoveIfFurnace) {
            removeBlockEntity(pos);
        }
    }


    @Redirect(method = "setBlockEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BlockEntityType;isValid(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean antishadowpatch$allowSettingUnsupportedBlockEntity(BlockEntityType instance, BlockState state) {
        return true;
    }

}

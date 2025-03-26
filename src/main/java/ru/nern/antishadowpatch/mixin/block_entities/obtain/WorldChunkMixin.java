package ru.nern.antishadowpatch.mixin.block_entities.obtain;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.UpgradeData;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.gen.chunk.BlendingData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Mixin(WorldChunk.class)
public abstract class WorldChunkMixin extends Chunk {

    public WorldChunkMixin(ChunkPos pos, UpgradeData upgradeData, HeightLimitView heightLimitView, Registry<Biome> biomeRegistry, long inhabitedTime, @Nullable ChunkSection[] sectionArray, @Nullable BlendingData blendingData) {
        super(pos, upgradeData, heightLimitView, biomeRegistry, inhabitedTime, sectionArray, blendingData);
    }

    /*
     * In 25w02a, Mojang moved all block-entity-related code for onStateReplaced to a separate method - BlockEntity.onBlockReplaced
     * Because of that, many blocks (if BringBackBlockEntitySwap is enabled) can now contain unsupported block entities.
     * To prevent things like AbstractFurnaceBlockEntity from dropping xp in onBlockReplaced, we first check if the block entity supports the block (so that shulker boxes, for example, wouldn't be able to drop xp from a furnace block entity)
     */
    @WrapWithCondition(
            method = "setBlockState",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/BlockEntity;onBlockReplaced(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V", ordinal = 0)
    )
    private boolean antishadowpatch$preventUnwantedSideEffects(BlockEntity blockEntity, BlockPos pos, BlockState oldState) {
        return blockEntity.getType().supports(oldState);
    }

    @WrapWithCondition(
            method = "setBlockState",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/WorldChunk;removeBlockEntity(Lnet/minecraft/util/math/BlockPos;)V", ordinal = 0)
    )
    private boolean antishadowpatch$preventRemoveBlockEntity(WorldChunk instance, BlockPos pos, @Local(ordinal = 1) BlockState oldState) {
        return !AntiShadowPatch.config().Blocks.BringBackFurnaceXPDupe && oldState.isOf(Blocks.FURNACE);
    }

    @Inject(method = "setBlockState", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 1))
    private void antishadowpatch$moveRemoveBlockEntity(BlockPos pos, BlockState newState, int flags, CallbackInfoReturnable<BlockState> cir, @Local(ordinal = 1) BlockState oldState) {
        boolean shouldRemoveIfFurnace = AntiShadowPatch.config().Blocks.BringBackFurnaceXPDupe || !oldState.isOf(Blocks.FURNACE);

        if(!oldState.isOf(newState.getBlock()) && oldState.hasBlockEntity() && shouldRemoveIfFurnace) {
            removeBlockEntity(pos);
        }
    }

    @Redirect(method = "setBlockEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/BlockEntityType;supports(Lnet/minecraft/block/BlockState;)Z"))
    private boolean antishadowpatch$allowSettingUnsupportedBlockEntity(BlockEntityType instance, BlockState state) {
        return true;
    }

}

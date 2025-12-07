package ru.nern.antishadowpatch.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.block.ChainRestrictedNeighborUpdater;
import net.minecraft.world.block.SimpleNeighborUpdater;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

/*
 * Wrapper around ChainRestrictedNeighborUpdater that calls SimpleNeighborUpdater.
 * Needed because Mojang has changed the neighborUpdate type in the World class, and it is no longer possible to just switch to SimpleNeighborUpdater directly.
 */
public class ChainRestrictedNeighborUpdaterWrapper extends ChainRestrictedNeighborUpdater {
    public SimpleNeighborUpdater updater;

    public ChainRestrictedNeighborUpdaterWrapper(World world) {
        super(world, 0);
        this.updater = new SimpleNeighborUpdater(world);
    }

    @Override
    public void replaceWithStateForNeighborUpdate(Direction direction, BlockState neighborState, BlockPos pos, BlockPos neighborPos, int flags, int maxUpdateDepth) {
        if(this.neighborUpdateCallback != null)
        {
            this.neighborUpdateCallback.accept(pos);
        }

        this.updater.replaceWithStateForNeighborUpdate(direction, neighborState, pos, neighborPos, flags, maxUpdateDepth);
    }

    @Override
    public void updateNeighbor(BlockPos pos, Block sourceBlock, @Nullable WireOrientation orientation) {
        if(this.neighborUpdateCallback != null)
        {
            this.neighborUpdateCallback.accept(pos);
        }

        this.updater.updateNeighbor(pos, sourceBlock, orientation);
    }

    @Override
    public void updateNeighbor(BlockState state, BlockPos pos, Block sourceBlock, @Nullable WireOrientation orientation, boolean notify) {
        if(this.neighborUpdateCallback != null)
        {
            this.neighborUpdateCallback.accept(pos);
        }

        this.updater.updateNeighbor(state, pos, sourceBlock, orientation, notify);
    }

    @Override
    public void updateNeighbors(BlockPos pos, Block sourceBlock, @Nullable Direction except, @Nullable WireOrientation orientation) {
        this.updater.updateNeighbors(pos, sourceBlock, except, orientation);
    }
}

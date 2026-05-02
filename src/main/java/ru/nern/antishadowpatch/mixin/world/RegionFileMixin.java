package ru.nern.antishadowpatch.mixin.world;

import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.storage.RegionFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.ByteBuffer;

@Mixin(RegionFile.class)
public class RegionFileMixin {
    @Inject(method = "write", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/chunk/storage/RegionFile;getExternalChunkPath(Lnet/minecraft/world/level/ChunkPos;)Ljava/nio/file/Path;", shift = At.Shift.AFTER), cancellable = true)
    private void antishadowpatch$bringBackChunkSaveState(ChunkPos pos, ByteBuffer buf, CallbackInfo ci) {
        ci.cancel();
        throw new RuntimeException(String.format("Too big to save, %d > 1048576", buf.remaining()));
    }
}

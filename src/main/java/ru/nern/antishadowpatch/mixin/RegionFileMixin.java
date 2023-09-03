package ru.nern.antishadowpatch.mixin;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.storage.RegionFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.nern.antishadowpatch.AntiShadowPatch;

import java.nio.ByteBuffer;

@Mixin(RegionFile.class)
public class RegionFileMixin {
    @Inject(method = "writeChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/storage/RegionFile;getExternalChunkPath(Lnet/minecraft/util/math/ChunkPos;)Ljava/nio/file/Path;", shift = At.Shift.AFTER), cancellable = true)
    private void antishadowpatch_bringBackChunkSaveState(ChunkPos pos, ByteBuffer buf, CallbackInfo ci) {
        if(AntiShadowPatch.config.bringBackChunkSaveState)
        {
            ci.cancel();
            throw new RuntimeException(String.format("Too big to save, %d > 1048576", buf.remaining()));
        }
    }
}

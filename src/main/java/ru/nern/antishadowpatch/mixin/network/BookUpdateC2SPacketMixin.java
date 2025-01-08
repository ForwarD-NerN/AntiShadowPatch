package ru.nern.antishadowpatch.mixin.network;

import net.minecraft.network.packet.c2s.play.BookUpdateC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BookUpdateC2SPacket.class)
public class BookUpdateC2SPacketMixin {
    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 1024, ordinal = 0))
    private static int antishadowpatch$bringOldPageEditLength(int original) {
        return 8192;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 100, ordinal = 0))
    private static int antishadowpatch$bringOldMaxPages(int original) {
        return 200;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 32, ordinal = 0))
    private static int antishadowpatch$bringOldMaxTitleLength(int original) {
        return 128;
    }
}

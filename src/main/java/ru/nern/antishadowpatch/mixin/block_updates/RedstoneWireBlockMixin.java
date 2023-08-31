package ru.nern.antishadowpatch.mixin.block_updates;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.TrapdoorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import ru.nern.antishadowpatch.AntiShadowPatch;

@Restriction(
        require = {
                @Condition(value = "minecraft", versionPredicates = {">=1.20-pre-2", ">1.20"}),
        }
)
@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin {

    @WrapOperation(
            method = "getRenderConnectionType(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;Z)Lnet/minecraft/block/enums/WireConnection;",
            constant = @Constant(classValue = TrapdoorBlock.class))
    private boolean antishadowpatch_bringBackTrapdoorUpdateSkipping(Object obj, Operation<Boolean> original) {
        return !AntiShadowPatch.config.bringBackTrapdoorUpdateSkipping && original.call(obj);
    }
}

package ru.nern.antishadowpatch.mixin.block_updates;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import ru.nern.antishadowpatch.AntiShadowPatch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Restriction(
		require = {
				@Condition(value = "minecraft", versionPredicates = {">=23w35a", ">=1.20.2"}),
		}
)
@Mixin(ShulkerBoxBlock.class)
public class ShulkerBoxBlockMixin
{
	//Technically not the same, but works as intended
	@ModifyArg(method = "getComparatorOutput", at = @At(value = "INVOKE",
			target = "Lnet/minecraft/screen/ScreenHandler;calculateComparatorOutput(Lnet/minecraft/block/entity/BlockEntity;)I"), index = 0)
	private @Nullable BlockEntity antishadowpatch_bringBackCCE(@Nullable BlockEntity blockEntity) {
		return AntiShadowPatch.config.bringBackCCESuppression ? (ShulkerBoxBlockEntity) blockEntity : blockEntity;
	}
}

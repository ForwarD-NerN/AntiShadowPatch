package ru.nern.antishadowpatch.mixin.block.updates;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import ru.nern.antishadowpatch.AntiShadowPatch;


@Mixin(ShulkerBoxBlock.class)
public class ShulkerBoxBlockMixin
{
	@WrapOperation(
			method = "getComparatorOutput",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/ScreenHandler;calculateComparatorOutput(Lnet/minecraft/block/entity/BlockEntity;)I")
	)
	private int antishadowpatch$bringBackCCESuppression(BlockEntity blockEntity, Operation<Integer> original)
	{
		return AntiShadowPatch.config().Block_Updates.BringBackCCESuppression ?
				ScreenHandler.calculateComparatorOutput((Inventory) blockEntity) : original.call(blockEntity);
	}
}

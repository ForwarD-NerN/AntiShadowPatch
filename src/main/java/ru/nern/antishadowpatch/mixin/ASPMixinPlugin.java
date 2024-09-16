package ru.nern.antishadowpatch.mixin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import ru.nern.antishadowpatch.AntiShadowPatch;
import ru.nern.fconfiglib.v1.MixinConfigHelper;

import java.util.List;
import java.util.Set;

public class ASPMixinPlugin implements IMixinConfigPlugin {
    private MixinConfigHelper helper;

    @Override
    public void onLoad(String mixinPackage) {
        AntiShadowPatch.configManager.init();
        this.helper = new MixinConfigHelper(mixinPackage)
                .init(AntiShadowPatch.configManager);
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        //AntiShadowPatch.LOGGER.info("Mixin: {}. Should be applied: {}", mixinClassName, this.helper.shouldApplyMixin(mixinClassName));
        return this.helper.shouldApplyMixin(mixinClassName);
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}

package me.akaishin.cracked.mixin;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import me.akaishin.cracked.Cracked;

import java.util.Map;

public class CrackedMixinLoader
        implements IFMLLoadingPlugin {
    private static boolean isObfuscatedEnvironment = false;

    public CrackedMixinLoader() {
        Cracked.LOGGER.info("Cracked mixins initialized");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.cracked.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        Cracked.LOGGER.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }

    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
        isObfuscatedEnvironment = (Boolean) data.get("runtimeDeobfuscationEnabled");
    }

    public String getAccessTransformerClass() {
        return null;
    }
}


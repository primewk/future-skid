package me.akaishin.cracked.features;

import java.util.ArrayList;
import java.util.List;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.future.futuregui.FutureGuiOpen;
import me.akaishin.cracked.features.future.gui.CrackedHackGui;
import me.akaishin.cracked.features.future.guifutureold.futuregui.FutureGuiExeter;
import me.akaishin.cracked.features.future.guinew.gui.FutureNewGui;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.modules.client.FutureGuiB8;
import me.akaishin.cracked.features.setting.Setting;
import me.akaishin.cracked.manager.TextManager;
import me.akaishin.cracked.util.Util;

public class Feature
        implements Util {
    public List<Setting> settings = new ArrayList<Setting>();
    public TextManager renderer = Cracked.textManager;
    private String name;

    public Feature() {
    }

    public Feature(String name) {
        this.name = name;
    }

    public static boolean nullCheck() {
        return Feature.mc.player == null;
    }

    public static boolean fullNullCheck() {
        return Feature.mc.player == null || Feature.mc.world == null;
    }

    public String getName() {
        return this.name;
    }

    public List<Setting> getSettings() {
        return this.settings;
    }

    public boolean hasSettings() {
        return !this.settings.isEmpty();
    }

    public boolean isEnabled() {
        if (this instanceof Module) {
            return ((Module) this).isOn();
        }
        return false;
    }

    public boolean isDisabled() {
        return !this.isEnabled();
    }

    public Setting register(Setting setting) {
        setting.setFeature(this);
        this.settings.add(setting);
        if (this instanceof Module && Feature.mc.currentScreen instanceof FutureNewGui) {
            FutureNewGui.getInstance().updateModule((Module) this);
        }
        if (this instanceof Module && Feature.mc.currentScreen instanceof CrackedHackGui) {
            CrackedHackGui.getInstance().updateModule((Module) this);
        }
        if (this instanceof Module && Feature.mc.currentScreen instanceof FutureGuiOpen) {
            FutureGuiOpen.getInstance().updateModule((Module) this);
        }
        if (this instanceof Module && Feature.mc.currentScreen instanceof FutureGuiExeter) {
            FutureGuiExeter.getInstance().updateModule((Module) this);
        }
        return setting;
    }

    public void unregister(Setting settingIn) {
        ArrayList<Setting> removeList = new ArrayList<Setting>();
        for (Setting setting : this.settings) {
            if (!setting.equals(settingIn)) continue;
            removeList.add(setting);
        }
        if (!removeList.isEmpty()) {
            this.settings.removeAll(removeList);
        }
        if (this instanceof Module && Feature.mc.currentScreen instanceof FutureNewGui) {
            FutureNewGui.getInstance().updateModule((Module) this);
        }
        if (this instanceof Module && Feature.mc.currentScreen instanceof CrackedHackGui) {
            CrackedHackGui.getInstance().updateModule((Module) this);
        }
        if (this instanceof Module && Feature.mc.currentScreen instanceof FutureGuiOpen) {
            FutureGuiOpen.getInstance().updateModule((Module) this);
        }
        if (this instanceof Module && Feature.mc.currentScreen instanceof FutureGuiExeter) {
            FutureGuiExeter.getInstance().updateModule((Module) this);
        }
    }

    public Setting getSettingByName(String name) {
        for (Setting setting : this.settings) {
            if (!setting.getName().equalsIgnoreCase(name)) continue;
            return setting;
        }
        return null;
    }

    public void reset() {
        for (Setting setting : this.settings) {
            setting.setValue(setting.getDefaultValue());
        }
    }

    public void clearSettings() {
        this.settings = new ArrayList<Setting>();
    }
}


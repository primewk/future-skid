package me.akaishin.cracked.features.modules.chat;

import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import me.akaishin.cracked.util.TextUtil;

public class ModuleTools
        extends Module {
    private static ModuleTools INSTANCE;
    public Setting<Notifier> notifier = register(new Setting<Notifier>("ModuleNotifier", Notifier.CRACKED));
    public Setting<PopNotifier> popNotifier = register(new Setting<PopNotifier>("PopNotifier", PopNotifier.CRACKED));
	public Setting<TextUtil.Color> abyssColor = this.register(new Setting<TextUtil.Color>("AbyssTextColor", TextUtil.Color.AQUA, color -> this.notifier.getValue() == Notifier.ABYSS));
	public Setting<TextUtil.Color> abyssColorPop = this.register(new Setting<TextUtil.Color>("AbyssTextColor", TextUtil.Color.AQUA, color -> this.popNotifier.getValue() == PopNotifier.ABYSS));
    public ModuleTools() {
        super("ModuleTools", "Change settings", Category.CHAT, true, false, false);
        INSTANCE = this;
    }


    public static ModuleTools getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ModuleTools();
        }
        return INSTANCE;
    }
	
	public static enum Notifier {
        CRACKED,
        FUTURE,
        ABYSS,
        PYRO,
        KONAS,
        PHOBOS,
		DOTGOD,
        MUFFIN,
        WEATHER,
        SNOW,
        CATALYST,
        RUSHERHACK,
        LEGACY,
        EUROPA,
        LUIGIHACK,  
        NONE;
    }

    public static enum PopNotifier {
        CRACKED,
        FUTURE,
        ABYSS,
        PYRO,
        KONAS,
        PHOBOS,
		DOTGOD,
        MUFFIN,
        WEATHER,
        SNOW,
        CATALYST,
        RUSHERHACK,
        LEGACY,
        EUROPA,
        LUIGIHACK,  
        NONE;
    }
}

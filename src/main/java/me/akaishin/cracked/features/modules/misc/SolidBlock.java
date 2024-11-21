package me.akaishin.cracked.features.modules.misc;

import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;

public class SolidBlock
        extends Module {
	private static SolidBlock INSTANCE = new SolidBlock();
	public Setting<Boolean> web = this.register(new Setting<Boolean>("Web", true));
	public Setting<Boolean> vine = this.register(new Setting<Boolean>("Vine", false));
	
    public SolidBlock() {
        super("SolidBlock", "No more running into webs :)", Module.Category.MISC, true, false, false);
		this.setInstance();
    }
	
	public static SolidBlock getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SolidBlock();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

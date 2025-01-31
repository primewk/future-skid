package me.akaishin.cracked.features.modules.render;

import java.awt.Color;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;

public class EnchantColor
        extends Module {
	private static EnchantColor INSTANCE = new EnchantColor();
    public Setting<Integer> red = this.register(new Setting<Integer>("Red", 255, 0, 255));
    public Setting<Integer> green = this.register(new Setting<Integer>("Green", 255, 0, 255));
    public Setting<Integer> blue = this.register(new Setting<Integer>("Blue", 255, 0, 255));
    public Setting<Boolean> rainbow = this.register(new Setting<Boolean>("Rainbow", false));
	public Setting<Boolean> betterRainbow = this.register(new Setting<Boolean>("RainbowGlint", false));

    public EnchantColor() {
        super("EnchantColor", "Changes the enchant glint color", Module.Category.RENDER, true, false, true);
		this.setInstance();
    }
	
	public static EnchantColor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EnchantColor();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    public static Color getColor(long offset, float fade) {
        if (!Cracked.moduleManager.getModuleT(EnchantColor.class).rainbow.getValue().booleanValue()) {
            return new Color(Cracked.moduleManager.getModuleT(EnchantColor.class).red.getValue(), Cracked.moduleManager.getModuleT(EnchantColor.class).green.getValue(), Cracked.moduleManager.getModuleT(EnchantColor.class).blue.getValue());
        }
        float hue = (float)(System.nanoTime() + offset) / 1.0E10f % 1.0f;
        long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, 1.0f, 1.0f)), 16);
        Color c = new Color((int)color);
        return new Color((float)c.getRed() / 255.0f * fade, (float)c.getGreen() / 255.0f * fade, (float)c.getBlue() / 255.0f * fade, (float)c.getAlpha() / 255.0f);
    }

    @Override
    public void onUpdate() {
        if (this.rainbow.getValue().booleanValue()) {
            this.cycleRainbow();
        }
    }

    public void cycleRainbow() {
        float[] tick_color = new float[]{(float)(System.currentTimeMillis() % 11520L) / 11520.0f};
        int color_rgb_o = Color.HSBtoRGB(tick_color[0], 0.8f, 0.8f);
        this.red.setValue(color_rgb_o >> 16 & 0xFF);
        this.green.setValue(color_rgb_o >> 8 & 0xFF);
        this.blue.setValue(color_rgb_o & 0xFF);
    }
}

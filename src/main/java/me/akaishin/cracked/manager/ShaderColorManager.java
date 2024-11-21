package me.akaishin.cracked.manager;

import java.awt.Color;
import me.akaishin.cracked.features.modules.render.futurechams.FutureChams;
import me.akaishin.cracked.util.shaderchams.utilities.ColorUtil;


public class ShaderColorManager {
    private float red = 1.0f;
    private float blue = 1.0f;
    private float alpha = 1.0f;
    private float green = 1.0f;
    private Color color = new Color(this.red, this.green, this.blue, this.alpha);

    public void setRed(float f) {
        this.red = f;
        this.updateColor();
    }

    public void setBlue(float f) {
        this.blue = f;
        this.updateColor();
    }

    public void setColor(int n, int n2, int n3, int n4) {
        this.red = (float)n / 255.0f;
        this.green = (float)n2 / 255.0f;
        this.blue = (float)n3 / 255.0f;
        this.alpha = (float)n4 / 255.0f;
        this.updateColor();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getColorAsInt() {
        return ColorUtil.toRGBA(this.color);
    }

    public void setGreen(float f) {
        this.green = f;
        this.updateColor();
    }

    public Color getCurrent(int n) {
        return new Color(FutureChams.INSTANCE().red.getValue(), FutureChams.INSTANCE().green.getValue(), FutureChams.INSTANCE().blue.getValue(), n);
    }

    public ShaderColorManager() {
        Color current = new Color(-1);
    }

    public int getColorWithAlpha(int n) {
        return ColorUtil.toRGBA(new Color(this.red, this.green, this.blue, (float)n / 255.0f));
    }

    public Color getColor() {
        return this.color;
    }

    public int getColorAsIntFullAlpha() {
        return ColorUtil.toRGBA(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 255));
    }

    public void setAlpha(float f) {
        this.alpha = f;
        this.updateColor();
    }

    public void updateColor() {
        this.setColor(new Color(this.red, this.green, this.blue, this.alpha));
    }

    public Color getCurrent() {
        return new Color(FutureChams.INSTANCE().red.getValue(), FutureChams.INSTANCE().green.getValue(), FutureChams.INSTANCE().blue.getValue(), FutureChams.INSTANCE().alpha.getValue());
    }

    public boolean isRainbow() {
        return FutureChams.INSTANCE().rainbow.getValue();
    }

    public void setColor(float f, float f2, float f3, float f4) {
        this.red = f;
        this.green = f2;
        this.blue = f3;
        this.alpha = f4;
        this.updateColor();
    }
}

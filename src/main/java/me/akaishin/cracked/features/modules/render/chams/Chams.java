package me.akaishin.cracked.features.modules.render.chams;

import java.awt.Color;

import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.modules.render.CameraClip;
import me.akaishin.cracked.features.modules.render.NoRender;
import me.akaishin.cracked.features.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Chams
extends Module {
    public static Chams INSTANCE = new Chams();
    
        static {
        Chams.INSTANCE = new Chams();
    }

    private final Setting<Page> page = this.register(new Setting<>("Settings", Page.GLOBAL));
    public final Setting<Boolean> fill = this.register(new Setting<>("Fill", true, v -> this.page.getValue() == Page.GLOBAL));
    public final Setting<Boolean> xqz = this.register(new Setting<>("XQZ", true, v -> this.page.getValue() == Page.GLOBAL && this.fill.getValue())); //fill getvalue posibl error
    public final Setting<Boolean> wireframe = this.register(new Setting<>("Wireframe", true, v -> this.page.getValue() == Page.GLOBAL));
    public final Setting<Model> model = this.register(new Setting<>("Model", Model.OFF, v -> this.page.getValue() == Page.GLOBAL));
    public final Setting<Boolean> self = this.register(new Setting<>("Self", true, v -> this.page.getValue() == Page.GLOBAL));
    public final Setting<Boolean> noInterp = this.register(new Setting<>("NoInterp", false, v -> this.page.getValue() == Page.GLOBAL));
    public final Setting<Boolean> sneak = this.register(new Setting<>("Sneak", false, v -> this.page.getValue() == Page.GLOBAL));
    public final Setting<Boolean> glint = this.register(new Setting<>("Glint", false, v -> this.page.getValue() == Page.GLOBAL));
    public final Setting<Float> lineWidth = this.register(new Setting<>("LineWidth", 1.0f, 0.1f, 3.0f, v -> this.page.getValue() == Page.COLORS));

    public final Setting<Integer> colorGreen = this.register(new Setting<>("ColorGreen",251,0,255, v -> this.page.getValue() == Page.COLORS));
    public final Setting<Integer> colorRed = this.register(new Setting<>("ColorRed",2,0,255, v -> this.page.getValue() == Page.COLORS));
    public final Setting<Integer> colorBlue = this.register(new Setting<>("ColorBlue",0,0,255, v -> this.page.getValue() == Page.COLORS));
    public final Setting<Integer> colorAlpha = this.register(new Setting<>("ColorAlpha",42,0,255, v -> this.page.getValue() == Page.COLORS));

    public final Setting<Boolean> lineColorEnable = this.register(new Setting<>("LineColorEnable",true, v -> this.page.getValue() == Page.COLORS)); //on is enable color solucionar o aser que cuando se conecte esto se active lo de linercolor abajo
    
    public final Setting<Integer> lineColorRed = this.register(new Setting<>("LineColorRed", 2, 0, 255, v -> this.page.getValue() == Page.COLORS));
    public final Setting<Integer> lineColorGreen = this.register(new Setting<>("LineColorGreen", 255, 0, 255, v -> this.page.getValue() == Page.COLORS));
    public final Setting<Integer> lineColorBlue = this.register(new Setting<>("LineColorBlue", 8, 0, 255, v -> this.page.getValue() == Page.COLORS));
    public final Setting<Integer> lineColorAlpha = this.register(new Setting<>("LineColorAlpha", 131, 0, 255, v -> this.page.getValue() == Page.COLORS));

    public final Setting<Boolean> modelColorEnable = this.register(new Setting<>("ModelColorEnable",true, v -> this.page.getValue() == Page.COLORS)); // el mismo problema que arriva

    public final Setting<Integer> modelColorRed = this.register(new Setting<>("ModelColorRed", 0, 0, 255, v -> this.page.getValue() == Page.COLORS));
    public final Setting<Integer> modelColorGreen = this.register(new Setting<>("ModelColorGreen", 255, 0, 255, v -> this.page.getValue() == Page.COLORS));
    public final Setting<Integer> modelColorBlue = this.register(new Setting<>("ModelColorBlue", 2, 0, 255, v -> this.page.getValue() == Page.COLORS));
    public final Setting<Integer> modelColorAlpha = this.register(new Setting<>("ModelColorAlpha", 88, 0, 255, v -> this.page.getValue() == Page.COLORS));

    private final Setting<Boolean> hide = this.register(new Setting<>("Hide", false, v -> this.page.getValue() == Page.GLOBAL));
    private final Setting<Float> range = this.register(new Setting<>("Range", 1.5f, 1.0f, 12.0f, v -> this.hide.getValue() && this.page.getValue() == Page.GLOBAL));//HIDE POSIBLE ERROR igual que mas arriba

    public Chams() {
        super("Chams", "Chams UFFF", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }

    public static Chams getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Chams();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }


    @Override
    public String getInfo() {
        String info = null;
        if (this.fill.getValue()) {
            info = "Fill";
        } else if (this.wireframe.getValue()) {
            info = "Wireframe";
        }
        if (this.wireframe.getValue() && this.fill.getValue()) {
            info = "Both";
        }
        return info;
    }

    @SubscribeEvent
    public void onRenderPlayerEvent(RenderPlayerEvent.Pre event) {
        event.getEntityPlayer().hurtTime = 0;
    }

    @SubscribeEvent
    public void onRenderLiving(RenderLivingEvent.Pre<EntityLivingBase> event) {
        double dist;
        if (event.getEntity() instanceof EntityPlayer && event.getEntity() != Chams.mc.player && this.hide.getValue() && (dist = event.getEntity().getDistance(Chams.mc.player)) < (double) this.range.getValue()) {
            event.setCanceled(true);
        }
    }

    public static enum Model {
        XQZ,
        VANILLA,
        OFF

    }

    public static enum Page {
        COLORS,
        GLOBAL

    }
}


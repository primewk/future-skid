package me.akaishin.cracked.features.modules.client;

import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import net.minecraft.util.ResourceLocation;

public class Capes
extends Module {
    public static Capes INSTANCE;
    public final Setting<Mode> mode = this.register(new Setting<>("Mode", Mode.Future));
    public final Setting<String> Name = this.register(new Setting<>("CapeName", "CrackedCustom"));

    public Capes() {
        super("Capes", "Universal Cape Good", Module.Category.CLIENT, true, false, true);
        INSTANCE = this;
    }


    public ResourceLocation Cape() {
        if (this.mode.getValue() == Mode.Future) {
            return new ResourceLocation("textures/capes/future.png");
        }
        if (this.mode.getValue() == Mode.Anime) {
            return new ResourceLocation("textures/capes/capeanimet.gif");
        }
        if (this.mode.getValue() == Mode.GS) {
            return new ResourceLocation("textures/capes/gs.png");
        }
        if (this.mode.getValue() == Mode.Enderman) {
            return new ResourceLocation("textures/capes/enderman.png");
        }
        if (this.mode.getValue() == Mode.Hyper) {
            return new ResourceLocation("textures/capes/hyper.png");
        }
        if (this.mode.getValue() == Mode.KuroPack) {
            return new ResourceLocation("textures/capes/kuropack.png");
        }
        if (this.mode.getValue() == Mode.Pepsi) {
            return new ResourceLocation("textures/capes/pepsimod.png");
        }
        if (this.mode.getValue() == Mode.Rayoc) {
            return new ResourceLocation("textures/capes/rayoc.gif");
        }
        if (this.mode.getValue() == Mode.Rusherhack) {
            return new ResourceLocation("textures/capes/rusherhack.png");
        }
        if (this.mode.getValue() == Mode.Unknown) {
            return new ResourceLocation("textures/capes/unknown.png");
        }
        if (this.mode.getValue() == Mode.Urmonia) {
            return new ResourceLocation("textures/capes/urmonia.png");
        }
        if (this.mode.getValue() == Mode.Custom) {
            return new ResourceLocation("textures/capes/" + this.Name.getValue() + ".png");
        }
        return null;
    }

    public enum Mode {
        Custom,
        Future,
        Anime,
        GS,
        Enderman,
        Hyper,
        KuroPack,
        Pepsi,
        Rayoc,
        Rusherhack,
        Unknown,
        Urmonia,

    }
}
package me.akaishin.cracked.util.shaderchams.chams;

import me.akaishin.cracked.util.shaderchams.chams.FramebufferShader;
import me.akaishin.cracked.util.shaderchams.chams.ShaderProducer;
import me.akaishin.cracked.util.shaderchams.chams.shaders.AquaGlShader;
import me.akaishin.cracked.util.shaderchams.chams.shaders.AquaShader;
import me.akaishin.cracked.util.shaderchams.chams.shaders.BasicShader;
import me.akaishin.cracked.util.shaderchams.chams.shaders.FlowGlShader;
import me.akaishin.cracked.util.shaderchams.chams.shaders.FlowShader;
import me.akaishin.cracked.util.shaderchams.chams.shaders.GangGlShader;
import me.akaishin.cracked.util.shaderchams.chams.shaders.GlowShader;
import me.akaishin.cracked.util.shaderchams.chams.shaders.HolyFuckShader;
import me.akaishin.cracked.util.shaderchams.chams.shaders.PurpleShader;
import me.akaishin.cracked.util.shaderchams.chams.shaders.RedShader;
import me.akaishin.cracked.util.shaderchams.chams.shaders.SmokeShader;

public enum ShaderMode {
    AQUA("Aqua", AquaShader::INSTANCE),
    AQUAGLOW("AquaGlow", AquaGlShader::INSTANCE),
    FLOW("Flow", FlowShader::INSTANCE),
    FLOWBLUR("FlowBlur", ShaderMode::static0),
    FLOWGLOW("FlowGLow", FlowGlShader::INSTANCE),
    GHOST("Glow", GlowShader::INSTANCE),
    SMOKE("Smoke", SmokeShader::INSTANCE),
    RED("Red", RedShader::INSTANCE),
    HOLYFUCK("HolyFuck", HolyFuckShader::INSTANCE),
    GANG("Gang", GangGlShader::INSTANCE),
    BLUEFLAMES("BlueFlames", ShaderMode::static1),
    GAMER("Gamer", ShaderMode::static2),
    CODEX("Codex", ShaderMode::static3),
    GALAXY("Galaxy", ShaderMode::static4),
    DDEV("Ddev", ShaderMode::static5),
    CRAZY("Crazy", ShaderMode::static6),
    SNOW("Snow", ShaderMode::static7),
    TECHNO("Techno", ShaderMode::static8),
    GOLDEN("Golden", ShaderMode::static9),
    HOTSHIT("HotShit", ShaderMode::static10),
    GUISHADER("GuiShader", ShaderMode::static11),
    HIDEF("Hidef", ShaderMode::static12),
    HOMIE("Homie", ShaderMode::static13),
    KFC("KFC", ShaderMode::static14),
    OHMYLORD("Lord", ShaderMode::static15),
    SHELDON("Sheldon", ShaderMode::static16),
    SMOKY("Smoky", ShaderMode::static17),
    STROXINJAT("Stroxinjat", ShaderMode::static18),
    WEIRD("Weird", ShaderMode::static19),
    YIPPIEOWNS("YippieOwns", ShaderMode::static20),
    PURPLE("Purple", PurpleShader::INSTANCE);

    private final String name;
    private final ShaderProducer shaderProducer;

    private static FramebufferShader static9() {
        return BasicShader.INSTANCE("golden.frag", 0.01f);
    }

    private static FramebufferShader static10() {
        return BasicShader.INSTANCE("hotshit.frag", 0.005f);
    }

    private static FramebufferShader static18() {
        return BasicShader.INSTANCE("stroxinjat.frag");
    }

    private static FramebufferShader static2() {
        return BasicShader.INSTANCE("gamer.frag", 0.03f);
    }

    private static FramebufferShader static3() {
        return BasicShader.INSTANCE("codex.frag");
    }

    private static FramebufferShader static14() {
        return BasicShader.INSTANCE("kfc.frag", 0.01f);
    }

    private static FramebufferShader static19() {
        return BasicShader.INSTANCE("weird.frag", 0.01f);
    }

    private static FramebufferShader static15() {
        return BasicShader.INSTANCE("ohmylord.frag", 0.01f);
    }

    private static FramebufferShader static0() {
        return BasicShader.INSTANCE("flowglow_z.frag", 5.0E-4f);
    }

    private static FramebufferShader static5() {
        return BasicShader.INSTANCE("ddev.frag");
    }

    private static FramebufferShader static12() {
        return BasicShader.INSTANCE("hidef.frag", 0.05f);
    }

    private static FramebufferShader static1() {
        return BasicShader.INSTANCE("blueflames.frag", 0.01f);
    }

    private static FramebufferShader static17() {
        return BasicShader.INSTANCE("smoky.frag", 0.001f);
    }

    private static FramebufferShader static7() {
        return BasicShader.INSTANCE("snow.frag", 0.01f);
    }

    ShaderMode(String string2, ShaderProducer shaderProducer) {
        this.name = string2;
        this.shaderProducer = shaderProducer;
    }

    private static FramebufferShader static6() {
        return BasicShader.INSTANCE("crazy.frag", 0.01f);
    }

    private static FramebufferShader static8() {
        return BasicShader.INSTANCE("techno.frag", 0.01f);
    }

    private static FramebufferShader static4() {
        return BasicShader.INSTANCE("galaxy33.frag", 0.001f);
    }

    private static FramebufferShader static13() {
        return BasicShader.INSTANCE("homie.frag", 0.001f);
    }

    private static FramebufferShader static20() {
        return BasicShader.INSTANCE("yippieOwns.frag");
    }

    public String getName() {
        return this.name;
    }

    private static FramebufferShader static11() {
        return BasicShader.INSTANCE("clickguishader.frag", 0.02f);
    }

    private static FramebufferShader static16() {
        return BasicShader.INSTANCE("sheldon.frag", 0.001f);
    }

    public FramebufferShader getShader() {
        return this.shaderProducer.INSTANCE();
    }
}


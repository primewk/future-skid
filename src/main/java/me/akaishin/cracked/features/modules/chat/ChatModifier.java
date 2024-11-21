package me.akaishin.cracked.features.modules.chat;

import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.akaishin.cracked.event.events.PacketEvent;
import me.akaishin.cracked.features.future.gui.custom.GuiChat;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import me.akaishin.cracked.util.Timer;

public class ChatModifier
        extends Module {
            private static ChatModifier INSTANCE = new ChatModifier();
            private final Timer timer = new Timer();
            public Setting<Suffix> suffix = this.register(new Setting<Suffix>("Suffix", Suffix.NONE, "Your Suffix."));
            public Setting<String> custom = this.register(new Setting("Custom", "Cracked", v -> this.suffix.getValue() == Suffix.CUSTOM));
            public Setting<TextColor> textcolor = this.register(new Setting<TextColor>("TextColor", TextColor.NONE, "Your text color."));
            public Setting<StiTextColor> stiTextColor = this.register(new Setting<StiTextColor>("StiTextColor", StiTextColor.NONE, "Your sti text color."));
            public Setting<Boolean> clean = this.register(new Setting<Boolean>("CleanChat", Boolean.valueOf(false), "Cleans your chat"));
            public Setting<Boolean> infinite = this.register(new Setting<Boolean>("Infinite", Boolean.valueOf(false), "Makes your chat infinite."));
            public Setting<Boolean> crack = this.register(new Setting<Boolean>("GlobalChat", Boolean.valueOf(false), "Global Chat Prefix"));
            //SmoothChat uff estaba re dificil xd
            public Setting<Boolean> smoothChat = register(new Setting("SmoothChat", Boolean.valueOf(false)));
  
            public Setting<Double> xOffset = register(new Setting("XOffset", Double.valueOf(0.0D), Double.valueOf(0.0D), Integer.valueOf(600), v -> ((Boolean)this.smoothChat.getValue()).booleanValue()));
            
            public Setting<Double> yOffset = register(new Setting("YOffset", Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(30.0D), v -> ((Boolean)this.smoothChat.getValue()).booleanValue()));
            
            public Setting<Double> vSpeed = register(new Setting("VSpeed", Double.valueOf(30.0D), Double.valueOf(1.0D), Double.valueOf(100.0D), v -> ((Boolean)this.smoothChat.getValue()).booleanValue()));
            
            public Setting<Double> vLength = register(new Setting("VLength", Double.valueOf(10.0D), Double.valueOf(5.0D), Double.valueOf(100.0D), v -> ((Boolean)this.smoothChat.getValue()).booleanValue()));
            
            public Setting<Double> vIncrements = register(new Setting("VIncrements", Double.valueOf(1.0D), Double.valueOf(1.0D), Double.valueOf(5.0D), v -> ((Boolean)this.smoothChat.getValue()).booleanValue()));
            
            public Setting<Type> type = register(new Setting("Type", Type.HORIZONTAL, v -> ((Boolean)this.smoothChat.getValue()).booleanValue()));
            public static GuiChat guiChatSmooth;
            public static GuiNewChat guiChat;


            public ChatModifier() {
                super("ChatModifier", "Modifies your chat", Module.Category.CHAT, true, false, false);
                this.setInstance();
            }

        
            public static ChatModifier getInstance() {
                if (INSTANCE == null) {
                    INSTANCE = new ChatModifier();
                }
                return INSTANCE;
            }
        
            private void setInstance() {
                INSTANCE = this;
            }

            @SubscribeEvent
            public void onPacketSend(PacketEvent.Send event) {
                if (event.getStage() == 0 && event.getPacket() instanceof CPacketChatMessage) {
                    CPacketChatMessage packet = (CPacketChatMessage)event.getPacket();
                    String s = packet.getMessage();
                    if (s.startsWith("/") || s.startsWith("!")) {
                        return;
                    }
        
                    switch (this.suffix.getValue()) {
                        case CRACKED: {
                            s = s + " \u00bb " + this.convertToUnicode("cracked v1.0");
                            break;
                        }
                    }
                    switch (this.suffix.getValue()) {
                        case FUTURE: {
                            s = s + " \u00bb " + this.convertToUnicode("future v2.13.5");
                            break;
                        }
                    }
                    switch (this.suffix.getValue()) {
                        case ABYSS: {
                            s = s + " \u00bb " + this.convertToUnicode("abyss v3.0");
                            break;
                        }
                    }
                    switch (this.suffix.getValue()) {
                        case PYRO: {
                            s = s + " \u00bb " + this.convertToUnicode("pyro v4.0");
                            break;
                        }
                    }
                    switch (this.suffix.getValue()) {
                        case CUSTOM: {
                            s = s + " \u00bb " + this.convertToUnicode(this.custom.getValue());
                            break;
                        }
                    }
        
                    switch (this.stiTextColor.getValue()) {
                        case RANDOM: {
                            int minNum = 1;
                            int maxNum = 16;
                            int randomNumber = (int)Math.floor(Math.random()*(maxNum-minNum+1)+minNum);
                            if (randomNumber == 1) {
                                s = "!2" + s;
                            }
                            if (randomNumber == 2) {
                                s = "!3" + s;
                            }
                            if (randomNumber == 3) {
                                s = "!4" + s;
                            }
                            if (randomNumber == 4) {
                                s = "!5" + s;
                            }
                            if (randomNumber == 5) {
                                s = "!7" + s;
                            }
                            if (randomNumber == 6) {
                                s = "!8" + s;
                            }
                            if (randomNumber == 7) {
                                s = "!9" + s;
                            }
                            if (randomNumber == 8) {
                                s = "!g" + s;
                            }
                            if (randomNumber == 9) {
                                s = "!c" + s;
                            }
                            if (randomNumber == 10) {
                                s = "!d" + s;
                            }
                            if (randomNumber == 11) {
                                s = "!a" + s;
                            }
                            if (randomNumber == 12) {
                                s = "!e" + s;
                                }
                            if (randomNumber == 13) {
                                s = "!b" + s;
                            }
                            if (randomNumber == 14) {
                                s = "!f" + s;
                            }
                            if (randomNumber == 15) {
                                s = "!1" + s;
                            }
                            if (randomNumber == 16) {
                                s = "!6" + s;
                            }
                            break;
                        }	
                        case LIGHT_GREEN: {
                            s = "!2" + s;
                            break;
                        }
                        case LIGHT_BLUE: {
                            s = "!3" + s;
                            break;
                        }
                        case LIGHT_RED: {
                            s = "!4" + s;
                        }
                        case LIGHT_AQUA: {
                            s = "!5" + s;
                            break;
                        }
                        case YELLOW: {
                            s = "!7" + s;
                            break;
                        }
                        case LIGHT_GRAY: {
                            s = "!8" + s;
                            break;
                        }
                        case LIGHT_PURPLE: {
                            s = "!g" + s;
                            break;
                        }
                        case GRAY: {
                            s = "!c" + s;
                            break;
                        }
                        case BLACK: {
                            s = "!9" + s;
                            break;
                        }
                        case BLUE: {
                            s = "!d" + s;
                            break;
                        }
                        case GREEN: {
                            s = "!a" + s;
                            break;
                        }
                        case AQUA: {
                            s = "!e" + s;
                            break;
                        }
                        case RED: {
                            s = "!b" + s;
                            break;
                        }
                        case PURPLE: {
                            s = "!f" + s;
                            break;
                        }
                        case GOLD: {
                            s = "!6" + s;
                            break;
                        }
                        case WHITE: {
                            s = "!1" + s;
                            break;
                        }
                        case ITALIC: {
                            s = "!h" + s;
                            break;
                        }
                        case STRIKE: {
                            s = "!k" + s;
                            break;
                        }
                        case UNDERLINE: {
                            s = "!i" + s;
                            break;
                        }
                        case BOLD: {
                            s = "!j" + s;
                            break;
                        }
                    }
                    switch (this.textcolor.getValue()) {
                        case GREEN: {
                            s = "> " + s;
                            break;
                        }
                        case YELLOW: {
                            s = "# " + s;
                            break;
                        }
                        case GOLD: {
                            s = "$ " + s;
                        }
                        case BLUE: {
                            s = "! " + s;
                            break;
                        }
                        case AQUA: {
                            s = "`` " + s;
                            break;
                        }
                        case PURPLE: {
                            s = "? " + s;
                            break;
                        }
                        case RED: {
                            s = "& " + s;
                            break;
                        }
                        case DARKRED: {
                            s = "@ " + s;
                            break;
                        }
                        case GRAY: {
                            s = ". " + s;
                            break;
                        }
                    }
        
                    if (s.length() >= 256) {
                        s = s.substring(0, 256);
                    }
                    packet.message = s;
                }
            }



            private String convertToUnicode(String base) {
                String new_base = base;
        
                new_base = new_base.replace("a", "\u1d00");
                new_base = new_base.replace("b", "\u0299");
                new_base = new_base.replace("c", "\u1d04");
                new_base = new_base.replace("d", "\u1d05");
                new_base = new_base.replace("e", "\u1d07");
                new_base = new_base.replace("f", "\u0493");
                new_base = new_base.replace("g", "\u0262");
                new_base = new_base.replace("h", "\u029c");
                new_base = new_base.replace("i", "\u026a");
                new_base = new_base.replace("j", "\u1d0a");
                new_base = new_base.replace("k", "\u1d0b");
                new_base = new_base.replace("l", "\u029f");
                new_base = new_base.replace("m", "\u1d0d");
                new_base = new_base.replace("n", "\u0274");
                new_base = new_base.replace("o", "\u1d0f");
                new_base = new_base.replace("p", "\u1d18");
                new_base = new_base.replace("q", "\u01eb");
                new_base = new_base.replace("r", "\u0280");
                new_base = new_base.replace("s", "\u0455");
                new_base = new_base.replace("t", "\u1d1b");
                new_base = new_base.replace("u", "\u1d1c");
                new_base = new_base.replace("v", "\u1d20");
                new_base = new_base.replace("w", "\u1d21");
                new_base = new_base.replace("x", "\u0445");
                new_base = new_base.replace("y", "\u028f");
                new_base = new_base.replace("z", "\u1d22");
        
                return new_base;
            }
        
        
            /*private String convertToUnicode(String base) {
                String new_base;
                new_base = base.replace("a", "\u1d00");
                new_base = new_base.replace("b", "\u0299");
                new_base = new_base.replace("c", "\u1d04");
                new_base = new_base.replace("d", "\u1d05");
                new_base = new_base.replace("e", "\u1d07");
                new_base = new_base.replace("f", "\u0493");
                new_base = new_base.replace("g", "\u0262");
                new_base = new_base.replace("h", "\u029c");
                new_base = new_base.replace("i", "\u026a");
                new_base = new_base.replace("j", "\u1d0a");
                new_base = new_base.replace("k", "\u1d0b");
                new_base = new_base.replace("l", "\u029f");
                new_base = new_base.replace("m", "\u1d0d");
                new_base = new_base.replace("n", "\u0274");
                new_base = new_base.replace("o", "\u1d0f");
                new_base = new_base.replace("p", "\u1d18");
                new_base = new_base.replace("q", "\u01eb");
                new_base = new_base.replace("r", "\u0280");
                new_base = new_base.replace("s", "\u0455");
                new_base = new_base.replace("t", "\u1d1b");
                new_base = new_base.replace("u", "\u1d1c");
                new_base = new_base.replace("v", "\u1d20");
                new_base = new_base.replace("w", "\u1d21");
                new_base = new_base.replace("x", "\u0445");
                new_base = new_base.replace("y", "\u028f");
                new_base = new_base.replace("z", "\u1d22");
                
                new_base = new_base.replace("0", "\u2080");
                new_base = new_base.replace("1", "\u2081");
                new_base = new_base.replace("2", "\u2082");
                new_base = new_base.replace("3", "\u2083");
                new_base = new_base.replace("4", "\u2084");
                new_base = new_base.replace("5", "\u2085");
                new_base = new_base.replace("6", "\u2086");
                new_base = new_base.replace("7", "\u2087");
                new_base = new_base.replace("8", "\u2088");
                new_base = new_base.replace("9", "\u2089");
                
                return new_base;
            }*/
        
            @SubscribeEvent
            public void onChatPacketReceive(PacketEvent.Receive event) {
                if (event.getStage() != 0 || event.getPacket() instanceof SPacketChat) {
                    // empty if block
                }
            }
        
            @SubscribeEvent
            public
            void onPacketReceive(PacketEvent.Receive event) {
                if (event.getStage() == 0 && this.crack.getValue() && event.getPacket() instanceof SPacketChat) {
                    String originalMessage = ((SPacketChat) event.getPacket()).chatComponent.getFormattedText();
                    String message = this.getCrackString(originalMessage) + originalMessage;
                    ((SPacketChat) event.getPacket()).chatComponent = new TextComponentString(message);
                }
            }
        
            public String getCrackString(String message) {
                return message = "\u00A7b\u00A7l[\u00A7a\u00A7lCracked\u00A7b\u00A7l]\u00A7r ";
            }
        
            private boolean shouldSendMessage(EntityPlayer player) {
                if (player.dimension != 1) {
                    return false;
                }
                return player.getPosition().equals(new Vec3i(0, 240, 0));
            }
            //smoothChat :(
            public void onEnable() {
                guiChatSmooth = new GuiChat(mc);
                ObfuscationReflectionHelper.setPrivateValue(GuiIngame.class, mc.ingameGUI, guiChatSmooth, new String[]{"field_73840_e"});
            }

            public void onDisable() {
                guiChat = new GuiNewChat(mc);
                ObfuscationReflectionHelper.setPrivateValue(GuiIngame.class, mc.ingameGUI, guiChat, new String[]{"field_73840_e"});
            }


            public void onLogin() {
                if (this.isEnabled()) {
                    this.disable();
                    this.enable();
                }
            }

            public static enum Type {
                HORIZONTAL,
                VERTICAL;
            }
        
            public enum Suffix {
                NONE, 
                CRACKED,
                FUTURE,
                ABYSS,
                PYRO,
                CUSTOM;
            }
            
            public enum TextColor {
                NONE,
                GREEN,
                YELLOW,
                GOLD,
                BLUE,
                AQUA,
                PURPLE,
                RED,
                DARKRED,
                GRAY;
            }
            
            public enum StiTextColor {
                NONE,
                RANDOM,
                GREEN,
                BLUE,
                AQUA,
                YELLOW,
                GOLD,
                RED,
                PURPLE,
                LIGHT_BLUE,
                LIGHT_GREEN,
                LIGHT_AQUA,
                LIGHT_RED,
                LIGHT_PURPLE,
                LIGHT_GRAY,
                GRAY,
                BLACK,
                WHITE,
                BOLD,
                STRIKE,
                UNDERLINE,
                ITALIC;
            }
}

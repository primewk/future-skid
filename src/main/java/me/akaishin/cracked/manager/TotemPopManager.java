package me.akaishin.cracked.manager;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.akaishin.cracked.Cracked;
import me.akaishin.cracked.features.Feature;
import me.akaishin.cracked.features.command.Command;
import me.akaishin.cracked.features.modules.chat.ModuleTools;
import me.akaishin.cracked.features.modules.chat.Notifications;
import me.akaishin.cracked.util.TextUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class TotemPopManager
        extends Feature {
    private final Set<EntityPlayer> toAnnounce = new HashSet<>();
    private Notifications notifications;
    private Map<EntityPlayer, Integer> poplist = new ConcurrentHashMap<>();

    public void onUpdate() {
        if (this.notifications.totemAnnounce.passedMs(this.notifications.delay.getValue()) && this.notifications.isOn() && this.notifications.totemPops.getValue()) {
            for (EntityPlayer player : this.toAnnounce) {
                if (player == null) continue;
                int playerNumber = 0;
                for (char character : player.getName().toCharArray()) {
                    playerNumber += character;
                    playerNumber *= 10;
                }
                Command.sendOverwriteMessage(this.pop(player), playerNumber, this.notifications.totemNoti.getValue());
                this.toAnnounce.remove(player);
                this.notifications.totemAnnounce.reset();
                break;
            }
        }
    }

    public String pop(EntityPlayer player) {
        if (this.getTotemPops(player) == 1) {
            if (ModuleTools.getInstance().isEnabled()) {
                switch (ModuleTools.getInstance().popNotifier.getValue()) {
                    case CRACKED: {
                        String text = (Cracked.commandManager.getClientMessage()) + " " + ChatFormatting.GREEN + player.getName() + ChatFormatting.GRAY + " popped " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.GRAY + " totem.";
                        return text;
                    }
                    case FUTURE: {
                        String text = ChatFormatting.RED + "[Future] " + ChatFormatting.GREEN + player.getName() + ChatFormatting.GRAY + " just popped " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.GRAY + " totem.";
                        return text;
                    }
                    case PHOBOS: {
                        String text = ChatFormatting.GOLD + player.getName() + ChatFormatting.RED + " popped " + ChatFormatting.GOLD + this.getTotemPops(player) + ChatFormatting.RED + " totem.";
                        return text;
                    }
                    case DOTGOD: {
                        String text = ChatFormatting.DARK_PURPLE + "[" + ChatFormatting.LIGHT_PURPLE + "DotGod.CC" + ChatFormatting.DARK_PURPLE + "] " + ChatFormatting.LIGHT_PURPLE + player.getName() + " has popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.LIGHT_PURPLE + " time in total!";
                        return text;
                    }
				    case SNOW: {
                        String text = ChatFormatting.BLUE + "[" + ChatFormatting.AQUA + "Snow" + ChatFormatting.BLUE + "] [" + ChatFormatting.DARK_AQUA + player.getName() + ChatFormatting.BLUE + "] " + " popped " + ChatFormatting.GOLD + this.getTotemPops(player) + ChatFormatting.BLUE + " totem";
                        return text;
                    }
                    case WEATHER: {
                        String text = ChatFormatting.AQUA + "[" + ChatFormatting.AQUA + "Weather" + ChatFormatting.AQUA + "] " + ChatFormatting.DARK_AQUA + player.getName() + ChatFormatting.WHITE + " popped " + ChatFormatting.RED +  this.getTotemPops(player) + ChatFormatting.WHITE + " totem";
                        return text;
                    }
                    case CATALYST: {
                        String text = ChatFormatting.DARK_GRAY + "[" + ChatFormatting.AQUA + "Catalyst" + ChatFormatting.DARK_GRAY + "] " + ChatFormatting.GRAY + player.getName() + ChatFormatting.LIGHT_PURPLE + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.WHITE + " totem";
                        return text;
                    }
                    case RUSHERHACK: {
                        String text = ChatFormatting.WHITE + "[" + ChatFormatting.GREEN + "rusherhack" + ChatFormatting.WHITE + "] " + ChatFormatting.GOLD + player.getName() + ChatFormatting.LIGHT_PURPLE + " popped " + ChatFormatting.WHITE + this.getTotemPops(player) + ChatFormatting.LIGHT_PURPLE + " totem";
                        return text;
                    }
                    case LEGACY: {
                         String text = ChatFormatting.WHITE + "[" + ChatFormatting.LIGHT_PURPLE + "Legacy" + ChatFormatting.WHITE + "] " + ChatFormatting.BOLD + player.getName() + ChatFormatting.LIGHT_PURPLE + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.LIGHT_PURPLE + " totem";
                        return text;
                        }
                    case KONAS: {
                        String text = ChatFormatting.DARK_GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "Konas" + ChatFormatting.DARK_GRAY + "] " + ChatFormatting.RED + player.getName() + ChatFormatting.WHITE + " popped " + ChatFormatting.DARK_PURPLE + this.getTotemPops(player) + ChatFormatting.WHITE + " totem";
                        return text;
                    }
                    case EUROPA: {
                         String text = ChatFormatting.GRAY + "[" + ChatFormatting.RED + "Europa" + ChatFormatting.GRAY + "] " + ChatFormatting.RESET + ChatFormatting.WHITE + player.getName() + ChatFormatting.LIGHT_PURPLE + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.DARK_PURPLE + " totem";
                        return text;
                    }
                    case PYRO: {
                        String text = ChatFormatting.DARK_RED + "[" + ChatFormatting.DARK_RED + "Pyro" + ChatFormatting.DARK_RED + "] " + ChatFormatting.RED + player.getName() + ChatFormatting.LIGHT_PURPLE + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.DARK_PURPLE + " totem";
                        return text;
                    }
                    case MUFFIN: {
                        String text = ChatFormatting.LIGHT_PURPLE + "[" + ChatFormatting.DARK_PURPLE + "Muffin" + ChatFormatting.LIGHT_PURPLE + "] " + ChatFormatting.LIGHT_PURPLE + player.getName() + ChatFormatting.DARK_PURPLE + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.DARK_PURPLE + " totem";
                        return text;
                    }
				    case ABYSS: {
                       String text = TextUtil.coloredString("[Abyss] ", ModuleTools.getInstance().abyssColor.getPlannedValue()) + ChatFormatting.WHITE + player.getName() + ChatFormatting.GREEN + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.GREEN + " totem";
                       return text;
                    }
				    case LUIGIHACK: {
                        String text = ChatFormatting.GREEN + ("[LuigiHack] ") + ChatFormatting.GRAY + player.getName() + ChatFormatting.GRAY + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.GRAY + " totem";
                        return text;
                    }
                    case NONE: {
                        return Cracked.commandManager.getClientMessage() + " " + ChatFormatting.WHITE + player.getName() + " popped " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.WHITE + " Totem.";
                    }
                }
            } else {
                return Cracked.commandManager.getClientMessage() + " " + ChatFormatting.WHITE + player.getName() + " popped " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.WHITE + " Totem.";
            }
        } else {
            if (ModuleTools.getInstance().isEnabled()) {
                switch (ModuleTools.getInstance().popNotifier.getValue()) {
                    case CRACKED: {
                        String text = (Cracked.commandManager.getClientMessage()) + " " + ChatFormatting.GREEN + player.getName() + ChatFormatting.GRAY + " popped " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.GRAY + " totem.";
                        return text;
                    }
                    case FUTURE: {
                        String text = ChatFormatting.RED + "[Future] " + ChatFormatting.GREEN + player.getName() + ChatFormatting.GRAY + " just popped " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.GRAY + " totems.";
                        return text;
                    }
                    case PHOBOS: {
                        String text = ChatFormatting.GOLD + player.getName() + ChatFormatting.RED + " popped " + ChatFormatting.GOLD + this.getTotemPops(player) + ChatFormatting.RED + " totems.";
                        return text;
                    }
                    case DOTGOD: {
                        String text = ChatFormatting.DARK_PURPLE + "[" + ChatFormatting.LIGHT_PURPLE + "DotGod.CC" + ChatFormatting.DARK_PURPLE + "] " + ChatFormatting.LIGHT_PURPLE + player.getName() + " has popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.LIGHT_PURPLE + " times in total!";
                        return text;
                    }
				    case SNOW: {
                        String text = ChatFormatting.BLUE + "[" + ChatFormatting.AQUA + "Snow" + ChatFormatting.BLUE + "] [" + ChatFormatting.DARK_AQUA + player.getName() + ChatFormatting.BLUE + "] " + " popped " + ChatFormatting.GOLD + this.getTotemPops(player) + ChatFormatting.BLUE + " totem";
                        return text;
                    }
                    case WEATHER: {
                        String text = ChatFormatting.AQUA + "[" + ChatFormatting.AQUA + "Weather" + ChatFormatting.AQUA + "] " + ChatFormatting.DARK_AQUA + player.getName() + ChatFormatting.WHITE + " popped " + ChatFormatting.RED +  this.getTotemPops(player) + ChatFormatting.WHITE + " totem";
                        return text;
                    }
                    case CATALYST: {
                        String text = ChatFormatting.DARK_GRAY + "[" + ChatFormatting.AQUA + "Catalyst" + ChatFormatting.DARK_GRAY + "] " + ChatFormatting.GRAY + player.getName() + ChatFormatting.LIGHT_PURPLE + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.WHITE + " totem";
                        return text;
                    }
                    case RUSHERHACK: {
                        String text = ChatFormatting.WHITE + "[" + ChatFormatting.GREEN + "rusherhack" + ChatFormatting.WHITE + "] " + ChatFormatting.GOLD + player.getName() + ChatFormatting.LIGHT_PURPLE + " popped " + ChatFormatting.WHITE + this.getTotemPops(player) + ChatFormatting.LIGHT_PURPLE + " totem";
                        return text;
                    }
                    case LEGACY: {
                         String text = ChatFormatting.WHITE + "[" + ChatFormatting.LIGHT_PURPLE + "Legacy" + ChatFormatting.WHITE + "] " + ChatFormatting.BOLD + player.getName() + ChatFormatting.LIGHT_PURPLE + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.LIGHT_PURPLE + " totem";
                        return text;
                        }
                    case KONAS: {
                        String text = ChatFormatting.DARK_GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "Konas" + ChatFormatting.DARK_GRAY + "] " + ChatFormatting.RED + player.getName() + ChatFormatting.WHITE + " popped " + ChatFormatting.DARK_PURPLE + this.getTotemPops(player) + ChatFormatting.WHITE + " totem";
                        return text;
                    }
                    case EUROPA: {
                         String text = ChatFormatting.GRAY + "[" + ChatFormatting.RED + "Europa" + ChatFormatting.GRAY + "] " + ChatFormatting.RESET + ChatFormatting.WHITE + player.getName() + ChatFormatting.LIGHT_PURPLE + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.DARK_PURPLE + " totem";
                        return text;
                    }
                    case PYRO: {
                        String text = ChatFormatting.DARK_RED + "[" + ChatFormatting.DARK_RED + "Pyro" + ChatFormatting.DARK_RED + "] " + ChatFormatting.RED + player.getName() + ChatFormatting.LIGHT_PURPLE + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.DARK_PURPLE + " totem";
                        return text;
                    }
                    case MUFFIN: {
                        String text = ChatFormatting.LIGHT_PURPLE + "[" + ChatFormatting.DARK_PURPLE + "Muffin" + ChatFormatting.LIGHT_PURPLE + "] " + ChatFormatting.LIGHT_PURPLE + player.getName() + ChatFormatting.DARK_PURPLE + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.DARK_PURPLE + " totem";
                        return text;
                    }
				    case ABYSS: {
                       String text = TextUtil.coloredString("[Abyss] ", ModuleTools.getInstance().abyssColor.getPlannedValue()) + ChatFormatting.WHITE + player.getName() + ChatFormatting.GREEN + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.GREEN + " totem";
                       return text;
                    }
				    case LUIGIHACK: {
                        String text = ChatFormatting.GREEN + ("[LuigiHack] ") + ChatFormatting.GRAY + player.getName() + ChatFormatting.GRAY + " popped " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.GRAY + " totem";
                        return text;
                    }
                    case NONE: {
                        return Cracked.commandManager.getClientMessage() + " " + ChatFormatting.WHITE + player.getName() + " popped " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.WHITE + " Totems.";
                    }
                }
            } else {
                return Cracked.commandManager.getClientMessage() + " " + ChatFormatting.WHITE + player.getName() + " popped " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.WHITE + " Totems.";
            }
        }
        return "";
    }


    public void onLogout() {
        this.onOwnLogout(this.notifications.clearOnLogout.getValue());
    }

    public void init() {
        this.notifications = Cracked.moduleManager.getModuleByClass(Notifications.class);
    }

    public void onTotemPop(EntityPlayer player) {
        this.popTotem(player);
        if (!player.equals(TotemPopManager.mc.player)) {
            this.toAnnounce.add(player);
            this.notifications.totemAnnounce.reset();
        }
    }

    public String death1(EntityPlayer player) {
        if (this.getTotemPops(player) == 1) {
            if (ModuleTools.getInstance().isEnabled()) {
                switch (ModuleTools.getInstance().popNotifier.getValue()) {
                    case CRACKED: {
                        String text = (Cracked.commandManager.getClientMessage()) + " " + ChatFormatting.GREEN + player.getName() + ChatFormatting.GRAY + " died after popping " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.GRAY + " totem.";
                        return text;
                    }
                    case FUTURE: {
                        String text = ChatFormatting.RED + "[Future] " + ChatFormatting.GREEN + player.getName() + ChatFormatting.GRAY + " died after popping " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.GRAY + " totem";
                        return text;
                    }
                    case PHOBOS: {
                        final String text = ChatFormatting.GOLD + player.getName() + ChatFormatting.RED + " died after popping " + ChatFormatting.GOLD + this.getTotemPops(player) + ChatFormatting.RED + " totems.";
                        return text;
                    }
                    case DOTGOD: {
                        String text = ChatFormatting.DARK_PURPLE + "[" + ChatFormatting.LIGHT_PURPLE + "DotGod.CC" + ChatFormatting.DARK_PURPLE + "] " + ChatFormatting.LIGHT_PURPLE + player.getName() + " died after popping " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.LIGHT_PURPLE + " time!";
                        return text;
                    }
				    case SNOW: {
                        String text = ChatFormatting.BLUE + "[" + ChatFormatting.AQUA + "Snow" + ChatFormatting.BLUE + "] [" + ChatFormatting.DARK_AQUA + player.getName() + ChatFormatting.BLUE + "] " + " died after popping " + ChatFormatting.GOLD + this.getTotemPops(player) + ChatFormatting.BLUE + " totem";
                        return text;
                    }
                    case WEATHER: {
                        String text = ChatFormatting.AQUA + "[" + ChatFormatting.AQUA + "Weather" + ChatFormatting.AQUA + "] " + ChatFormatting.DARK_AQUA + player.getName() + ChatFormatting.WHITE + " died after popping " + ChatFormatting.RED +  this.getTotemPops(player) + ChatFormatting.WHITE + " totem";
                        return text;
                    }
                    case CATALYST: {
                        String text = ChatFormatting.DARK_GRAY + "[" + ChatFormatting.AQUA + "Catalyst" + ChatFormatting.DARK_GRAY + "] " + ChatFormatting.GRAY + player.getName() + ChatFormatting.LIGHT_PURPLE + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.WHITE + " totem";
                        return text;
                    }
                    case RUSHERHACK: {
                        String text = ChatFormatting.WHITE + "[" + ChatFormatting.GREEN + "rusherhack" + ChatFormatting.WHITE + "] " + ChatFormatting.GOLD + player.getName() + ChatFormatting.LIGHT_PURPLE + " died after popping " + ChatFormatting.WHITE + this.getTotemPops(player) + ChatFormatting.LIGHT_PURPLE + " totem";
                        return text;
                    }
                    case LEGACY: {
                         String text = ChatFormatting.WHITE + "[" + ChatFormatting.LIGHT_PURPLE + "Legacy" + ChatFormatting.WHITE + "] " + ChatFormatting.BOLD + player.getName() + ChatFormatting.LIGHT_PURPLE + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.LIGHT_PURPLE + " totem";
                        return text;
                        }
                    case KONAS: {
                        String text = ChatFormatting.DARK_GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "Konas" + ChatFormatting.DARK_GRAY + "] " + ChatFormatting.RED + player.getName() + ChatFormatting.WHITE + " died after popping " + ChatFormatting.DARK_PURPLE + this.getTotemPops(player) + ChatFormatting.WHITE + " totem";
                        return text;
                    }
                    case EUROPA: {
                         String text = ChatFormatting.GRAY + "[" + ChatFormatting.RED + "Europa" + ChatFormatting.GRAY + "] " + ChatFormatting.RESET + ChatFormatting.WHITE + player.getName() + ChatFormatting.LIGHT_PURPLE + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.DARK_PURPLE + " totem";
                        return text;
                    }
                    case PYRO: {
                        String text = ChatFormatting.DARK_RED + "[" + ChatFormatting.DARK_RED + "Pyro" + ChatFormatting.DARK_RED + "] " + ChatFormatting.RED + player.getName() + ChatFormatting.LIGHT_PURPLE + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.DARK_PURPLE + " totem";
                        return text;
                    }
                    case MUFFIN: {
                        String text = ChatFormatting.LIGHT_PURPLE + "[" + ChatFormatting.DARK_PURPLE + "Muffin" + ChatFormatting.LIGHT_PURPLE + "] " + ChatFormatting.LIGHT_PURPLE + player.getName() + ChatFormatting.DARK_PURPLE + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.DARK_PURPLE + " totem";
                        return text;
                    }
				    case ABYSS: {
                       String text = TextUtil.coloredString("[Abyss] ", ModuleTools.getInstance().abyssColor.getPlannedValue()) + ChatFormatting.WHITE + player.getName() + ChatFormatting.GREEN + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.GREEN + " totem";
                       return text;
                    }
				    case LUIGIHACK: {
                        String text = ChatFormatting.GREEN + ("[LuigiHack] ") + ChatFormatting.GRAY + player.getName() + ChatFormatting.GRAY + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.GRAY + " totem";
                        return text;
                    }
                    case NONE: {
                        return Cracked.commandManager.getClientMessage() + " " + ChatFormatting.WHITE + player.getName() + " died after popping " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.WHITE + " Totem!";

                    }
                }
            } else {
                return Cracked.commandManager.getClientMessage() + ChatFormatting.WHITE + player.getName() + " died after popping " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.WHITE + " Totem!";

            }
        } else {
            if (ModuleTools.getInstance().isEnabled()) {
                switch (ModuleTools.getInstance().popNotifier.getValue()) {
                    case CRACKED: {
                        String text = (Cracked.commandManager.getClientMessage()) + " " + ChatFormatting.GREEN + player.getName() + ChatFormatting.GRAY + " died after popping " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.GRAY + " totem.";
                        return text;
                    }
                    case FUTURE: {
                        String text = ChatFormatting.RED + "[Future] " + ChatFormatting.GREEN + player.getName() + ChatFormatting.GRAY + " died after popping " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.GRAY + " totems.";
                        return text;
                    }
                    case PHOBOS: {
                        String text = ChatFormatting.GOLD + player.getName() + ChatFormatting.RED + " died after popping " + ChatFormatting.GOLD + this.getTotemPops(player) + ChatFormatting.RED + " totems.";
                        return text;
                    }
                    case DOTGOD: {
                        String text = ChatFormatting.DARK_PURPLE + "[" + ChatFormatting.LIGHT_PURPLE + "DotGod.CC" + ChatFormatting.DARK_PURPLE + "] " + ChatFormatting.LIGHT_PURPLE + player.getName() + " died after popping " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.LIGHT_PURPLE + " times!";
                        return text;
                    }
				    case SNOW: {
                        String text = ChatFormatting.BLUE + "[" + ChatFormatting.AQUA + "Snow" + ChatFormatting.BLUE + "] [" + ChatFormatting.DARK_AQUA + player.getName() + ChatFormatting.BLUE + "] " + " died after popping " + ChatFormatting.GOLD + this.getTotemPops(player) + ChatFormatting.BLUE + " totem";
                        return text;
                    }
                    case WEATHER: {
                        String text = ChatFormatting.AQUA + "[" + ChatFormatting.AQUA + "Weather" + ChatFormatting.AQUA + "] " + ChatFormatting.DARK_AQUA + player.getName() + ChatFormatting.WHITE + " died after popping " + ChatFormatting.RED +  this.getTotemPops(player) + ChatFormatting.WHITE + " totem";
                        return text;
                    }
                    case CATALYST: {
                        String text = ChatFormatting.DARK_GRAY + "[" + ChatFormatting.AQUA + "Catalyst" + ChatFormatting.DARK_GRAY + "] " + ChatFormatting.GRAY + player.getName() + ChatFormatting.LIGHT_PURPLE + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.WHITE + " totem";
                        return text;
                    }
                    case RUSHERHACK: {
                        String text = ChatFormatting.WHITE + "[" + ChatFormatting.GREEN + "rusherhack" + ChatFormatting.WHITE + "] " + ChatFormatting.GOLD + player.getName() + ChatFormatting.LIGHT_PURPLE + " died after popping " + ChatFormatting.WHITE + this.getTotemPops(player) + ChatFormatting.LIGHT_PURPLE + " totem";
                        return text;
                    }
                    case LEGACY: {
                         String text = ChatFormatting.WHITE + "[" + ChatFormatting.LIGHT_PURPLE + "Legacy" + ChatFormatting.WHITE + "] " + ChatFormatting.BOLD + player.getName() + ChatFormatting.LIGHT_PURPLE + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.LIGHT_PURPLE + " totem";
                        return text;
                        }
                    case KONAS: {
                        String text = ChatFormatting.DARK_GRAY + "[" + ChatFormatting.LIGHT_PURPLE + "Konas" + ChatFormatting.DARK_GRAY + "] " + ChatFormatting.RED + player.getName() + ChatFormatting.WHITE + " died after popping " + ChatFormatting.DARK_PURPLE + this.getTotemPops(player) + ChatFormatting.WHITE + " totem";
                        return text;
                    }
                    case EUROPA: {
                         String text = ChatFormatting.GRAY + "[" + ChatFormatting.RED + "Europa" + ChatFormatting.GRAY + "] " + ChatFormatting.RESET + ChatFormatting.WHITE + player.getName() + ChatFormatting.LIGHT_PURPLE + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.DARK_PURPLE + " totem";
                        return text;
                    }
                    case PYRO: {
                        String text = ChatFormatting.DARK_RED + "[" + ChatFormatting.DARK_RED + "Pyro" + ChatFormatting.DARK_RED + "] " + ChatFormatting.RED + player.getName() + ChatFormatting.LIGHT_PURPLE + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.DARK_PURPLE + " totem";
                        return text;
                    }
                    case MUFFIN: {
                        String text = ChatFormatting.LIGHT_PURPLE + "[" + ChatFormatting.DARK_PURPLE + "Muffin" + ChatFormatting.LIGHT_PURPLE + "] " + ChatFormatting.LIGHT_PURPLE + player.getName() + ChatFormatting.DARK_PURPLE + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.DARK_PURPLE + " totem";
                        return text;
                    }
				    case ABYSS: {
                       String text = TextUtil.coloredString("[Abyss] ", ModuleTools.getInstance().abyssColor.getPlannedValue()) + ChatFormatting.WHITE + player.getName() + ChatFormatting.GREEN + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.GREEN + " totem";
                       return text;
                    }
				    case LUIGIHACK: {
                        String text = ChatFormatting.GREEN + ("[LuigiHack] ") + ChatFormatting.GRAY + player.getName() + ChatFormatting.GRAY + " died after popping " + ChatFormatting.RED + this.getTotemPops(player) + ChatFormatting.GRAY + " totem";
                        return text;
                    }
                    case NONE: {
                        return Cracked.commandManager.getClientMessage() + " " + ChatFormatting.WHITE + player.getName() + " died after popping " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.WHITE + " Totems!";

                    }
                }
            } else {
                return Cracked.commandManager.getClientMessage() + " " + ChatFormatting.WHITE + player.getName() + " died after popping " + ChatFormatting.GREEN + this.getTotemPops(player) + ChatFormatting.WHITE + " Totems!";
            }
        }
        return null;
    }


    public void onDeath(EntityPlayer player) {
        if (this.getTotemPops(player) != 0 && !player.equals(TotemPopManager.mc.player) && this.notifications.isOn() && this.notifications.totemPops.getValue()) {
            int playerNumber = 0;
            for (char character : player.getName().toCharArray()) {
                playerNumber += character;
                playerNumber *= 10;
            }
            Command.sendOverwriteMessage(this.death1(player), playerNumber, this.notifications.totemNoti.getValue());
            this.toAnnounce.remove(player);
        }
        this.resetPops(player);
    }

    public void onLogout(EntityPlayer player, boolean clearOnLogout) {
        if (clearOnLogout) {
            this.resetPops(player);
        }
    }

    public void onOwnLogout(boolean clearOnLogout) {
        if (clearOnLogout) {
            this.clearList();
        }
    }

    public void clearList() {
        this.poplist = new ConcurrentHashMap<>();
    }

    public void resetPops(EntityPlayer player) {
        this.setTotemPops(player, 0);
    }

    public void popTotem(EntityPlayer player) {
        this.poplist.merge(player, 1, Integer::sum);
    }

    public void setTotemPops(EntityPlayer player, int amount) {
        this.poplist.put(player, amount);
    }

    public int getTotemPops(EntityPlayer player) {
        Integer pops = this.poplist.get(player);
        if (pops == null) {
            return 0;
        }
        return pops;
    }

    public String getTotemPopString(EntityPlayer player) {
        return "\u00a7f" + (this.getTotemPops(player) <= 0 ? "" : "-" + this.getTotemPops(player) + " ");
    }
}
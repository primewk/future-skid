package me.akaishin.cracked.features.modules.misc;

import me.akaishin.cracked.*;
import me.akaishin.cracked.features.modules.Module;
import me.akaishin.cracked.features.setting.Setting;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.client.network.NetworkPlayerInfo;


public class TabFriends extends Module
{
    public static String color;
    public static TabFriends INSTANCE;
    public static Setting<Boolean> prefix;
    public Setting<FriendColor> mode;

    public TabFriends() {
        super("TabFriends", "Tab Friends GoD", Module.Category.MISC, true, false, false);
        this.mode = this.register(new Setting("Color", FriendColor.Green));
        TabFriends.prefix = this.register(new Setting("Prefix", true));
        TabFriends.INSTANCE = this;
    }

    public static String getPlayerName(final NetworkPlayerInfo networkPlayerInfoIn) {
        final String name = (networkPlayerInfoIn.getDisplayName() != null) ? networkPlayerInfoIn.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName(networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
        if (!Cracked.friendManager.isFriend(name)) {
            return name;
        }
        if (TabFriends.prefix.getValue()) {
            return "\u00a77[" + TabFriends.color + "Friend\u00a77] " + TabFriends.color + name;
        }
        return TabFriends.color + name;
    }

    @Override
    public void onUpdate() {
        switch (this.mode.getValue()) {
            case DarkRed: {
                TabFriends.color = "\u00a74";
                break;
            }
            case Red: {
                TabFriends.color = "\u00a7c";
                break;
            }
            case Gold: {
                TabFriends.color = "\u00a76";
                break;
            }
            case Yellow: {
                TabFriends.color = "\u00a7e";
                break;
            }
            case DarkGreen: {
                TabFriends.color = "\u00a72";
                break;
            }
            case Green: {
                TabFriends.color = "\u00a7a";
                break;
            }
            case Aqua: {
                TabFriends.color = "\u00a7b";
                break;
            }
            case DarkAqua: {
                TabFriends.color = "\u00a73";
                break;
            }
            case DarkBlue: {
                TabFriends.color = "\u00a71";
                break;
            }
            case Blue: {
                TabFriends.color = "\u00a79";
                break;
            }
            case LightPurple: {
                TabFriends.color = "\u00a7d";
                break;
            }
            case DarkPurple: {
                TabFriends.color = "\u00a75";
                break;
            }
            case Gray: {
                TabFriends.color = "\u00a77";
                break;
            }
            case DarkGray: {
                TabFriends.color = "\u00a78";
                break;
            }
            case Black: {
                TabFriends.color = "\u00a70";
                break;
            }
            case None: {
                TabFriends.color = "";
                break;
            }
        }
    }

    static {
        TabFriends.color = "";
    }

    private enum FriendColor
    {
        DarkRed,
        Red,
        Gold,
        Yellow,
        DarkGreen,
        Green,
        Aqua,
        DarkAqua,
        DarkBlue,
        Blue,
        LightPurple,
        DarkPurple,
        Gray,
        DarkGray,
        Black,
        None
    }
}

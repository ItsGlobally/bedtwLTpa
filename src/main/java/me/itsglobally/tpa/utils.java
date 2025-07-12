package me.itsglobally.tpa;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class utils {
    private static final HashMap<Player, Player> tpaDb = new HashMap<>();

    private static final List<String> displayNameList = new ArrayList<>();

    public static void chat(Player p, String m) {
        p.sendMessage(m);
    }

    public static void chat(CommandSender p, String m) {
        p.sendMessage(m);
    }

    public static void chat(Player p, Player ap, String m) {
        p.sendMessage(m);
        ap.sendMessage(m);
    }
    public static void log(String m) {
        Bukkit.getLogger().info(m);
    }
    public static void WnEr(String m) {
        Bukkit.getLogger().warning(m);
    }
    public static void setTpaTg(Player p, Player tg) {
        tpaDb.put(p, tg);
    }
    public static void remTpaTg(Player p) {
        tpaDb.remove(p);
    }
    public static Player getTpaTg(Player p) {
        return tpaDb.getOrDefault(p, null);
    }
    @Nullable
    public static Player getPlayerByDisplayName(String n) {
        Player fP = null;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getDisplayName().equals(n)) {
                fP = p;
            }
        }
        return fP;
    }
    public static List<String> getOnlinePlayerDisplayName() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            displayNameList.add(p.getDisplayName());
        }
    }
}

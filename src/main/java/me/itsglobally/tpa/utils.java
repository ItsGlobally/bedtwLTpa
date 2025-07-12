package me.itsglobally.tpa;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class utils {
    private static final HashMap<Player, Player> tpaDb = new HashMap<>();

    private static final HashMap<Player, List<Player>> tpaBlDb= new HashMap<>();

    private static final HashMap<Player, List<Player>> tpaWlDb= new HashMap<>();

    public static void chat(Player p, String m) {
        p.sendMessage(m);
    }

    public static void chat(CommandSender p, String m) {
        p.sendMessage(m);
    }

    public static void chat(Player p, TextComponent m) {
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
    public static List<Player> getTpaBl(Player p) { return tpaBlDb.getOrDefault(p, null); }
    public static void addTpaBl(Player p, Player tg) {
        List<Player> current = getTpaBl(p);
        if (current.contains(tg)) { return; }
        current.add(tg);
        tpaBlDb.put(p, current);
    }
    public static void remTpaBl(Player p, Player tg) {
        List<Player> current = getTpaBl(p);
        if (!current.contains(tg)) { return; }
        current.remove(tg);
        tpaBlDb.put(p, current);
    }
    public static List<Player> getTpaWl(Player p) { return tpaWlDb.getOrDefault(p, null); }
    public static void addTpaWl(Player p, Player tg) {
        List<Player> current = getTpaWl(p);
        if (current.contains(tg)) { return; }
        current.add(tg);
        tpaBlDb.put(p, current);
    }
    public static void remTpaWl(Player p, Player tg) {
        List<Player> current = getTpaWl(p);
        if (!current.contains(tg)) { return; }
        current.remove(tg);
        tpaBlDb.put(p, current);
    }
    private static final HashMap<Player, Boolean> tpYesOrNo = new HashMap<>();
    public static void tpyes(Player p) {
        tpYesOrNo.put(p, true);
    }
    public static void tpno(Player p) {
        tpYesOrNo.put(p, false);
    }
    public static boolean getTpYesOrNo(Player p) {
        return tpYesOrNo.getOrDefault(p, true);
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
        List<String> displayNameList = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            displayNameList.add(p.getDisplayName());
        }
        return displayNameList;
    }
}

package me.itsglobally.tpa;

import me.bedtwL.ffa.api.PureAPI;
import me.bedtwL.ffa.api.utils.ILanguageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class mainCommand implements CommandExecutor {

    private static JavaPlugin plugin;

    @Deprecated // functions not good
    private static final ILanguageUtils langapi = PureAPI.getLanguageUtils();

    public mainCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        // s = cmd
        // strings = args
        if (!(commandSender instanceof Player p)) {
            utils.chat(commandSender, "gay");
            return true;
        }
        Player player = null;
        /* switch (s) {
            case "tpa":
            case "tpaccept":
            case "tpdeny":
            case "tpyes":
            case "tpno":

                break;

                if (utils.getPlayerByDisplayName(strings[0]) != null) {
                    player = utils.getPlayerByDisplayName(strings[0]);
                }
                break;
        } */
        if (utils.getPlayerByDisplayName(strings[0]) != null) {
            player = utils.getPlayerByDisplayName(strings[0]);
        }
        if (player == null) {
            utils.chat(p, "No players found");
            return true;
        }

        switch (s) {
            case "tpa" -> {
                tpa(p, player);
                return true;
            }
            case "tpaccept" -> {
                tpaccept(p, player);
                return true;
            }
            case "tpdeny" -> {
                tpdeny(p, player);
                return true;
            }
            case "tpyes" -> {
                tpyes(p, player);
                return true;
            }
            case "tpno" -> {
                tpno(p, player);
                return true;
            }
            case "tpwhitelist" -> {
                tpwhitelist(p, player);
                return true;
            }
        }
        return true;
    }
    private static void tpa(Player p, Player tg) {
        if (utils.getTpaTg(p) != tg) {
            utils.chat(p, "You already have a request to " + tg.getDisplayName());
            return;
        }
        utils.setTpaTg(p, tg);
        utils.chat(p, "Sent request to " + tg.getDisplayName());
        utils.chat(tg, p.getName() + " wants to teleport to you!\nAccept or deny by using");
        utils.chat(tg, "/tpaccept " + p.getDisplayName());
        utils.chat(tg, "/tpdeny " + p.getDisplayName());
        new BukkitRunnable() {
            @Override
            public void run(){
                if (utils.getTpaTg(p) == null) {
                    return;
                }
                utils.remTpaTg(p);
            } // nb
        }.runTaskLater(plugin,6000L); // so this done?(whole plugin shit u missed some stuff
    }
    private static void tpaccept(Player p, Player tg) {
        if (utils.getTpaTg(p) != tg) {
            utils.chat(p, "Player not found or this player or the player did not send you a tpa request!");
            return;
        }
        if (tg == null) {
            utils.chat(p, "Player logged off.");
            return;
        }
        tg.teleport(p.getLocation());
        utils.chat(p, tg, "Telepored!");
        utils.remTpaTg(p);
    }
    private static void tpdeny(Player p, Player tg) {
        if (utils.getTpaTg(tg) != p) {
            utils.chat(p, "Player not found or this player or the player did not send you a tpa request!");
            return;
        }
        utils.chat(p, "Denied " + tg.getDisplayName() + "'s teleport request!");
        utils.chat(tg, tg.getDisplayName() + " denied your teleport request!");
    }
    private static void tpyes(Player p, Player tg) {
        utils.chat(p, "function not done");
    }
    private static void tpno(Player p, Player tg) {
        utils.chat(p, "function not done");
    }
    private static void tpwhitelist(Player p, Player tg) {
        utils.chat(p, "function not done");
    }
}

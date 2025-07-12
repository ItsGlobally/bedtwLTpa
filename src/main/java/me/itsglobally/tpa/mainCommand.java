package me.itsglobally.tpa;

import me.bedtwL.ffa.api.PureAPI;
import me.bedtwL.ffa.api.utils.ILanguageUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class mainCommand implements CommandExecutor, TabCompleter {

    private static JavaPlugin plugin;

    private static ILanguageUtils langapi;

    public mainCommand(JavaPlugin plugin) {
        mainCommand.plugin = plugin;
        mainCommand.langapi=PureAPI.getLanguageUtils();
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        // s = cmd
        // strings = args
        if (!(commandSender instanceof Player p)) {
            // utils.chat(commandSender, "gay");
            langapi.tellMsg(commandSender, "cmd.tpa.not-player");
            return true;
        }
        Player player = null;
        if (utils.getPlayerByDisplayName(strings[0]) != null) {
            player = utils.getPlayerByDisplayName(strings[0]);
        }
        if (player == null) {
            // utils.chat(p, "No players found");
            langapi.tellMsg(p, "cmd.tpa.no-player"); // donne
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
            case "tpcancel" -> {
                tpcancel(p, player);
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
            case "tpblacklist" -> {
                tpblacklist(p, player);
                return true;
            }
        }
        return true;
    }
    private static void tpa(Player p, Player tg) {
        boolean executeAbleWl = true;
        if (utils.getTpaTg(p) == tg) {
            // utils.chat(p, "You already have a request to " + tg.getDisplayName());
            utils.chat(p, langapi.getMsg(p.getUniqueId(), "cmd.tpa.already-rq").replace("{tg}", tg.getDisplayName()));
            return;
        }
        if (utils.getTpaBl(tg).contains(p)) {
            langapi.tellMsg(p, "cmd.tpa.get-blocked");
            return;
        }
        if (!utils.getTpYesOrNo(tg)) {
            executeAbleWl = utils.getTpaWl(p).contains(tg);
        }
        if (!executeAbleWl) {
            langapi.tellMsg(p, "cmd.tpa.tg-disabled-rq");
            return;
        }
        TextComponent accept = Component.text("[Y] ").clickEvent(ClickEvent.runCommand("/tpaccept " + p.getDisplayName()));
        TextComponent deny = Component.text("[N] ").clickEvent(ClickEvent.runCommand("/tpdeny" + p.getDisplayName()));
        utils.setTpaTg(p, tg);
        //utils.chat(p, "Sent request to " + tg.getDisplayName());
        utils.chat(p, langapi.getMsg(p.getUniqueId(), "cmd.tpa.sent-req").replace("{tg}", tg.getDisplayName()));
        //utils.chat(tg, p.getDisplayName() + " wants to teleport to you!\nAccept or deny by using");
        utils.chat(tg, langapi.getMsg(tg.getUniqueId(), "cmd.tpa.get-req").replace("{p}", p.getDisplayName()));
        utils.chat(tg, accept.append(deny));
        new BukkitRunnable() {
            @Override
            public void run(){
                if (utils.getTpaTg(p) == null) {
                    return;
                }
                utils.remTpaTg(p);
            }
        }.runTaskLater(plugin,6000L);
    }
    private static void tpaccept(Player p, Player tg) {
        if (utils.getTpaTg(tg) != p) {
            //utils.chat(p, "Player not found or this player or the player did not send you a tpa request!");
            langapi.tellMsg(p, "cmd.tpa.player-didnt-rq");
            return;
        }
        if (tg == null) {
            //utils.chat(p, "Player logged off.");
            langapi.tellMsg(p, "cmd.tpa.player-logged-off");
            return;
        }
        tg.teleport(p.getLocation());
        //utils.chat(p, tg, "Telepored!");
        langapi.tellMsg(p, "cmd.tpa.telepored");
        langapi.tellMsg(tg, "cmd.tpa.telepored");
        utils.remTpaTg(p);
    }
    private static void tpdeny(Player p, Player tg) {
        if (utils.getTpaTg(tg) != p) {
            // utils.chat(p, "Player not found or this player or the player did not send you a tpa request!");
            langapi.tellMsg(p, "cmd.tpa.player-didnt-rq");
            return;
        }
        //utils.chat(p, "Denied " + tg.getDisplayName() + "'s teleport request!");
        utils.chat(p, langapi.getMsg(p.getUniqueId(), "cmd.tpa.denied-rq").replace("{tg}", tg.getDisplayName()));
        utils.chat(tg, langapi.getMsg(tg.getUniqueId(), "cmd.tpa.get-denied").replace("{p}", p.getDisplayName()));
        //utils.chat(tg, p.getDisplayName() + " denied your teleport request!");
    }
    private static void tpcancel(Player p, Player tg) {
        if (utils.getTpaTg(p) != tg) {
            // You didn't send a tpa request to this player!
            langapi.tellMsg(p, "cmd.tpa.u-didnt-rq");
            return;
        }
        utils.remTpaTg(p);
    }
    private static void tpyes(Player p, Player tg) {
        utils.tpyes(p);
        langapi.tellMsg(p, "cmd.tpa.tpyes");
    }
    private static void tpno(Player p, Player tg) {
        utils.tpno(p);
        langapi.tellMsg(p, "cmd.tpa.tpno");
    }
    private static void tpwhitelist(Player p, Player tg) {
        if (utils.getTpaWl(p).contains(tg)) {
            langapi.tellMsg(p, "cmd.tpa.already-in-wl");
            return;
        }
        utils.addTpaWl(p, tg);
        langapi.tellMsg(p, "cmd.tpa.add-wl-success");
    }
    private static void tpblacklist(Player p, Player tg) {
        if (utils.getTpaBl(p).contains(tg)) {
            langapi.tellMsg(p, "cmd.tpa.already-in-bl");
            return;
        }
        utils.addTpaBl(p, tg);
        // lang api: bro tmd need use cmd.tpa.<ur code>
        langapi.tellMsg(p, "cmd.tpa.add-bl-success");
    }
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String @NotNull [] args) {
        switch (alias) {
            case "tpa", "tpaccept", "tpdeny" -> {
                return utils.getOnlinePlayerDisplayName();
            }
            case "tpwhilelist", "tpblacklist" -> {
                if (args.length == 1) {
                    return List.of("add", "remove");
                } else if (args.length == 2) {
                    return utils.getOnlinePlayerDisplayName();
                } else {
                    return Collections.singletonList("This_cmd_only_requires_2_args");
                }
            }
            case "tpyes","tpno" -> {
                return List.of("true", "false");
            }

        }
        return List.of();
    }

}

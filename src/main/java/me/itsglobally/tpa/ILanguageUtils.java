package me.itsglobally.tpa;

import me.bedtwL.ffa.api.language.PlayerLanguage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class ILanguageUtils implements me.bedtwL.ffa.api.utils.ILanguageUtils {
    @Override
    public String getMsg(UUID uuid, String s) {
        return s;
    }

    @Override
    public List<String> getListMsg(UUID uuid, String s) {
        return List.of();
    }

    @Override
    public void tellMsg(Player player, String s) {
        utils.chat(player, s);
    }

    @Override
    public void tellMsg(CommandSender commandSender, String s) {
        utils.chat(commandSender, s);
    }

    @Override
    public PlayerLanguage getPlayerLanguage(UUID uuid) {
        return PlayerLanguage.Taiwan;
    }
}

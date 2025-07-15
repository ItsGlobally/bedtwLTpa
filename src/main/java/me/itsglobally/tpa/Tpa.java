package me.itsglobally.tpa;

import me.bedtwL.ffa.api.PureAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class Tpa extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("tpa").setExecutor(new mainCommand());
        getCommand("tpa").setTabCompleter(new mainCommand());
        getLogger().info("Enabled!");
        PureAPI.setInstance(this);
        PureAPI.setLanguageUtils(new ILanguageUtils());
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled!");
    }
}

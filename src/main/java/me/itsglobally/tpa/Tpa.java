package me.itsglobally.tpa;

import org.bukkit.plugin.java.JavaPlugin;

public final class Tpa extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("tpa").setExecutor(new mainCommand(this));
        getCommand("tpa").setTabCompleter(new mainCommand(this));
        getLogger().info("Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled!");
    }
}

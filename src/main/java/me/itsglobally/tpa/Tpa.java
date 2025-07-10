package me.itsglobally.tpa;

import org.bukkit.plugin.java.JavaPlugin;

public final class Tpa extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("tpa").setExecutor(new mainCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

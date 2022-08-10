package me.znoah.tinyreports;

import me.znoah.tinyreports.loader.SpigotLoader;

import org.bukkit.plugin.java.JavaPlugin;

public class SpigotTReports extends JavaPlugin {
    private final SpigotLoader loader = new SpigotLoader(this);

    @Override
    public void onEnable() {
        loader.load();
    }

    @Override
    public void onDisable() {
        loader.unload();
    }
}

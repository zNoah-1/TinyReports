package me.znoah.tinyreports.loader;

import me.znoah.tinyreports.SpigotTReports;
import me.znoah.tinyreports.command.report.ReportCommand;
import me.znoah.tinyreports.command.report.ReportTabCompleter;
import me.znoah.tinyreports.config.SpigotConfig;
import me.znoah.tinyreports.listener.JoinQuitListener;
import me.znoah.tinyreports.report.ReportRegister;
import me.znoah.tinyreports.user.staff.StaffRegistry;

import org.bukkit.Bukkit;

public class SpigotLoader implements Loader {
    private final SpigotTReports plugin;
    private ConfigLoader configLoader;

    public SpigotLoader(SpigotTReports plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        loadConfig();
        if (configLoader.isFailed()) {
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }

        StaffRegistry staffRegistry = new StaffRegistry();
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(staffRegistry), plugin);

        plugin.getCommand("report").setExecutor(
                new ReportCommand(
                        new ReportRegister(staffRegistry, configLoader.getConfig("messages.yml")),
                        configLoader.getConfig("messages.yml"),
                        configLoader.getConfig("config.yml")
                )
        );
        plugin.getCommand("report").setTabCompleter(new ReportTabCompleter());
    }

    @Override
    public void reload() {
        configLoader.reload();
    }

    @Override
    public void unload() {
        //Empty
    }

    private void loadConfig(){
        this.configLoader = new ConfigLoader(
                new SpigotConfig("config.yml"),
                new SpigotConfig("messages.yml")
        );
        configLoader.load();
    }
}

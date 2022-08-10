package me.znoah.tinyreports.loader;

import me.znoah.tinyreports.SpigotTReports;
import me.znoah.tinyreports.command.report.ReportCommand;
import me.znoah.tinyreports.command.report.ReportTabCompleter;
import me.znoah.tinyreports.listener.JoinQuitListener;
import me.znoah.tinyreports.report.ReportRegister;
import me.znoah.tinyreports.user.staff.StaffRegistry;

import org.bukkit.Bukkit;

public class SpigotLoader implements Loader {
    private final SpigotTReports plugin;

    public SpigotLoader(SpigotTReports plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        StaffRegistry staffRegistry = new StaffRegistry();
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(staffRegistry), plugin);

        plugin.getCommand("report").setExecutor(new ReportCommand(new ReportRegister(staffRegistry)));
        plugin.getCommand("report").setTabCompleter(new ReportTabCompleter());
    }

    @Override
    public void reload() {

    }

    @Override
    public void unload() {

    }
}

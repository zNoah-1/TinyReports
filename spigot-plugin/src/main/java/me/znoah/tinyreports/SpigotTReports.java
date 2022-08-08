package me.znoah.tinyreports;

import me.znoah.tinyreports.command.report.ReportTabCompleter;
import me.znoah.tinyreports.command.report.ReportCommand;
import me.znoah.tinyreports.listener.JoinQuitListener;
import me.znoah.tinyreports.report.ReportRegister;
import me.znoah.tinyreports.user.staff.StaffRegistry;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotTReports extends JavaPlugin {
    @Override
    public void onEnable() {
        StaffRegistry staffRegistry = new StaffRegistry();
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(staffRegistry), this);
        this.getCommand("report").setExecutor(new ReportCommand(new ReportRegister(staffRegistry)));
        this.getCommand("report").setTabCompleter(new ReportTabCompleter());
    }

    @Override
    public void onDisable() {

    }
}

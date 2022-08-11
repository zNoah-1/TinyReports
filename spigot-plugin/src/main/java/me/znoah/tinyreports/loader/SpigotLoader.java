/*
    TinyReports (spigot-plugin) - The spigot implementation of TinyReports (core)
    Copyright (C) 2022 zNoah-1

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

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
        loadListeners(staffRegistry);
        loadCommands(staffRegistry);
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

    private void loadListeners(StaffRegistry staffRegistry){
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(staffRegistry), plugin);
    }

    private void loadCommands(StaffRegistry staffRegistry){
        plugin.getCommand("report").setExecutor(
                new ReportCommand(
                        new ReportRegister(
                                staffRegistry,
                                configLoader.getConfig("messages.yml"),
                                configLoader.getConfig("discord.yml")),
                        configLoader.getConfig("messages.yml"),
                        configLoader.getConfig("config.yml")
                )
        );
        plugin.getCommand("report").setTabCompleter(new ReportTabCompleter());
    }
}

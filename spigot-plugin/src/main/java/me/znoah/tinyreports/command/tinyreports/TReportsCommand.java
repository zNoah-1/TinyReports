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

package me.znoah.tinyreports.command.tinyreports;

import me.znoah.tinyreports.command.SubCommand;
import me.znoah.tinyreports.command.tinyreports.reload.TReportsReloadCommand;
import me.znoah.tinyreports.config.Config;
import me.znoah.tinyreports.loader.Loader;
import me.znoah.tinyreports.util.chat.ColorUtil;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class TReportsCommand implements CommandExecutor {
    private final Map<String, SubCommand> subCommands = new HashMap<>();
    private final Config msgConfig;

    public TReportsCommand(Loader loader, Config msgConfig) {
        this.msgConfig = msgConfig;
        subCommands.put("reload", new TReportsReloadCommand(loader, msgConfig));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("tinyreports.op")){
            if (args.length == 0) sendHelpMessage(sender);
            else {
                String subCommandName = args[0];
                SubCommand subCommand = subCommands.get(subCommandName);

                if (subCommand == null) {
                    sendHelpMessage(sender);
                }
                else {
                    subCommand.run(sender, args);
                }
            }
        }
        else {
            sendNoPermissionMessage(sender);
        }
        return true;
    }

    private void sendHelpMessage(CommandSender sender){
        msgConfig.getStringList("commands.tinyreports.help")
                .forEach(line -> sender.sendMessage(ColorUtil.format(line)));
    }

    private void sendNoPermissionMessage(CommandSender sender){
        msgConfig.getStringList("commands.tinyreports.no-permission")
                .forEach(line -> sender.sendMessage(ColorUtil.format(line)));
    }
}

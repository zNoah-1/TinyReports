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

package me.znoah.tinyreports.command.tinyreports.reload;

import me.znoah.tinyreports.command.SubCommand;
import me.znoah.tinyreports.config.Config;
import me.znoah.tinyreports.loader.ConfigLoader;
import me.znoah.tinyreports.loader.Loader;
import me.znoah.tinyreports.util.chat.ColorUtil;

import org.bukkit.command.CommandSender;

public class TReportsReloadCommand implements SubCommand {
    private final Loader loader;
    private final Config msgConfig;

    public TReportsReloadCommand(Loader loader, Config msgConfig) {
        this.loader = loader;
        this.msgConfig = msgConfig;
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender.hasPermission("tinyreports.op")){
            loader.reload();
            if (((ConfigLoader) loader).isFailed()){
                sendErrorMessage(sender);
            }
            else {
                sendSuccessMessage(sender);
            }
        }
        else {
            sendNoPermissionMessage(sender);
        }
    }

    private void sendErrorMessage(CommandSender sender){
        sender.sendMessage(ColorUtil.format((String) msgConfig.get("commands.tinyreports.reload.error")));
    }

    private void sendSuccessMessage(CommandSender sender){
        sender.sendMessage(ColorUtil.format((String) msgConfig.get("commands.tinyreports.reload.success")));
    }

    private void sendNoPermissionMessage(CommandSender sender){
        msgConfig.getStringList("commands.tinyreports.no-permission")
                .forEach(line -> sender.sendMessage(ColorUtil.format(line)));
    }
}

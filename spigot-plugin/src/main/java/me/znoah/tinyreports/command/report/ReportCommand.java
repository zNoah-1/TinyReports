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

package me.znoah.tinyreports.command.report;

import me.znoah.tinyreports.command.CooldownableCommand;
import me.znoah.tinyreports.config.Config;
import me.znoah.tinyreports.report.ReportRegister;
import me.znoah.tinyreports.user.SpigotUser;
import me.znoah.tinyreports.util.chat.ColorUtil;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand extends CooldownableCommand implements CommandExecutor {
    private final ReportRegister reportRegister;
    private final Config msgConfig;

    public ReportCommand(ReportRegister reportRegister, Config msgConfig, Config mainConfig){
        super(mainConfig);

        this.reportRegister = reportRegister;
        this.msgConfig = msgConfig;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sendOnlyPlayersAllowed(sender);
            return true;
        }

        Player reporter = (Player) sender;

        if (playerIsInCooldown(reporter.getUniqueId())){
            sendInCooldown(sender);
            return true;
        }

        if (args.length < 2){
            sendCorrectUsage(sender);
        }
        else {
            String reportedName = args[0];
            StringBuilder reason = new StringBuilder(args[1]);

            for (int i = 2; i < args.length; i++){
                reason.append(" ").append(args[i]);
            }

            Player reported = Bukkit.getPlayer(reportedName);
            if (reported == null) reported = (Player) Bukkit.getOfflinePlayer(reportedName);

            if (reported == null) {
                sendPlayerDoesNotExist(sender);
            }
            else if (reporter == reported){
                sendYouCannotReportYourself(sender);
            }
            else {
                SpigotUser reporterUser = new SpigotUser((Player) sender);
                reportRegister.add(reportedName, reason.toString(), reporterUser);
                addToCooldown(((Player) sender).getUniqueId());
                sendSuccess(sender);
            }
        }

        return true;
    }

    private void sendPlayerDoesNotExist(CommandSender sender){
        sender.sendMessage(ColorUtil.format((String) msgConfig.get("commands.report.player-not-found")));
    }

    private void sendCorrectUsage(CommandSender sender){
        sender.sendMessage(ColorUtil.format((String) msgConfig.get("commands.report.usage")));
    }

    private void sendOnlyPlayersAllowed(CommandSender sender){
        sender.sendMessage(ColorUtil.format((String) msgConfig.get("commands.report.no-console")));
    }

    private void sendYouCannotReportYourself(CommandSender sender){
        sender.sendMessage(ColorUtil.format((String) msgConfig.get("commands.report.no-self-report")));
    }

    private void sendInCooldown(CommandSender sender){
        sender.sendMessage(ColorUtil.format((String) msgConfig.get("commands.report.in-cooldown")));
    }

    private void sendSuccess(CommandSender sender){
        sender.sendMessage(ColorUtil.format((String) msgConfig.get("commands.report.success")));
    }
}

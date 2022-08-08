package me.znoah.tinyreports.command.report;

import me.znoah.tinyreports.command.CooldownableCommand;
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

    public ReportCommand(ReportRegister reportRegister){
        super(60000);
        this.reportRegister = reportRegister;
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
        sender.sendMessage(ColorUtil.format("&cThat player does not exist"));
    }

    private void sendCorrectUsage(CommandSender sender){
        sender.sendMessage(ColorUtil.format("&cCorrect use: /report <player> <reason>"));
    }

    private void sendOnlyPlayersAllowed(CommandSender sender){
        sender.sendMessage(ColorUtil.format("&cOnly players can use this command"));
    }

    private void sendYouCannotReportYourself(CommandSender sender){
        sender.sendMessage(ColorUtil.format("&cYou can't report yourself"));
    }

    private void sendInCooldown(CommandSender sender){
        sender.sendMessage(ColorUtil.format("&cThis command is in cooldown, try again later"));
    }

    private void sendSuccess(CommandSender sender){
        sender.sendMessage(ColorUtil.format("&aReport sent successfully"));
    }
}

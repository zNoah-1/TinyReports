package me.znoah.tinyreports.command.report;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ReportTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1){
            List<String> playerNameList = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()){
                if (player == sender) continue;
                playerNameList.add(player.getName());
            }
            return playerNameList;
        }
        return new ArrayList<>();
    }
}

package me.znoah.tinyreports.listener;

import me.znoah.tinyreports.user.SpigotUser;
import me.znoah.tinyreports.user.staff.StaffRegistry;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {
    private final StaffRegistry staffRegistry;

    public JoinQuitListener(StaffRegistry staffRegistry) {
        this.staffRegistry = staffRegistry;
    }

    @EventHandler()
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (player.hasPermission("tinyreports.staff")){
            staffRegistry.add(new SpigotUser(player));
        }
    }

    @EventHandler()
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        if (player.hasPermission("tinyreports.staff")){
            staffRegistry.remove(player.getUniqueId());
        }
    }
}

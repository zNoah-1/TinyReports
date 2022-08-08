package me.znoah.tinyreports.user;

import me.znoah.tinyreports.util.chat.ColorUtil;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SpigotUser implements User {
    private final Player player;

    public SpigotUser(Player player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public UUID getPlayerUUID() {
        return player.getUniqueId();
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(ColorUtil.format(message));
    }
}

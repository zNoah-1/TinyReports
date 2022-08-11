package me.znoah.tinyreports.command;

import me.znoah.tinyreports.config.Config;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class CooldownableCommand {
    private final Map<UUID, Long> playersInCooldown = new HashMap<>();
    private final Config mainConfig;

    public CooldownableCommand(Config mainConfig) {
        this.mainConfig = mainConfig;
    }

    protected void addToCooldown(UUID playerUUID){
        playersInCooldown.put(playerUUID, System.currentTimeMillis());
        removeCooldownLater(playerUUID);
    }

    protected boolean playerIsInCooldown(UUID playerUUID){
        Long lastUse = playersInCooldown.get(playerUUID);
        return lastUse != null;
    }

    private void removeCooldownLater(UUID playerUUID){
        ActionListener taskPerformer = evt -> playersInCooldown.remove(playerUUID);
        Timer timer = new Timer((Integer) mainConfig.get("main-config.command-cooldown"), taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }
}

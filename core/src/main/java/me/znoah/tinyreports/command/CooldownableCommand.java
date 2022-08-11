package me.znoah.tinyreports.command;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class CooldownableCommand {
    private final Map<UUID, Long> playersInCooldown = new HashMap<>();
    private final int cooldownDuration; //In millis

    public CooldownableCommand(int cooldownDuration) {
        this.cooldownDuration = cooldownDuration;
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
        Timer timer = new Timer(cooldownDuration, taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }
}

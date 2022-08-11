/*
    TinyReports (core) - The core of TinyReports

    Copyright © 2022 zNoah-1

    Permission is hereby granted, free of charge, to any person obtaining a copy of this
    software and associated documentation files (the "Software"), to deal in the Software
    without restriction, including without limitation the rights to use, copy, modify,
    merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
    permit persons to whom the Software is furnished to do so, subject to the following
    conditions:

    The above copyright notice and this permission notice shall be included in all copies
    or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
    INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
    PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
    HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
    OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
    SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

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

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
